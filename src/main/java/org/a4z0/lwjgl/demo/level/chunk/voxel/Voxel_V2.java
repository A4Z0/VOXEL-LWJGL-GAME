package org.a4z0.lwjgl.demo.level.chunk.voxel;

import org.a4z0.lwjgl.demo.level.chunk.Chunk;
import org.a4z0.lwjgl.demo.math.position.VoxelPosition;

public final class Voxel_V2 {

    private final Chunk Chunk;
    private final VoxelPosition Position;

    /**
    * Construct a {@link Voxel_V2}.
    *
    * @param Chunk ...
    * @param Position ...
    */

    public Voxel_V2(Chunk Chunk, VoxelPosition Position) {
        this.Chunk = Chunk;
        this.Position = Position;
    }

    /**
    * @return ...
    */

    public Chunk getChunk() {
        return this.Chunk;
    }

    /**
    * @return the {@link VoxelPosition Position}.
    */

    public VoxelPosition getPosition() {
        return this.Position;
    }

    /**
    * @return the {@link Voxel} to the North of this one.
    */

    public Voxel getNorth() {
        return this.getChunk().getVoxelAt(this.getPosition().x, this.getPosition().y, this.getPosition().z - 1);
    }

    /**
    * @return the {@link Voxel} to the South of this one.
    */

    public Voxel getSouth() {
        return this.getChunk().getVoxelAt(this.getPosition().x, this.getPosition().y, this.getPosition().z + 1);
    }

    /**
    * @return the {@link Voxel} to the East of this one.
    */

    public Voxel getEast() {
        return this.getChunk().getVoxelAt(this.getPosition().x + 1, this.getPosition().y, this.getPosition().z);
    }

    /**
    * @return the {@link Voxel} to the West of this one.
    */

    public Voxel getWest() {
        return this.getChunk().getVoxelAt(this.getPosition().x - 1, this.getPosition().y, this.getPosition().z);
    }

    /**
    * @return the {@link Voxel} to the Top of this one.
    */

    public Voxel getTop() {
        return this.getChunk().getVoxelAt(this.getPosition().x, this.getPosition().y + 1, this.getPosition().z);
    }

    /**
    * @return the {@link Voxel} to the Bottom of this one.
    */

    public Voxel getBottom() {
        return this.getChunk().getVoxelAt(this.getPosition().x, this.getPosition().y - 1, this.getPosition().z);
    }
}