package org.a4z0.lwjgl.demo.voxel.math;

public interface Position3ic {

    /**
    * @return the Z-Axis.
    */

    int getX();

    /**
    * @return the Y-Axis.
    */

    int getY();

    /**
    * @return the Z-Axis.
    */

    int getZ();

    /**
    * Checks if this {@link Position3ic} is equals to the given {@link Object}, false otherwise.
    *
    * @param o {@link Object} to be checked.
    *
    * @return true if equal, false otherwise.
    */

    boolean equals(Object o);
}