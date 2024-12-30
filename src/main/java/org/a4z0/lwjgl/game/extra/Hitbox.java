package org.a4z0.lwjgl.game.extra;

import org.a4z0.lwjgl.api.event.EventHandler;
import org.a4z0.lwjgl.Key;
import org.a4z0.lwjgl.game.Game;
import org.a4z0.lwjgl.game.event.input.keyboard.KeyEvent;
import org.a4z0.lwjgl.game.event.level.LevelRenderEvent;
import org.a4z0.lwjgl.game.extra.test.AABBRenderer;
import org.a4z0.lwjgl.game.registry.Registries;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_B;

public final class Hitbox {

    public static boolean ENABLED;

    @EventHandler
    private void onKeyDown(final KeyEvent.Down e) {
        if(e.getCode() != GLFW_KEY_B)
            return;

        ENABLED = !ENABLED;
    }

    @EventHandler
    private void onRender(final LevelRenderEvent.After e) {
        if(!ENABLED)
            return;

        Registries.SHADER_PROGRAM.getOrThrow(Key.of("outline")).bind();
        Registries.SHADER_PROGRAM.getOrThrow(Key.of("outline")).setUniform4fv("camera_projection_view", Game.PLAYER.getCamera().getProjectionView());

        AABBRenderer.draw(Game.PLAYER.getBody(), 0, 0, 0, 255, 255, 255);

        AABBRenderer.flush();
        Registries.SHADER_PROGRAM.getOrThrow(Key.of("outline")).unbind();
    }
}