package org.a4z0.lwjgl.demo.voxel.position;

import org.a4z0.lwjgl.demo.voxel.chunk.Chunk;

/**
* Represents a Chunk Position.
*/

public class ChunkPosition {

    protected final int x;
    protected final int z;

    /**
    * Construct a {@link ChunkPosition}.
    *
    * @param x X-Axis.
    * @param z Z-Axis.
    */

    public ChunkPosition(int x, int z) {
        this.x = (x >> Chunk.CHUNK_SIZE_X_BIT_SHIFT) * Chunk.CHUNK_SIZE_X;
        this.z = (z >> Chunk.CHUNK_SIZE_Z_BIT_SHIFT) * Chunk.CHUNK_SIZE_Z;
    }

    /**
    * @return the X-Axis.
    */

    public int getX() {
        return this.x;
    }

    /**
    * @return the Z-Axis.
    */

    public int getZ() {
        return this.z;
    }

    @Deprecated
    public static long getIndex(int x, int z) {
        return (long) x & 4294967295L | ((long) z & 4294967295L) << 32;
    }

    public static long asBlock(int x, int z) {
        return getIndex(x >> Chunk.CHUNK_SIZE_X_BIT_SHIFT, z >> Chunk.CHUNK_SIZE_Z_BIT_SHIFT);
    }
}