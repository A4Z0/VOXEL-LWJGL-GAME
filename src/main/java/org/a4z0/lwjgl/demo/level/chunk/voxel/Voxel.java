package org.a4z0.lwjgl.demo.level.chunk.voxel;

import org.a4z0.lwjgl.demo.math.aabb.AABBf;
import org.a4z0.lwjgl.demo.math.aabb.AABBfc;
import org.a4z0.lwjgl.demo.math.position.VoxelPosition;

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

    default AABBf $() {
        return new AABBf(
            this.getPosition().getX(),
            this.getPosition().getY(),
            this.getPosition().getZ(),
            (0.0625f + this.getPosition().getX()),
            (0.0625f + this.getPosition().getY()),
            (0.0625f + this.getPosition().getZ())
        );
    }

    /**
    * ...
    *
    * @param AABB ...
    *
    * @return ...
    */

    default boolean intersects(AABBfc AABB) {
        if(this.getColor() == 0)
            return false;

        return AABB.intersects(this.$());
    }

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
}