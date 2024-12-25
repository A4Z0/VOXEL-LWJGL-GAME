package org.a4z0.lwjgl.demo.extra;

import org.a4z0.api.event.EventHandler;
import org.a4z0.api.math.position.ChunkPosition;
import org.a4z0.api.math.position.ChunkSectionPosition;
import org.a4z0.api.text.TextComponent;
import org.a4z0.lwjgl.demo.Game;
import org.a4z0.lwjgl.demo.Main;
import org.a4z0.lwjgl.demo.event.input.keyboard.KeyEvent;
import org.a4z0.lwjgl.demo.event.level.LevelRenderEvent;
import org.a4z0.lwjgl.demo.registry.Registries;
import org.a4z0.lwjgl.demo.resource.Key;
import org.a4z0.lwjgl.demo.resource.font.FontRenderer;
import org.a4z0.lwjgl.demo.resource.language.Language;

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

        ChunkPosition chunkPosition = ChunkPosition.ofLocation(Game.PLAYER.getLocation());
        ChunkSectionPosition layerPosition = ChunkSectionPosition.ofLocation(Game.PLAYER.getLocation());

        FontRenderer.drawString(TextComponent.empty()
            .append(TextComponent.translatable(Language.getInstance().get("debug.name")))
            .append(TextComponent.translatable(Language.getInstance().get("debug.framerate"), Main.CHRONO.getFPS(), 0f))
            .append(TextComponent.translatable(Language.getInstance().get("debug.location"), Game.PLAYER.getLocation().getX(), Game.PLAYER.getLocation().getY(), Game.PLAYER.getLocation().getZ()))
            .append(TextComponent.translatable(Language.getInstance().get("debug.facing"), Game.PLAYER.getLocation().getYaw(), Game.PLAYER.getLocation().getPitch()))
            .append(TextComponent.translatable(Language.getInstance().get("debug.chunk"), chunkPosition.getX(), chunkPosition.getY(), chunkPosition.getZ()))
            .append(TextComponent.translatable(Language.getInstance().get("debug.layer"), layerPosition.getX(), layerPosition.getY(), layerPosition.getZ())),
        3f, 3f, 1.5f);

        FontRenderer.flush();
        Registries.SHADER_PROGRAM.getOrThrow(Key.of("text")).unbind();
    }
}