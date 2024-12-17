package org.a4z0.lwjgl.demo;

import org.a4z0.lwjgl.demo.event.EventHandler;
import org.a4z0.lwjgl.demo.event.EventPriority;
import org.a4z0.lwjgl.demo.event.client.render.WorldRenderEvent;
import org.a4z0.lwjgl.demo.event.client.window.WindowReziseEvent;
import org.a4z0.lwjgl.demo.registry.Registries;
import org.a4z0.lwjgl.demo.resource.Key;
import org.a4z0.lwjgl.demo.resource.font.FontRenderer;

public final class Game {

    private static int WIDTH = 800;
    private static int HEIGHT = 600;
    
    @EventHandler(priority = EventPriority.LOWEST)
    private void onResize(final WindowReziseEvent e) {
        WIDTH = e.getWidth();
        HEIGHT = e.getHeight();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onFlush(final WorldRenderEvent.After e) {
        FontRenderer.flush();
        Registries.SHADER_PROGRAM.get(Key.of("text")).unbind();
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