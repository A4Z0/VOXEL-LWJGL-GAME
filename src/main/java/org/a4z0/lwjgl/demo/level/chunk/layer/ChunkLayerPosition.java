package org.a4z0.lwjgl.demo.level.chunk.layer;

import org.a4z0.lwjgl.demo.math.position.Position3ic;
import org.a4z0.lwjgl.demo.level.chunk.layer.voxel.VoxelPosition;
import org.a4z0.lwjgl.demo.math.vector.Vector3fc;

import java.util.Objects;

public final class ChunkLayerPosition implements Position3ic {

    private final int x, y, z;

    /**
    * Construct a {@link ChunkLayerPosition}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    private ChunkLayerPosition(float x, float y, float z) {
        this((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z));
    }

    /**
    * Construct a {@link ChunkLayerPosition}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    public ChunkLayerPosition(int x, int y, int z) {
        this.x = x >> 4;
        this.y = y >> 4;
        this.z = z >> 4;
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

    public int getRelativeX() {
        return getRelativeX(this.x);
    }

    /**
    * @return the Relative Y-Axis.
    */

    public int getRelativeY() {
        return getRelativeY(this.y);
    }

    /**
    * @return the Relative Z-Axis.
    */

    public int getRelativeZ() {
        return getRelativeZ(this.z);
    }

    /**
    * @return the Index of this {@link ChunkLayerPosition}.
    */

    public int getIndex() {
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
        return (o instanceof ChunkLayerPosition) && ((ChunkLayerPosition) o).x == this.x && ((ChunkLayerPosition) o).y == this.y && ((ChunkLayerPosition) o).z == this.z;
    }

    /**
    * @return this as a {@link String}.
    */

    @Override
    public String toString() {
        return "ChunkLayerPosition{"
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

    public static int getRelativeX(int x) {
        return x & 15;
    }

    /**
    * Retrieves the Relative Y-Axis.
    *
    * @param y Y-Axis.
    *
    * @return the Relative Y-Axis.
    */

    public static int getRelativeY(int y) {
        return y & 15;
    }

    /**
    * Retrieves the Relative Z-Axis.
    *
    * @param z Z-Axis.
    *
    * @return the Relative Z-Axis.
    */

    public static int getRelativeZ(int z) {
        return z & 15;
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

    public static int getIndex(int x, int y, int z) {
        return (getRelativeY(y) << 4 | getRelativeZ(z)) << 4 | getRelativeX(x);
    }

    /**
    * Construct a {@link ChunkLayerPosition}.
    *
    * @param v3f Position.
    *
    * @return a new {@link ChunkLayerPosition}.
    */

    public static ChunkLayerPosition ofVoxel(Vector3fc v3f) {
        return ofVoxel(v3f.getX(), v3f.getY(), v3f.getZ());
    }

    /**
    * Construct a {@link ChunkLayerPosition}.
    *
    * @param voxelPosition Position.
    *
    * @return a new {@link ChunkLayerPosition}.
    */

    public static ChunkLayerPosition ofVoxel(VoxelPosition voxelPosition) {
        return ofVoxel(voxelPosition.getX(), voxelPosition.getY(), voxelPosition.getZ());
    }

    /**
    * Construct a {@link ChunkLayerPosition}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return a new {@link ChunkLayerPosition}.
    */

    public static ChunkLayerPosition ofVoxel(float x, float y, float z) {
        return new ChunkLayerPosition(((int) Math.floor(x)), ((int) Math.floor(y)), ((int) Math.floor(z)));
    }

    /**
   * Construct a {@link ChunkLayerPosition}.
   *
   * @param v3f Position.
   *
   * @return a new {@link ChunkLayerPosition}.
   */

    public static ChunkLayerPosition ofEntity(Vector3fc v3f) {
        return ofEntity(v3f.getX(), v3f.getY(), v3f.getZ());
    }

    /**
    * Construct a {@link ChunkLayerPosition}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return a new {@link ChunkLayerPosition}.
    */

    public static ChunkLayerPosition ofEntity(float x, float y, float z) {
        return new ChunkLayerPosition(((int) Math.floor(x)) << 4, ((int) Math.floor(y)) << 4, ((int) Math.floor(z)) << 4);
    }
}