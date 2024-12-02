package org.a4z0.lwjgl.demo.behavior;

import org.a4z0.lwjgl.demo.DevelopmentConstants;
import org.a4z0.lwjgl.demo.Main;
import org.a4z0.lwjgl.demo.level.Location;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_CURSOR_NORMAL;

public final class ClientCameraBehavior {

    private static boolean VISIBLE = true;
    private static float LAST_MOUSE_X = 0f;
    private static float LAST_MOUSE_Y = 0f;

    public static void tick() {
        setCameraAtPlayer();
    }

    private static void setCameraAtPlayer() {
        Location PlayerLocation = DevelopmentConstants.DEVELOPMENT_PLAYER.getLocation()
            .clone().add(0.5f, 2f, 0.5f);

        DevelopmentConstants.DEVELOPMENT_CAMERA
            .position(PlayerLocation.getX(), PlayerLocation.getY(), PlayerLocation.getZ())
            .setPitch(DevelopmentConstants.DEVELOPMENT_PLAYER.getLocation().getPitch())
            .setYaw(DevelopmentConstants.DEVELOPMENT_PLAYER.getLocation().getYaw());

        DevelopmentConstants.DEVELOPMENT_CAMERA.setWidth(Main.GL_WINDOW_WIDTH);
        DevelopmentConstants.DEVELOPMENT_CAMERA.setHeight(Main.GL_WINDOW_HEIGHT);
    }

    public static void onMouseMove(double x, double y) {
        if(!Main.PAUSED) {
            if(VISIBLE) {
                glfwSetInputMode(Main.GL_WINDOW, GLFW_CURSOR, GLFW_CURSOR_DISABLED);

                VISIBLE = false;
            }

            final float SENSITIVITY = 0.05f;

            DevelopmentConstants.DEVELOPMENT_PLAYER.getLocation()
               .setYaw((float) (DevelopmentConstants.DEVELOPMENT_PLAYER.getLocation().getYaw() + ((LAST_MOUSE_X - x) * -SENSITIVITY)));
            DevelopmentConstants.DEVELOPMENT_PLAYER.getLocation()
               .setPitch((float) (DevelopmentConstants.DEVELOPMENT_PLAYER.getLocation().getPitch() - ((LAST_MOUSE_Y - y) * SENSITIVITY)));

            LAST_MOUSE_X = (float) x;
            LAST_MOUSE_Y = (float) y;
        } else {
            if(!VISIBLE) {
                glfwSetInputMode(Main.GL_WINDOW, GLFW_CURSOR, GLFW_CURSOR_NORMAL);

                VISIBLE = true;
            }
        }
    }
}