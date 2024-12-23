package org.a4z0.lwjgl.demo.voxel;

import org.a4z0.lwjgl.demo.level.chunk.Chunk;

public interface IVoxel {

    /**
    * Represents an Empty Voxel.
    */

    IVoxel EMPTY = new IVoxel() {

        @Override
        public int getColor() {
            return 0;
        }

        @Override
        public void setColor(byte r, byte g, byte b, byte a) {}

        @Override
        public IVoxel getNorth() {
            return EMPTY;
        }

        @Override
        public IVoxel getSouth() {
            return EMPTY;
        }

        @Override
        public IVoxel getEast() {
            return EMPTY;
        }

        @Override
        public IVoxel getWest() {
            return EMPTY;
        }

        @Override
        public IVoxel getTop() {
            return EMPTY;
        }

        @Override
        public IVoxel getBottom() {
            return EMPTY;
        }

        @Override
        public void consume(FaceConsumer consumer) {

        }

        @Override
        public String toString() {
            return "EmptyVoxel{}";
        }
    };

    /**
    * @return the {@link IVoxel}'s Color.
    */

    int getColor();

    /**
    * Sets the Color of this {@link IVoxel}.
    *
    * @param r Red.
    * @param g Green.
    * @param b Blue.
    */

    default void setColor(int r, int g, int b) {
        this.setColor((byte) r, (byte) g, (byte) b);
    }

    /**
    * Sets the Color of this {@link IVoxel}.
    *
    * @param r Red.
    * @param g Green.
    * @param b Blue.
    */

    default void setColor(byte r, byte g, byte b) {
        this.setColor(r, g, b, (byte) 255);
    }

    /**
    * Sets the Color of this {@link IVoxel}.
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
    * Sets the Color of this {@link IVoxel}.
    *
    * @param r Red.
    * @param g Green.
    * @param b Blue.
    * @param a Alpha.
    */

    void setColor(byte r, byte g, byte b, byte a);

    /**
    * @return the {@link IVoxel} to the North of this one.
    */

    IVoxel getNorth();

    /**
    * @return the {@link IVoxel} to the South of this one.
    */

    IVoxel getSouth();

    /**
    * @return the {@link IVoxel} to the East of this one.
    */

    IVoxel getEast();

    /**
    * @return the {@link IVoxel} to the West of this one.
    */

    IVoxel getWest();

    /**
    * @return the {@link IVoxel} to the Top of this one.
    */

    IVoxel getTop();

    /**
    * @return the {@link IVoxel} to the Bottom of this one.
    */

    IVoxel getBottom();

    /**
     * ..
     *
     * @param consumer ...
     */

    void consume(FaceConsumer consumer);

    /**
    * ...
    *
    * @param chunk ...
    * @param palette ...
    * @param x ...
    * @param y ...
    * @param z ...
    *
    * @return ...
    */

    static IVoxel of(Chunk chunk, int[] palette, int x, int y, int z) {
        return new Voxel(chunk, palette, x, y, z);
    }
}