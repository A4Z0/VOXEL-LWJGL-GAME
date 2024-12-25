package org.a4z0.lwjgl.demo.level;

import org.a4z0.api.level.Level;
import org.a4z0.api.level.chunk.Chunk;
import org.a4z0.api.level.chunk.provider.ChunkProvider;
import org.a4z0.api.math.position.BlockPosition;
import org.a4z0.api.text.TextComponent;
import org.a4z0.lwjgl.demo.Game;
import org.a4z0.lwjgl.demo.Main;
import org.a4z0.lwjgl.demo.event.level.LevelRenderEvent;
import org.a4z0.lwjgl.demo.level.chunk.provider.ClientChunkProvider;
import org.a4z0.lwjgl.demo.registry.Registries;
import org.a4z0.lwjgl.demo.resource.Key;

import java.util.UUID;

public class ClientLevel implements Level {

    public final ChunkProvider provider;

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
        Registries.SHADER_PROGRAM.getOrThrow(Key.of("world")).setUniform4fv("camera_projection_view", Game.CAMERA.getProjectionView());
        this.provider.tick();
        Registries.SHADER_PROGRAM.getOrThrow(Key.of("world")).unbind();
        Main.EVENT_BUS.submit(new LevelRenderEvent.After());
    }

    @Override
    public String toString() {
        return "ClientLevel{" + "\"Name\": \"" + this.getName().getText() + "\"" + ", " + "\"UUID\":" + this.getUUID() + ", " + "\"Seed\":" + this.getSeed() + "}";
    }
}