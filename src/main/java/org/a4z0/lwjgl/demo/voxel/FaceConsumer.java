package org.a4z0.lwjgl.demo.voxel;

import org.a4z0.lwjgl.demo.level.Direction;

@FunctionalInterface
public interface FaceConsumer {

    /**
    * ...
    *
    * @param direction Direction.
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param argb ARGB.
    */

    void consume(Direction direction, int x, int y, int z, int argb);
}