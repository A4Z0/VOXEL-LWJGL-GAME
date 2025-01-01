package org.a4z0.lwjgl.game;

import org.a4z0.lwjgl.api.event.EventBus;
import org.a4z0.lwjgl.game.controller.PlayerController;
import org.a4z0.lwjgl.game.event.input.mouse.MouseMoveEvent;
import org.a4z0.lwjgl.game.event.setup.CommonSetupEvent;
import org.a4z0.lwjgl.game.event.window.WindowReziseEvent;
import org.a4z0.lwjgl.game.extra.ChunkBondaries;
import org.a4z0.lwjgl.game.extra.Debugger;
import org.a4z0.lwjgl.game.extra.Hitbox;
import org.a4z0.lwjgl.game.input.InputAction;
import org.a4z0.lwjgl.game.input.InputHandler;
import org.a4z0.lwjgl.game.registry.Registries;
import org.a4z0.lwjgl.game.util.Timer;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public final class Main {

    public static Timer CHRONO;

    public static final EventBus EVENT_BUS = new EventBus();
    public static long WINDOW;

    public static boolean FREEZE_SIGNAL;
    public static boolean CURSOR_ENABLED;

    public static boolean EXIT_SIGNAL;

    public static void main(String[] args) {
        if(!glfwInit())
            throw new IllegalStateException("Couldn't initialize GLFW!");

        glfwWindowHint(GLFW_SAMPLES, 16);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        WINDOW = glfwCreateWindow(Game.getWidth(), Game.getHeight(), "LWJGL", 0, 0);

        if(WINDOW == 0)
            throw new IllegalStateException("Unable to create a Window!");

        glfwMakeContextCurrent(WINDOW);
        glfwSwapInterval(0);

        GL.createCapabilities();

        CHRONO = new Timer();
        EVENT_BUS.register(new Game());
        EVENT_BUS.register(new Debugger());
        EVENT_BUS.register(new ChunkBondaries());
        EVENT_BUS.register(new Hitbox());

        //noinspection resource
        glfwSetFramebufferSizeCallback(WINDOW, (_ignored, w, h) -> EVENT_BUS.submit(new WindowReziseEvent(w, h)));
        //noinspection resource
        glfwSetCursorPosCallback(WINDOW, (_ignored, x, y) -> EVENT_BUS.submit(new MouseMoveEvent(x, y)));

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glClearColor(0f, 0f, 0f, 1.0f);
        glEnable(GL_DEPTH_TEST);
        glClearDepth(1f);
        glDepthFunc(GL_LEQUAL);
        //glEnable(GL_CULL_FACE);
        //glCullFace(GL_BACK);
        glLineWidth(1f);
        
        InputHandler.init();

        EVENT_BUS.submit(new CommonSetupEvent());

        // Disable Cursor
        glfwSetInputMode(WINDOW, GLFW_CURSOR, GLFW_CURSOR_DISABLED);

        Registries.init();

        while(!glfwWindowShouldClose(WINDOW)) {
            CHRONO.update();

            glfwPollEvents();

            if(InputHandler.getKey(GLFW_KEY_ESCAPE) != InputAction.RELEASE && InputHandler.getKey(GLFW_KEY_X) == InputAction.DOWN)
                EXIT_SIGNAL = true;

            glViewport(0, 0, Game.getWidth(), Game.getHeight());
            glClear(GL_COLOR_BUFFER_BIT  | GL_DEPTH_BUFFER_BIT);
            glClearColor(0.6f, 0.8f, 1f, 1);

            PlayerController.tick();
            Game.PLAYER.tick();
            Game.LEVEL.tick();

            glFlush();
            glfwSwapBuffers(WINDOW);

            if(EXIT_SIGNAL)
                break;
        }

        glfwTerminate();
        System.exit(0);
    }
}