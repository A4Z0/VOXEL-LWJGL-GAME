package org.a4z0.lwjgl.demo;

import org.a4z0.lwjgl.demo.behavior.ClientCameraBehavior;
import org.a4z0.lwjgl.demo.font.Fonts;
import org.a4z0.lwjgl.demo.font.Glyph;
import org.a4z0.lwjgl.demo.level.Location;
import org.a4z0.lwjgl.demo.render.GLFontRenderer;
import org.a4z0.lwjgl.demo.shader.ShaderPrograms;
import org.a4z0.lwjgl.demo.shader.Shaders;
import org.a4z0.lwjgl.demo.util.Color;
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
    public static boolean DEBUG = true;

    public static GLFontRenderer FONT_RENDERER;

    public static void main(String[] args) {
        System.out.println("[LWJGL]: Initializing...");

        if(!glfwInit())
            throw new IllegalStateException("Couldn't initialize GLFW!");

        System.out.println("[LWJGL]: Preparing Window...");
        glfwWindowHint(GLFW_SAMPLES, 16);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        System.out.println("[LWJGL]: Creating Window.");
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
            ClientCameraBehavior.onMouseMove(x, y);
            GL_CURSOR_X = x;
            GL_CURSOR_Y = y;
        });

        System.out.println("[Game]: -> \"Pre.Load\".");

        Fonts.init();
        Shaders.init();
        ShaderPrograms.init();
        DevelopmentConstants.init();
        FONT_RENDERER = new GLFontRenderer();

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glClearColor(0f, 0f, 0f, 1.0f);
        glEnable(GL_DEPTH_TEST);
        glClearDepth(1f);
        glDepthFunc(GL_LEQUAL);
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
        glLineWidth(1f);

        System.out.println("[Game]: -> \"Post.Load\".");

        while(!glfwWindowShouldClose(GL_WINDOW) && !CLOSE) {
            glViewport(0, 0, GL_WINDOW_WIDTH, GL_WINDOW_HEIGHT);
            glClear(GL_COLOR_BUFFER_BIT  | GL_DEPTH_BUFFER_BIT);
            glClearColor(0.6f, 0.8f, 1f, 1);

            Input.update();
            glfwPollEvents();

            if(!PAUSED)
                DevelopmentConstants.DEVELOPMENT_PLAYER.tick();
            if(Input.isKeyPressed(GLFW_KEY_P))
                PAUSED = !PAUSED;
            if(Input.isKeyPressed(GLFW_KEY_F3))
                DEBUG = !DEBUG;

            ShaderPrograms.WORLD_SHADER_PROGRAM.bind();
            ShaderPrograms.WORLD_SHADER_PROGRAM.setUniform4fv("camera_projection_view", DevelopmentConstants.DEVELOPMENT_CAMERA.getProjectionView());
            DevelopmentConstants.DEVELOPMENT_LEVEL.tick();
            ShaderPrograms.WORLD_SHADER_PROGRAM.unbind();

            if(DEBUG) {
                Location Location = DevelopmentConstants.DEVELOPMENT_PLAYER.getLocation();
                FONT_RENDERER.drawString(
                        "FPS: " + 0
                                + "\n"
                                + String.format("XYZ: %.3f / %.3f / %.3f", Location.getX(), Location.getY(), Location.getZ())
                                + "\n"
                                + String.format("Facing: (%.3f / %.3f)", Location.getPitch(), Location.getYaw()),
                        2f,
                        0f
                );
            }

            glFlush();
            glfwSwapBuffers(GL_WINDOW);
        }

        System.out.println("[Game]: -> \"Close\".");

        glfwTerminate();
        System.exit(0);
    }
}