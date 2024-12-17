package org.a4z0.lwjgl.demo.level;

import org.a4z0.lwjgl.demo.level.chunk.Chunk;
import org.a4z0.lwjgl.demo.level.chunk.ChunkProvider_V2;
import org.a4z0.lwjgl.demo.level.chunk.voxel.Voxel;

import java.util.UUID;

public final class Level {

    public final ChunkProvider_V2 provider;
    private final UUID uuid;
    private final String name;
    private final long seed;

    /**
    * Construct a {@link Level}.
    *
    * @param uuid {@link Level}'s {@link UUID}.
    * @param name {@link Level}'s Name.
    * @param seed {@link Level}'s Seed.
    */

    public Level(UUID uuid, String name, long seed) {
        this.provider = new ChunkProvider_V2(this);
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

    public String getName() {
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
        return this.provider.getChunk(x, y, z);
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
    * Ticks this {@link Level}.
    */

    public void tick() {
        this.provider.tick();
    }

    /**
    * Saves this {@link Level}.
    */

    public void save() {
        System.out.println("[Level:" + this.getName() + "]: Saved!");
    }
}