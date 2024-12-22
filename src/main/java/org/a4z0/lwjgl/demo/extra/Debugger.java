package org.a4z0.lwjgl.demo.extra;

import org.a4z0.lwjgl.demo.Game;
import org.a4z0.lwjgl.demo.Main;
import org.a4z0.lwjgl.demo.event.EventHandler;
import org.a4z0.lwjgl.demo.event.input.keyboard.KeyEvent;
import org.a4z0.lwjgl.demo.event.level.LevelRenderEvent;
import org.a4z0.lwjgl.demo.level.chunk.ChunkPosition;
import org.a4z0.lwjgl.demo.level.chunk.layer.ChunkLayerPosition;
import org.a4z0.lwjgl.demo.registry.Registries;
import org.a4z0.lwjgl.demo.resource.Key;
import org.a4z0.lwjgl.demo.resource.font.FontRenderer;
import org.a4z0.lwjgl.demo.text.TextComponent;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_F3;

public final class Debugger {

    public static boolean ENABLED;

    @EventHandler
    private void onKeyDown(final KeyEvent.Down e) {
        if(e.getCode() != GLFW_KEY_F3)
            return;

        ENABLED = !ENABLED;
    }

    @EventHandler
    private void onRender(final LevelRenderEvent.After e) {
        if(!ENABLED)
            return;

        Registries.SHADER_PROGRAM.getOrThrow(Key.of("text")).bind();

        ChunkPosition chunkPosition = ChunkPosition.ofEntity(Game.PLAYER.getPosition());
        ChunkLayerPosition layerPosition = ChunkLayerPosition.ofEntity(Game.PLAYER.getPosition());

        FontRenderer.drawString(TextComponent.empty()
            .append(TextComponent.translatable("debug.name"))
            .append(TextComponent.translatable("debug.framerate", Main.CHRONO.getFPS(), 0f))
            .append(TextComponent.translatable("debug.location", Game.PLAYER.getPosition().getX(), Game.PLAYER.getPosition().getY(), Game.PLAYER.getPosition().getZ()))
            .append(TextComponent.translatable("debug.facing", Game.PLAYER.getYaw(), Game.PLAYER.getPitch()))
            .append(TextComponent.translatable("debug.chunk", chunkPosition.getX(), chunkPosition.getY(), chunkPosition.getZ()))
            .append(TextComponent.translatable("debug.layer", layerPosition.getX(), layerPosition.getY(), layerPosition.getZ())),
        3f, 3f, 1.5f);

        FontRenderer.flush();
        Registries.SHADER_PROGRAM.getOrThrow(Key.of("text")).unbind();
    }
}