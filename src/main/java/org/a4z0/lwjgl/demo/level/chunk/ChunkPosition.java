package org.a4z0.lwjgl.demo.level.chunk;

import org.a4z0.lwjgl.demo.math.position.Position3ic;
import org.a4z0.lwjgl.demo.level.chunk.layer.voxel.VoxelPosition;
import org.a4z0.lwjgl.demo.math.vector.Vector3fc;

import java.util.Objects;

public final class ChunkPosition implements Position3ic {

    private final int x, y, z;

    /**
    * Construct a {@link ChunkPosition}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    private ChunkPosition(float x, float y, float z) {
        this((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z));
    }

    /**
    * Construct a {@link ChunkPosition}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    private ChunkPosition(int x, int y, int z) {
        this.x = x >> 8;
        this.y = y >> 8;
        this.z = z >> 8;
    }

    @Override
    public int getX() {
        return this.x * 16;
    }

    @Override
    public int getY() {
        return this.y * 16;
    }

    @Override
    public int getZ() {
        return this.z * 16;
    }

    /**
    * @return the Relative X-Axis.
    */

    public long getRelativeX() {
        return getRelativeX(this.x);
    }

    /**
    * @return the Relative Y-Axis.
    */

    public long getRelativeY() {
        return getRelativeY(this.y);
    }

    /**
    * @return the Relative Z-Axis.
    */

    public long getRelativeZ() {
        return getRelativeZ(this.z);
    }

    /**
    * @return the Index of this {@link ChunkPosition}.
    */

    public long getIndex() {
        return getIndex(this.x, this.y, this.z);
    }

    /**
    * Checks if this is equals to the given {@link Object}.
    *
    * @param o {@link Object} to be compared.
    *
    * @return true if equal, false otherwise.
    */

    @Override
    public boolean equals(Object o) {
        return (o instanceof ChunkPosition) && ((ChunkPosition) o).x == this.x && ((ChunkPosition) o).y == this.y && ((ChunkPosition) o).z == this.z;
    }

    /**
    * @return this as a {@link String}.
    */

    @Override
    public String toString() {
        return "ChunkPosition{"
            + "\"X\": " + this.getX()
            + ", "
            + "\"Y\": " + this.getY()
            + ", "
            + "\"Z\": " + this.getZ()
        + "}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y, this.z);
    }

    /**
    * Retrieves the Relative X-Axis.
    *
    * @param x X-Axis.
    *
    * @return the Relative X-Axis.
    */

    public static long getRelativeX(int x) {
        return x & 0x1FFFFF;
    }

    /**
    * Retrieves the Relative Y-Axis.
    *
    * @param y Y-Axis.
    *
    * @return the Relative Y-Axis.
    */

    public static long getRelativeY(int y) {
        return y & 0x1FFFFF;
    }

    /**
    * Retrieves the Relative Z-Axis.
    *
    * @param z Z-Axis.
    *
    * @return the Relative Z-Axis.
    */

    public static long getRelativeZ(int z) {
        return z & 0x1FFFFF;
    }

    /**
    * Retrieves the Index.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return the Index.
    */

    public static long getIndex(int x, int y, int z) {
        return getRelativeX(x) | getRelativeY(y) << 21 | getRelativeZ(z) << 42;
    }

    /**
    * Construct a {@link ChunkPosition}.
    *
    * @param v3f Position.
    *
    * @return a new {@link ChunkPosition}.
    */

    public static ChunkPosition ofVoxel(Vector3fc v3f) {
        return ofVoxel(v3f.getX(), v3f.getY(), v3f.getZ());
    }

    /**
    * Construct a {@link ChunkPosition}.
    *
    * @param voxelPosition Position.
    *
    * @return a new {@link ChunkPosition}.
    */

    public static ChunkPosition ofVoxel(VoxelPosition voxelPosition) {
        return ofVoxel(voxelPosition.getX(), voxelPosition.getY(), voxelPosition.getZ());
    }

    /**
    * Construct a {@link ChunkPosition}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return a new {@link ChunkPosition}.
    */

    public static ChunkPosition ofVoxel(float x, float y, float z) {
        return new ChunkPosition(((int) Math.floor(x)), ((int) Math.floor(y)), ((int) Math.floor(z)));
    }

    /**
    * Construct a {@link ChunkPosition}.
    *
    * @param v3f Position.
    *
    * @return a new {@link ChunkPosition}.
    */

    public static ChunkPosition ofEntity(Vector3fc v3f) {
        return ofEntity(v3f.getX(), v3f.getY(), v3f.getZ());
    }

    /**
    * Construct a {@link ChunkPosition}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return a new {@link ChunkPosition}.
    */

    public static ChunkPosition ofEntity(float x, float y, float z) {
        return new ChunkPosition(((int) Math.floor(x)) << 4, ((int) Math.floor(y)) << 4, ((int) Math.floor(z)) << 4);
    }
}