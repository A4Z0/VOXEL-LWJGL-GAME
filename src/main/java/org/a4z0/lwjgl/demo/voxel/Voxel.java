package org.a4z0.lwjgl.demo.voxel;

import org.a4z0.lwjgl.demo.color.Color;
import org.a4z0.lwjgl.demo.level.Direction;
import org.a4z0.lwjgl.demo.level.chunk.Chunk;

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
    public int getColor() {
        return this.palette[VoxelPosition.getIndex(this.x, this.y, this.z)];
    }

    @Override
    public void setColor(byte r, byte g, byte b, byte a) {
        this.palette[VoxelPosition.getIndex(this.x, this.y, this.z)] = new Color(r, g, b, a).asARGB();
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
    public void consume(FaceConsumer consumer) {
        if(this.getNorth().getColor() == 0)
            consumer.consume(Direction.NORTH, x, y, z, this.getColor());
        if(this.getSouth().getColor() == 0)
            consumer.consume(Direction.SOUTH, x, y, z, this.getColor());
        if(this.getEast().getColor() == 0)
            consumer.consume(Direction.EAST, x, y, z, this.getColor());
        if(this.getWest().getColor() == 0)
            consumer.consume(Direction.WEST, x, y, z, this.getColor());
        if(this.getTop().getColor() == 0)
            consumer.consume(Direction.TOP, x, y, z, this.getColor());
        if(this.getBottom().getColor() == 0)
            consumer.consume(Direction.BOTTOM, x, y, z, this.getColor());
    }
}