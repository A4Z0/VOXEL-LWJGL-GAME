package org.a4z0.lwjgl.demo.extra;

import org.a4z0.api.event.EventHandler;
import org.a4z0.api.math.aabb.AABBi;
import org.a4z0.api.math.position.ChunkPosition;
import org.a4z0.lwjgl.demo.Game;
import org.a4z0.lwjgl.demo.event.input.keyboard.KeyEvent;
import org.a4z0.lwjgl.demo.event.level.LevelRenderEvent;
import org.a4z0.lwjgl.demo.registry.Registries;
import org.a4z0.lwjgl.demo.resource.Key;
import org.a4z0.lwjgl.demo.extra.test.AABBRenderer;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_G;

public final class ChunkBondaries {

    public static boolean ENABLED;

    private static final AABBi Chunk = new AABBi(0, 0, 0, 32, 32, 32);

    @EventHandler
    private void onKeyDown(final KeyEvent.Down e) {
        if(e.getCode() != GLFW_KEY_G)
            return;

        ENABLED = !ENABLED;
    }

    @EventHandler
    private void onRender(final LevelRenderEvent.After e) {
        if(!ENABLED)
            return;

        Registries.SHADER_PROGRAM.getOrThrow(Key.of("outline")).bind();
        Registries.SHADER_PROGRAM.getOrThrow(Key.of("outline")).setUniform4fv("camera_projection_view", Game.CAMERA.getProjectionView());

        ChunkPosition chunkPosition = ChunkPosition.ofLocation(Game.PLAYER.getLocation());
        AABBRenderer.draw(Chunk, chunkPosition.getX(), chunkPosition.getY(), chunkPosition.getZ(), 255, 255, 0);

        AABBRenderer.flush();
        Registries.SHADER_PROGRAM.getOrThrow(Key.of("outline")).unbind();
    }
}