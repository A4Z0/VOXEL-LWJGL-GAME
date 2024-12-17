package org.a4z0.lwjgl.demo.math.position;

public final class ChunkLayerPosition implements Position3ic {

    private final int x, y, z;

    /**
    * Construct a {@link ChunkLayerPosition}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    public ChunkLayerPosition(int x, int y, int z) {
        this.x = x /*>> 4*/;
        this.y = y /*>> 4*/;
        this.z = z /*>> 4*/;
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

    @Override
    public boolean equals(Object o) {
        return (o instanceof ChunkLayerPosition) && ((ChunkLayerPosition) o).x == this.x && ((ChunkLayerPosition) o).y == this.y && ((ChunkLayerPosition) o).z == this.z;
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
}