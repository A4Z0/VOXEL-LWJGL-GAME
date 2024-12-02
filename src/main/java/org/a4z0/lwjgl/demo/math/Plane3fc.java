package org.a4z0.lwjgl.demo.math;

public interface Plane3fc {

    /**
    * @return the X-Axis.
    */

    float getX();

    /**
    * @return the Y-Axis.
    */

    float getY();

    /**
    * @return the Z-Axis.
    */

    float getZ();

    /**
    * @return the Distance.
    */

    float getDistance();

    /**
    * ...
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param d Distance.
    *
    * @return this {@link Plane3fc}.
    */

    Plane3fc set(float x, float y, float z, float d);

    /**
    * ...
    *
    * @param divisor ...
    *
    * @return ...
    */

    Plane3fc divide(float divisor);

    /**
    * ...
    *
    * @param x ...
    * @param y ...
    * @param z ...
    * @param d ...
    *
    * @return ...
    */

    Plane3fc divide(float x, float y, float z, float d);

    /**
    * ...
    *
    * @return ...
    */

    Plane3fc normalize();

    /**
    * ...
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return ...
    */

    float testPoint(float x, float y, float z);
}