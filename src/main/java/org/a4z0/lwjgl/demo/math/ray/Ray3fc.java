package org.a4z0.lwjgl.demo.math.ray;

import org.a4z0.lwjgl.demo.math.vector.Vector3fc;

public interface Ray3fc {

    /**
    * @return the Origin.
    */

    Vector3fc getOrigin();

    /**
    * @return the Direction.
    */

    Vector3fc getDirection();

    /**
    * ...
    *
    * @param origin Origin.
    * @param direction Direction.
    *
    * @return ...
    */

    Ray3fc set(Vector3fc origin, Vector3fc direction);

    /**
    * ...
    *
    * @param x ...
    * @param y ...
    * @param z ...
    * @param dx ...
    * @param dy ...
    * @param dz ...
    *
    * @return ...
    */

    Ray3fc set(float x, float y, float z, float dx, float dy, float dz);

    /**
    * Checks if this {@link Ray3fc} is equals to the given {@link Object}.
    *
    * @param o {@link Object} to be compared.
    *
    * @return true if equal, false otherwise.
    */

    boolean equals(Object o);

    /**
    * @return a clone of this {@link Ray3fc}.
    */

    Ray3fc clone();
}