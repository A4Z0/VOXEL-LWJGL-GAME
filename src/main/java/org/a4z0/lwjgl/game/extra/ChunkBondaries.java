package org.a4z0.lwjgl.game.extra;

import org.a4z0.lwjgl.api.event.EventHandler;
import org.a4z0.lwjgl.Key;
import org.a4z0.lwjgl.math.aabb.AABBi;
import org.a4z0.lwjgl.api.position.ChunkPosition;
import org.a4z0.lwjgl.game.Game;
import org.a4z0.lwjgl.game.event.input.keyboard.KeyEvent;
import org.a4z0.lwjgl.game.event.level.LevelRenderEvent;
import org.a4z0.lwjgl.game.registry.Registries;
import org.a4z0.lwjgl.game.extra.test.AABBRenderer;

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
        Registries.SHADER_PROGRAM.getOrThrow(Key.of("outline")).setUniform4fv("camera_projection_view", Game.PLAYER.getCamera().getProjectionView());

        ChunkPosition chunkPosition = ChunkPosition.ofLocation(Game.PLAYER.getLocation());
        AABBRenderer.draw(Chunk, chunkPosition.getX(), chunkPosition.getY(), chunkPosition.getZ(), 255, 255, 0);

        AABBRenderer.flush();
        Registries.SHADER_PROGRAM.getOrThrow(Key.of("outline")).unbind();
    }
}