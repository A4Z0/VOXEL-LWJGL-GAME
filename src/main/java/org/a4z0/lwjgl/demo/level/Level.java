package org.a4z0.lwjgl.demo.level;

import org.a4z0.lwjgl.demo.Game;
import org.a4z0.lwjgl.demo.Main;
import org.a4z0.lwjgl.demo.event.level.LevelRenderEvent;
import org.a4z0.lwjgl.demo.level.chunk.Chunk;
import org.a4z0.lwjgl.demo.level.chunk.ChunkProvider;
import org.a4z0.lwjgl.demo.level.chunk.LocalChunkProvider;
import org.a4z0.lwjgl.demo.level.chunk.layer.voxel.Voxel;
import org.a4z0.lwjgl.demo.registry.Registries;
import org.a4z0.lwjgl.demo.resource.Key;
import org.a4z0.lwjgl.demo.text.TextComponent;

import java.util.UUID;

public final class Level {

    public final ChunkProvider provider;

    private final UUID uuid;
    private final TextComponent name;
    private final long seed;

    /**
    * Construct a {@link Level}.
    *
    * @param uuid UUID.
    * @param name Name.
    * @param seed Seed.
    */

    public Level(UUID uuid, TextComponent name, long seed) {
        this.provider = new LocalChunkProvider(this);
        this.uuid = uuid;
        this.name = name;
        this.seed = seed;
    }

    /**
    * @return the {@link Level}'s Seed.
    */

    public long getSeed() {
        return this.seed;
    }

    /**
    * @return the {@link Level}'s UUID.
    */

    public UUID getUUID() {
        return this.uuid;
    }

    /**
    * @return the {@link Level}'s Name.
    */

    public TextComponent getName() {
        return this.name;
    }

    /**
    * Retrieves a {@link Chunk} at the coordinates.
    *
    * @param x X-Axis.
    * @param z Y-Axis.
    *
    * @return a {@link Chunk}.
    */

    public Chunk getChunkAt(int x, int y, int z) {
        return this.provider.getChunkAt(x, y, z);
    }

    /**
    * Retrieves a {@link Voxel} at the coordinates.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return a {@link Voxel}.
    */

    public Voxel getVoxelAt(int x, int y, int z) {
        return this.getChunkAt(x, y, z).getVoxelAt(x, y, z);
    }

    /**
    * Ticks this Level.
    */

    public void tick() {
        Main.EVENT_BUS.submit(new LevelRenderEvent.Post());
        Registries.SHADER_PROGRAM.get(Key.of("world")).bind();
        Registries.SHADER_PROGRAM.get(Key.of("world")).setUniform4fv("camera_projection_view", Game.CAMERA.getProjectionView());
        this.provider.tick();
        Registries.SHADER_PROGRAM.get(Key.of("world")).unbind();
        Main.EVENT_BUS.submit(new LevelRenderEvent.After());
    }
}