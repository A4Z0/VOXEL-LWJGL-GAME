package org.a4z0.lwjgl.demo.voxel.block.voxel;

import org.a4z0.lwjgl.demo.voxel.math.AABBf;
import org.a4z0.lwjgl.demo.voxel.math.AABBfc;

/**
* Represents a Voxel.
*/

public interface Voxel {

    AABBf VOXEL_AABB = new AABBf(0.03125f, 0.03125f, 0.03125f).subtract(0.03125f, 0.03125f, 0.03125f);

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
        return VOXEL_AABB.clone().add(this.getPosition().getX(), this.getPosition().getY(), this.getPosition().getZ());
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

        return this.$().intersects(AABB);
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