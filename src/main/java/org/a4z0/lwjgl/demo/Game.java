package org.a4z0.lwjgl.demo;

import org.a4z0.lwjgl.demo.event.EventHandler;
import org.a4z0.lwjgl.demo.event.EventPriority;
import org.a4z0.lwjgl.demo.event.input.mouse.MouseMoveEvent;
import org.a4z0.lwjgl.demo.event.setup.CommonSetupEvent;
import org.a4z0.lwjgl.demo.event.window.WindowReziseEvent;
import org.a4z0.lwjgl.demo.level.Level;
import org.a4z0.lwjgl.demo.entity.EntityPlayer;
import org.a4z0.lwjgl.demo.math.camera.Camera3fa;
import org.a4z0.lwjgl.demo.text.TextComponent;

import java.util.UUID;

public final class Game {

    private static int WIDTH = 800;
    private static int HEIGHT = 600;

    public static Level LEVEL;
    public static EntityPlayer PLAYER;
    public static Camera3fa CAMERA;

    @EventHandler
    private void onStartup(final CommonSetupEvent e) {
        LEVEL = new Level(UUID.randomUUID(), TextComponent.empty(), 0);
        PLAYER = new EntityPlayer(TextComponent.empty(), 0, 8, 0, 0, 90f);
        CAMERA = new Camera3fa<>();

        LEVEL.provider.load(0, 0, 0);
    }

    @EventHandler
    private void onMouse(final MouseMoveEvent e) {
        if(Main.FREEZE_SIGNAL)
            return;

        PLAYER.tickHeadMovement(e.getX(), e.getY());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private void onResize(final WindowReziseEvent e) {
        WIDTH = e.getWidth();
        HEIGHT = e.getHeight();
    }

    /**
    * @return the Width.
    */

    public static int getWidth() {
        return WIDTH;
    }

    /**
    * @return the Height.
    */

    public static int getHeight() {
        return HEIGHT;
    }
}