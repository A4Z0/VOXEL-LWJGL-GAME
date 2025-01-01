package org.a4z0.lwjgl.game.controller;

import org.a4z0.lwjgl.game.Game;
import org.a4z0.lwjgl.game.Main;
import org.a4z0.lwjgl.game.input.InputAction;
import org.a4z0.lwjgl.game.input.InputHandler;

import static org.lwjgl.glfw.GLFW.*;

public class PlayerController {

    public static float last_mouse_x = 0f;
    public static float last_mouse_y = 0f;

    public static void tick() {
        _LEGACY_TICK_MOVEMENT();
        _LEGACY_TICK_FLY();
        _LEGACY_TICK_JUMP();
    }

    public static void _LEGACY_HEAD_HANDLER(double x, double y) {
        final float sensitivity = 0.05f;

        Game.PLAYER.getLocation().setYaw((float) (Game.PLAYER.getLocation().getYaw() + ((last_mouse_x - x) * -sensitivity)));
        Game.PLAYER.getLocation().setPitch((float) (Game.PLAYER.getLocation().getPitch() - ((last_mouse_y - y) * sensitivity)));

        last_mouse_x = (float) x;
        last_mouse_y = (float) y;
    }

    public static void _LEGACY_TICK_MOVEMENT() {
        float yawRadians = (float) Math.toRadians(Game.PLAYER.getLocation().getYaw());
        float dx = (float) Math.sin(-yawRadians);
        float dz = (float) Math.cos(yawRadians);

        float vertical_speed_x = 0f;
        float vertical_speed_z = 0f;

        if(InputHandler.getKey(GLFW_KEY_W) != InputAction.RELEASE)
            vertical_speed_x += 1f;
        if(InputHandler.getKey(GLFW_KEY_A) != InputAction.RELEASE)
            vertical_speed_z += 1f;
        if(InputHandler.getKey(GLFW_KEY_S) != InputAction.RELEASE)
            vertical_speed_x -= 1f;
        if(InputHandler.getKey(GLFW_KEY_D) != InputAction.RELEASE)
            vertical_speed_z -= 1f;

        float walkSpeed = (float) (Game.PLAYER.getWalkSpeed() * Main.CHRONO.getDeltaTime());

        Game.PLAYER.getVelocity().add(dx * walkSpeed * vertical_speed_x, 0, dz * walkSpeed * vertical_speed_x);
        Game.PLAYER.getVelocity().add(dz * walkSpeed * vertical_speed_z, 0, -dx * walkSpeed * vertical_speed_z);
    }

    public static void _LEGACY_TICK_FLY() {
        if(InputHandler.getKey(GLFW_KEY_F) == InputAction.DOWN)
            Game.PLAYER.setFlight(!Game.PLAYER.isFlying());

        if(!Game.PLAYER.isFlying())
            return;

        float horizontal_speed = 0f;

        if(InputHandler.getKey(GLFW_KEY_SPACE) != InputAction.RELEASE)
            horizontal_speed += 1;
        if(InputHandler.getKey(GLFW_KEY_LEFT_SHIFT) != InputAction.RELEASE)
            horizontal_speed -= 1f;

        float flightSpeed = (float) (Game.PLAYER.getFlightSpeed() * Main.CHRONO.getDeltaTime());

        Game.PLAYER.getVelocity().add(0, flightSpeed * horizontal_speed, 0);
    }

    public static void _LEGACY_TICK_JUMP() {
        if(Game.PLAYER.isFlying() || !Game.PLAYER.isOnGround())
            return;

        if(InputHandler.getKey(GLFW_KEY_SPACE) != InputAction.DOWN)
            return;

        Game.PLAYER.jump();
    }
}
