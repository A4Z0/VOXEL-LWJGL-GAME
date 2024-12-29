package org.a4z0.lwjgl.demo;

import org.a4z0.api.event.EventHandler;
import org.a4z0.api.event.EventPriority;
import org.a4z0.api.text.TextComponent;
import org.a4z0.lwjgl.demo.controller.PlayerController;
import org.a4z0.lwjgl.demo.event.input.mouse.MouseMoveEvent;
import org.a4z0.lwjgl.demo.event.setup.CommonSetupEvent;
import org.a4z0.lwjgl.demo.event.window.WindowReziseEvent;
import org.a4z0.lwjgl.demo.level.ClientLevel;
import org.a4z0.lwjgl.demo.entity.EntityPlayer;

import java.util.UUID;

public final class Game {

    private static int WIDTH = 800;
    private static int HEIGHT = 600;

    public static ClientLevel LEVEL;
    public static EntityPlayer PLAYER;

    @EventHandler
    private void onStartup(final CommonSetupEvent e) {
        LEVEL = new ClientLevel(UUID.randomUUID(), TextComponent.empty(), 0);
        PLAYER = new EntityPlayer(TextComponent.empty(), 0, 8, 0, 0, 90f);

        LEVEL.provider.load(0, 0, 0);
    }

    @EventHandler
    private void onMouse(final MouseMoveEvent e) {
        if(Main.FREEZE_SIGNAL)
            return;

        PlayerController._LEGACY_HEAD_HANDLER(e.getX(), e.getY());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private void onResize(final WindowReziseEvent e) {
        WIDTH = e.getWidth();
        HEIGHT = e.getHeight();

        PLAYER.getCamera().setWidth(e.getWidth()).setHeight(e.getHeight());
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