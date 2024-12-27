package org.a4z0.lwjgl.demo.controller;

import org.a4z0.lwjgl.demo.Game;
import org.a4z0.lwjgl.demo.input.InputAction;
import org.a4z0.lwjgl.demo.input.InputHandler;

import static org.lwjgl.glfw.GLFW.*;

public class PlayerController {
    
    public static float vertical_speed_x;
    public static float vertical_speed_z;
    public static float horizontal_speed;
    public static float sprint_speed;

    public static boolean visible = true;
    public static float last_mouse_x = 0f;
    public static float last_mouse_y = 0f;

    public static void tick() {
        tickInput();
        tickMovement();
    }

    private static void tickInput() {
        float vertical_speed_x = 0;
        float vertical_speed_z = 0;
        float horizontal_speed = 0;
        float sprint_speed = 1f;

        // Walk
        if(InputHandler.getKey(GLFW_KEY_W) != InputAction.RELEASE)
            vertical_speed_x += 1f;
        if(InputHandler.getKey(GLFW_KEY_A) != InputAction.RELEASE)
            vertical_speed_z += 1f;
        if(InputHandler.getKey(GLFW_KEY_S) != InputAction.RELEASE)
            vertical_speed_x -= 1f;
        if(InputHandler.getKey(GLFW_KEY_D) != InputAction.RELEASE)
            vertical_speed_z -= 1f;

        // Fly
        if(InputHandler.getKey(GLFW_KEY_F) == InputAction.DOWN)
            Game.PLAYER.setFlight(!Game.PLAYER.isFlying());
        if(InputHandler.getKey(GLFW_KEY_SPACE) != InputAction.RELEASE && Game.PLAYER.isFlying())
            horizontal_speed += 1f;
        if(InputHandler.getKey(GLFW_KEY_LEFT_SHIFT) != InputAction.RELEASE && Game.PLAYER.isFlying())
            horizontal_speed -= 1f;

        // Sprint
        if(InputHandler.getKey(GLFW_KEY_LEFT_CONTROL) != InputAction.RELEASE)
            sprint_speed = 2f;

        PlayerController.vertical_speed_x = vertical_speed_x;
        PlayerController.vertical_speed_z = vertical_speed_z;
        PlayerController.horizontal_speed = horizontal_speed;
        PlayerController.sprint_speed = sprint_speed;
    }

    public static void tickHeadMovement(double x, double y) {
        final float sensitivity = 0.05f;

        Game.PLAYER.getLocation().setYaw((float) (Game.PLAYER.getLocation().getYaw() + ((last_mouse_x - x) * -sensitivity)));
        Game.PLAYER.getLocation().setPitch((float) (Game.PLAYER.getLocation().getPitch() - ((last_mouse_y - y) * sensitivity)));

        last_mouse_x = (float) x;
        last_mouse_y = (float) y;

        updateBody();
    }

    private static void tickMovement() {
        float vertical_speed_x = PlayerController.vertical_speed_x * sprint_speed * Game.PLAYER.getWalkSpeed();
        float vertical_speed_z = PlayerController.vertical_speed_z * sprint_speed * Game.PLAYER.getWalkSpeed();
        float horizontal_speed = PlayerController.horizontal_speed * sprint_speed * Game.PLAYER.getFlightSpeed();

        float RADIANS = (float) Math.toRadians(Game.PLAYER.getLocation().getYaw());

        float DIR_X = (float) Math.sin(-RADIANS);
        float DIR_Z = (float) Math.cos(RADIANS);

        Game.PLAYER.getLocation().add(DIR_X * vertical_speed_x, 0, DIR_Z *  vertical_speed_x);
        Game.PLAYER.getLocation().add(DIR_Z * vertical_speed_z, 0, -DIR_X * vertical_speed_z);
        Game.PLAYER.getLocation().add(0, horizontal_speed, 0);

        updateBody();
    }

    private static void updateBody() {
        Game.CAMERA.set(Game.PLAYER.getLocation());
        Game.CAMERA.setWidth(Game.getWidth()).setHeight(Game.getHeight());
    }
}
