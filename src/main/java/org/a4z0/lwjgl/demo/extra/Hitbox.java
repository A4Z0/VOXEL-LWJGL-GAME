package org.a4z0.lwjgl.demo.extra;

import org.a4z0.api.event.EventHandler;
import org.a4z0.api.location.Location3f;
import org.a4z0.math.vector.Vector3f;
import org.a4z0.api.position.ChunkPosition;
import org.a4z0.lwjgl.demo.Game;
import org.a4z0.lwjgl.demo.entity.EntityPlayer;
import org.a4z0.lwjgl.demo.event.input.keyboard.KeyEvent;
import org.a4z0.lwjgl.demo.event.level.LevelRenderEvent;
import org.a4z0.lwjgl.demo.extra.test.AABBRenderer;
import org.a4z0.lwjgl.demo.registry.Registries;
import org.a4z0.lwjgl.demo.resource.Key;

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