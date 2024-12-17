package org.a4z0.lwjgl.demo.level.chunk;

import org.a4z0.lwjgl.demo.math.position.ChunkLayerPosition;
import org.a4z0.lwjgl.demo.level.chunk.voxel.Voxel;

public final class ChunkLayer_V2 {

    private final Chunk chunk;
    private final ChunkLayerPosition position;

    /**
    * Construct a {@link ChunkLayer_V2}.
    *
    * @param chunk ...
    * @param position ...
    */

    public ChunkLayer_V2(Chunk chunk, ChunkLayerPosition position) {
        this.chunk = chunk;
        this.position = position;
    }

    /**
    * @return the {@link Chunk}.
    */

    public Chunk getChunk() {
        return this.chunk;
    }

    /**
    * @return the {@link ChunkLayerPosition Position}.
    */

    public ChunkLayerPosition getPosition() {
        return this.position;
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
        return this.chunk.getVoxelAt(x, y, z);
    }

    /**
    * ...
    */

    public void render() {

    }
}