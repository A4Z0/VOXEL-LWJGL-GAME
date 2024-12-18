package org.a4z0.lwjgl.demo.level.chunk.layer.voxel;

import org.a4z0.lwjgl.demo.level.chunk.Chunk;
import org.a4z0.lwjgl.demo.util.Color;

public interface Voxel {

    /**
    * @return the {@link Voxel}'s {@link VoxelPosition Position}.
    */

    VoxelPosition getPosition();

    /**
    * @return the {@link Voxel}'s Color.
    */

    int getColor();

    /**
    * Sets the Color of this {@link Voxel}.
    *
    * @param r Red.
    * @param g Green.
    * @param b Blue.
    */

    default void setColor(int r, int g, int b) {
        this.setColor((byte) r, (byte) g, (byte) b);
    }

    /**
    * Sets the Color of this {@link Voxel}.
    *
    * @param r Red.
    * @param g Green.
    * @param b Blue.
    */

    default void setColor(byte r, byte g, byte b) {
        this.setColor(r, g, b, (byte) 255);
    }

    /**
    * Sets the Color of this {@link Voxel}.
    *
    * @param r Red.
    * @param g Green.
    * @param b Blue.
    * @param a Alpha.
    */

    default void setColor(int r, int g, int b, int a) {
        this.setColor((byte) r, (byte) g, (byte) b, (byte) a);
    }

    /**
    * Sets the Color of this {@link Voxel}.
    *
    * @param r Red.
    * @param g Green.
    * @param b Blue.
    * @param a Alpha.
    */

    void setColor(byte r, byte g, byte b, byte a);

    /**
    * @return the {@link Voxel} to the North of this one.
    */

    Voxel getNorth();

    /**
    * @return the {@link Voxel} to the South of this one.
    */

    Voxel getSouth();

    /**
    * @return the {@link Voxel} to the East of this one.
    */

    Voxel getEast();

    /**
    * @return the {@link Voxel} to the West of this one.
    */

    Voxel getWest();

    /**
    * @return the {@link Voxel} to the Top of this one.
    */

    Voxel getTop();

    /**
    * @return the {@link Voxel} to the Bottom of this one.
    */

    Voxel getBottom();

    /**
    * ...
    *
    * @param chunk ...
    * @param Palette ...
    * @param x ...
    * @param y ...
    * @param z ...
    *
    * @return ...
    */

    static Voxel of(Chunk chunk, int[] Palette, int x, int y, int z) {
        return new Voxel() {

            @Override
            public VoxelPosition getPosition() {
                return new VoxelPosition(x, y, z);
            }

            @Override
            public int getColor() {
                if((x < 0 || x >= Chunk.CHUNK_SIZE_X) || (y < 0 || y >= Chunk.CHUNK_SIZE_Y) || (z < 0 || z >= Chunk.CHUNK_SIZE_Z))
                    return 0;

                return Palette[this.getPosition().getIndex()];
            }

            @Override
            public void setColor(byte r, byte g, byte b, byte a) {
                if((x < 0 || x >= Chunk.CHUNK_SIZE_X) || (y < 0 || y >= Chunk.CHUNK_SIZE_Y) || (z < 0 || z >= Chunk.CHUNK_SIZE_Z))
                    return;

                Palette[this.getPosition().getIndex()] = new Color(r, g, b, a).asARGB();
            }

            @Override
            public Voxel getNorth() {
                return chunk.getVoxelAt(x, y, z - 1);
            }

            @Override
            public Voxel getSouth() {
                return chunk.getVoxelAt(x, y, z + 1);
            }

            @Override
            public Voxel getEast() {
                return chunk.getVoxelAt(x + 1, y, z);
            }

            @Override
            public Voxel getWest() {
                return chunk.getVoxelAt(x - 1, y, z);
            }

            @Override
            public Voxel getTop() {
                return chunk.getVoxelAt(x, y + 1, z);
            }

            @Override
            public Voxel getBottom() {
                return chunk.getVoxelAt(x, y - 1, z);
            }

            @Override
            public String toString() {
                return "Voxel{\"Position\":" + this.getPosition() + ", \"Color\":" + Color.of(this.getColor()) + "}";
            }
        };
    }
}