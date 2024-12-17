package org.a4z0.lwjgl.demo;

import org.a4z0.lwjgl.demo.chrono.Chrono;
import org.a4z0.lwjgl.demo.event.EventBus;
import org.a4z0.lwjgl.demo.event.client.input.mouse.MouseMoveEvent;
import org.a4z0.lwjgl.demo.event.client.render.WorldRenderEvent;
import org.a4z0.lwjgl.demo.event.client.setup.CommonSetupEvent;
import org.a4z0.lwjgl.demo.event.client.window.WindowReziseEvent;
import org.a4z0.lwjgl.demo.registry.Registries;
import org.a4z0.lwjgl.demo.screen.Debugger;
import org.a4z0.lwjgl.demo.input.InputHandler;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public final class Main {

    public static Chrono CHRONO;
    public static final EventBus EVENT_BUS = new EventBus();

    public static long WINDOW;
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

        CHRONO = new Chrono();
        CHRONO.setTargetFPS(10000);

        EVENT_BUS.register(new Game());
        EVENT_BUS.register(new Debugger());

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
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
        glLineWidth(1f);

        Registries.init();
        Registries.bootstrap();
        InputHandler.init();

        EVENT_BUS.submit(new CommonSetupEvent());

        while(!glfwWindowShouldClose(WINDOW)) {
            if(!CHRONO.update())
                continue;

            glfwPollEvents();

            glViewport(0, 0, Game.getWidth(), Game.getHeight());
            glClear(GL_COLOR_BUFFER_BIT  | GL_DEPTH_BUFFER_BIT);
            glClearColor(0.6f, 0.8f, 1f, 1);

            EVENT_BUS.submit(new WorldRenderEvent.Post());
            EVENT_BUS.submit(new WorldRenderEvent.After());

            glFlush();
            glfwSwapBuffers(WINDOW);

            if(EXIT_SIGNAL)
                break;
        }

        glfwTerminate();
        System.exit(0);
    }
}