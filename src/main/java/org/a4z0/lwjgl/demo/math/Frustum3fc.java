package org.a4z0.lwjgl.demo.math;

import org.joml.Matrix4f;

public interface Frustum3fc {

    Plane3fc DEFAULT_LEFT_PLANE = new Plane3f(0, 0, -1, 1);
    Plane3fc DEFAULT_RIGHT_PLANE = new Plane3f(0, 0, 1, 1);
    Plane3fc DEFAULT_TOP_PLANE = new Plane3f(1, 0, 0, 1);
    Plane3fc DEFAULT_BOTTOM_PLANE = new Plane3f(-1, 0, 0, 1);
    Plane3fc DEFAULT_NEAR_PLANE = new Plane3f(0, -1, 0, 1);
    Plane3fc DEFAULT_FAR_PLANE = new Plane3f(0, 1, 0, 1);

    /**
    * @return ...
    */

    Plane3fc getLeft();

    /**
    * @return ...
    */

    Plane3fc getRight();

    /**
    * @return ...
    */

    Plane3fc getTop();

    /**
    * @return ...
    */

    Plane3fc getBottom();

    /**
    * @return ...
    */

    Plane3fc getNear();

    /**
    * @return ...
    */

    Plane3fc getFar();

    /**
    * ...
    *
    * @param projectionView ...
    *
    * @return this {@link Frustum3fc}.
    */

    Frustum3fc set(Matrix4f projectionView);

    /**
    * ...
    *
    * @param x ...
    * @param y ...
    * @param z ...
    * @param r ...
    *
    * @return ...
    */

    boolean testPoint(float x, float y, float z, float r);
}