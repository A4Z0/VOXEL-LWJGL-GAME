package org.a4z0.lwjgl.demo.voxel;

import org.a4z0.lwjgl.demo.color.Color;
import org.a4z0.lwjgl.demo.chunk.Chunk;

public final class Voxel implements IVoxel {

    private final Chunk chunk;
    private final int[] palette;
    private final int x, y, z;

    /**
    * Construct a {@link Voxel}.
    *
    * @param chunk Chunk.
    * @param palette Palette.
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    Voxel(Chunk chunk, int[] palette, int x, int y, int z) {
        this.chunk = chunk;
        this.palette = palette;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public VoxelPosition getPosition() {
        return new VoxelPosition(this.x, this.y, this.z);
    }

    @Override
    public int getColor() {
        return this.palette[this.getPosition().getIndex()];
    }

    @Override
    public void setColor(byte r, byte g, byte b, byte a) {
        this.palette[this.getPosition().getIndex()] = new Color(r, g, b, a).asARGB();
    }

    @Override
    public IVoxel getNorth() {
        return this.chunk.getVoxelAt(this.x, this.y, this.z - 1);
    }

    @Override
    public IVoxel getSouth() {
        return this.chunk.getVoxelAt(this.x, this.y, this.z + 1);
    }

    @Override
    public IVoxel getEast() {
        return this.chunk.getVoxelAt(this.x + 1, this.y, this.z);
    }

    @Override
    public IVoxel getWest() {
        return this.chunk.getVoxelAt(this.x - 1, this.y, this.z);
    }

    @Override
    public IVoxel getTop() {
        return this.chunk.getVoxelAt(this.x, this.y + 1, this.z);
    }

    @Override
    public IVoxel getBottom() {
        return this.chunk.getVoxelAt(this.x, this.y - 1, this.z);
    }

    @Override
    public String toString() {
        return "PaletteVoxel{"
            + "\"Position\": " + this.getPosition()
            + ", "
            + "\"Color\": " + Color.argb(this.getColor())
        + "}";
    }
}