package org.a4z0.lwjgl.demo;

import org.a4z0.lwjgl.demo.registry.Registries;
import org.a4z0.lwjgl.demo.resource.font.Fonts;
import org.a4z0.lwjgl.demo.resource.font.FontRenderer;
import org.a4z0.lwjgl.demo.resource.shader.ShaderPrograms;
import org.a4z0.lwjgl.demo.resource.shader.Shaders;
import org.a4z0.lwjgl.demo.time.Timer;
import org.a4z0.lwjgl.demo.ui.Debug;
import org.a4z0.lwjgl.demo.util.Input;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public final class Main {

    public static long GL_WINDOW;
    public static int GL_WINDOW_WIDTH = 800, GL_WINDOW_HEIGHT = 600;
    public static double GL_CURSOR_X = 0, GL_CURSOR_Y = 0;

    public static boolean PAUSED;
    public static boolean CLOSE;

    public static Timer TIMER;
    public static Debug DEBUG;
    public static FontRenderer FONT_RENDERER;

    public static void main(String[] args) {
        if(!glfwInit())
            throw new IllegalStateException("Couldn't initialize GLFW!");

        glfwWindowHint(GLFW_SAMPLES, 16);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        GL_WINDOW = glfwCreateWindow(GL_WINDOW_WIDTH, GL_WINDOW_HEIGHT, "LWJGL", 0, 0);

        if(GL_WINDOW == 0)
            throw new IllegalStateException("Unable to create a Window!");

        glfwMakeContextCurrent(GL_WINDOW);
        glfwSwapInterval(0);

        GL.createCapabilities();

        //noinspection resource
        glfwSetFramebufferSizeCallback(GL_WINDOW, (_ignored, w, h) -> {
            GL_WINDOW_WIDTH = w;
            GL_WINDOW_HEIGHT = h;
        });

        //noinspection resource
        glfwSetCursorPosCallback(GL_WINDOW, (_ignored, x, y) -> {
            GL_CURSOR_X = x;
            GL_CURSOR_Y = y;
        });

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
        Shaders.init();
        ShaderPrograms.init();
        DevelopmentConstants.init();
        Fonts.init();

        TIMER = new Timer();
        DEBUG = new Debug();
        FONT_RENDERER = new FontRenderer();

        while(!glfwWindowShouldClose(GL_WINDOW) && !CLOSE) {
            TIMER.getDeltaTime();
            TIMER.updateUPS();

            glViewport(0, 0, GL_WINDOW_WIDTH, GL_WINDOW_HEIGHT);
            glClear(GL_COLOR_BUFFER_BIT  | GL_DEPTH_BUFFER_BIT);
            glClearColor(0.6f, 0.8f, 1f, 1);

            Input.update();
            glfwPollEvents();

            DEBUG.onRender();

            TIMER.updateFPS();
            TIMER.update();

            glFlush();
            glfwSwapBuffers(GL_WINDOW);
        }

        glfwTerminate();
        System.exit(0);
    }
}