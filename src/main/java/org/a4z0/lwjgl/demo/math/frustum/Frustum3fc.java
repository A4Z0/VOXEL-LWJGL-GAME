package org.a4z0.lwjgl.demo.math.frustum;

import org.a4z0.lwjgl.demo.math.plane.Plane3f;
import org.a4z0.lwjgl.demo.math.plane.Plane3fc;
import org.a4z0.lwjgl.demo.math.vector.Vector3ic;
import org.joml.Matrix4f;

public interface Frustum3fc {

    Plane3fc DEFAULT_LEFT_PLANE = new Plane3f(0, 0, -1, 1);
    Plane3fc DEFAULT_RIGHT_PLANE = new Plane3f(0, 0, 1, 1);
    Plane3fc DEFAULT_TOP_PLANE = new Plane3f(1, 0, 0, 1);
    Plane3fc DEFAULT_BOTTOM_PLANE = new Plane3f(-1, 0, 0, 1);
    Plane3fc DEFAULT_NEAR_PLANE = new Plane3f(0, -1, 0, 1);
    Plane3fc DEFAULT_FAR_PLANE = new Plane3f(0, 1, 0, 1);

    /**
    * @return the Left Plane.
    */

    Plane3fc getLeft();

    /**
    * @return the Right Plane.
    */

    Plane3fc getRight();

    /**
    * @return the Top Plane.
    */

    Plane3fc getTop();

    /**
    * @return the Bottom Plane.
    */

    Plane3fc getBottom();

    /**
    * @return the Near Plane.
    */

    Plane3fc getNear();

    /**
    * @return the Far Plane.
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
    * @param v3i ...
    *
    * @return ...
    */

    default boolean testPoint(Vector3ic v3i) {
        return this.testPoint(v3i.getX(), v3i.getY(), v3i.getZ());
    }

    /**
    * ...
    *
    * @param v3i ...
    * @param r ...
    *
    * @return ...
    */

    default boolean testPoint(Vector3ic v3i, float r) {
        return this.testPoint(v3i.getX(), v3i.getY(), v3i.getZ(), r);
    }

    /**
    * ...
    *
    * @param x ...
    * @param y ...
    * @param z ...
    *
    * @return ...
    */

    default boolean testPoint(float x, float y, float z) {
        return this.testPoint(x, y, z, 0);
    }

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