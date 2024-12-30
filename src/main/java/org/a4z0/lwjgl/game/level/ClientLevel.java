package org.a4z0.lwjgl.game.level;

import org.a4z0.lwjgl.api.level.Level;
import org.a4z0.lwjgl.api.level.chunk.Chunk;
import org.a4z0.lwjgl.api.position.BlockPosition;
import org.a4z0.lwjgl.Key;
import org.a4z0.lwjgl.api.text.TextComponent;
import org.a4z0.lwjgl.game.Game;
import org.a4z0.lwjgl.game.Main;
import org.a4z0.lwjgl.game.event.level.LevelRenderEvent;
import org.a4z0.lwjgl.game.level.chunk.provider.ClientChunkProvider;
import org.a4z0.lwjgl.game.registry.Registries;

import java.util.UUID;

public class ClientLevel implements Level {

    public final ClientChunkProvider provider;

    protected final UUID uuid;
    protected final TextComponent name;
    protected final long seed;

    /**
    * Construct a {@link ClientLevel}.
    *
    * @param uuid UUID.
    * @param name Name.
    * @param seed Seed.
    */

    public ClientLevel(UUID uuid, TextComponent name, long seed) {
        this.provider = new ClientChunkProvider(this);
        this.uuid = uuid;
        this.name = name;
        this.seed = seed;
    }

    @Override
    public long getSeed() {
        return this.seed;
    }

    @Override
    public UUID getUUID() {
        return this.uuid;
    }

    @Override
    public TextComponent getName() {
        return this.name;
    }

    @Override
    public Chunk getChunkAt(BlockPosition blockPosition) {
        return this.provider.getChunkAt(blockPosition.getX(), blockPosition.getY(), blockPosition.getZ());
    }

    @Override
    public void tick() {
        Main.EVENT_BUS.submit(new LevelRenderEvent.Post());
        Registries.SHADER_PROGRAM.getOrThrow(Key.of("world")).bind();
        Registries.SHADER_PROGRAM.getOrThrow(Key.of("world")).setUniform4fv("camera_projection_view", Game.PLAYER.getCamera().getProjectionView());
        this.provider.tick();
        Registries.SHADER_PROGRAM.getOrThrow(Key.of("world")).unbind();
        Main.EVENT_BUS.submit(new LevelRenderEvent.After());
    }

    @Override
    public String toString() {
        return "ClientLevel{" + "\"Name\": \"" + this.getName().getText() + "\"" + ", " + "\"UUID\":" + this.getUUID() + ", " + "\"Seed\":" + this.getSeed() + "}";
    }
}