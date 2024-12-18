package org.a4z0.lwjgl.demo.level.chunk;

import org.a4z0.lwjgl.demo.level.Level;

public interface ChunkProvider {

    /**
    * @return the Level.
    */

    Level getLevel();

    /**
    * Retrieves a Chunk.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return a {@link Chunk}.
    */

    Chunk getChunkAt(int x, int y, int z);

    /**
    * Loads a Chunk.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    void load(int x, int y, int z);

    /**
    * Loads a Chunk.
    *
    * @param Chunk Chunk.
    */

    void load(Chunk Chunk);

    /**
    * Unloads a Chunk.
    *
    * @param x X-Axis.
    * @param y Y-AXIS.
    * @param z Z-Axis.
    */

    default void unload(int x, int y, int z) {
        this.unload(this.getChunkAt(x, y, z));
    }

    /**
    * Unloads a Chunk.
    *
    * @param Chunk Chunk.
    */

    void unload(Chunk Chunk);

    /**
    * Ticks this Provider.
    */

    void tick();
}