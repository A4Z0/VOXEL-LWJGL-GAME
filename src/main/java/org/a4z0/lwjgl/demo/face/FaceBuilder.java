package org.a4z0.lwjgl.demo.face;

import org.a4z0.lwjgl.demo.level.Direction;

@FunctionalInterface
public interface FaceBuilder {

    /**
    * ...
    *
    * @param Direction ...
    * @param x ...
    * @param y ...
    * @param z ...
    */

    void build(Direction Direction, float x, float y, float z);
}