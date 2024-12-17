package org.a4z0.lwjgl.demo.math.position;

public final class ChunkPosition implements Position3ic {

    private final int x, y, z;

    /**
    * Construct a {@link ChunkPosition}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    public ChunkPosition(int x, int y, int z) {
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

    @Override
    public boolean equals(Object o) {
        return (o instanceof ChunkPosition) && ((ChunkPosition) o).x == this.x && ((ChunkPosition) o).y == this.y && ((ChunkPosition) o).z == this.z;
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
}