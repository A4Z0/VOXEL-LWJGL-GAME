package org.a4z0.lwjgl.demo.math.position;

public final class VoxelPosition implements Position3fc {

    public final int x, y, z;

    /**
    * Construct a {@link VoxelPosition}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    public VoxelPosition(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public float getX() {
        return this.x * 0.0625f;
    }

    @Override
    public float getY() {
        return this.y * 0.0625f;
    }

    @Override
    public float getZ() {
        return this.z * 0.0625f;
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
    * @return the Index of this {@link VoxelPosition}.
    */

    public int getIndex() {
        return getIndex(this.x, this.y, this.z);
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof VoxelPosition) && ((VoxelPosition) o).x == this.x && ((VoxelPosition) o).y == this.y && ((VoxelPosition) o).z == this.z;
    }

    /**
    * Retrieves the Relative X-Axis.
    *
    * @param x X-Axis.
    *
    * @return the Relative X-Axis.
    */

    public static int getRelativeX(int x) {
        return x & 0xFF;
    }

    /**
    * Retrieves the Relative Y-Axis.
    *
    * @param y Y-Axis.
    *
    * @return the Relative Y-Axis.
    */

    public static int getRelativeY(int y) {
        return y & 0xFF;
    }

    /**
    * Retrieves the Relative Z-Axis.
    *
    * @param z Z-Axis.
    *
    * @return the Relative Z-Axis.
    */

    public static int getRelativeZ(int z) {
        return z & 0xFF;
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
        return (getRelativeY(y) << 8 | getRelativeZ(z)) << 8 | getRelativeX(x);
    }
}