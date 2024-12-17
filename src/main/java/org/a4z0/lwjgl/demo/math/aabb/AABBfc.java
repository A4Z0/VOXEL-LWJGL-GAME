package org.a4z0.lwjgl.demo.math.aabb;

import org.a4z0.lwjgl.demo.math.vector.Vector3fc;

public interface AABBfc extends Cloneable {

    /**
    * @return the {@link Vector3fc} of the Lower Northeast Corner.
    */

    Vector3fc getUpperSE();

    /**
    * @return the {@link Vector3fc} of the Upper Southwest Corner.
    */

    Vector3fc getLowerSW();

    /**
    * @return a {@link Vector3fc} corresponding to the center.
    */

    Vector3fc getCenter();

    /**
    * @return the minimum X-Axis.
    */

    float getLowerX();

    /**
    * @return the minimum Y-Axis.
    */

    float getLowerY();

    /**
    * @return the minimum Z-Axis.
    */

    float getLowerZ();

    /**
    * @return the maximum X-Axis.
    */

    float getUpperX();

    /**
    * @return the maximum Y-Axis.
    */

    float getUpperY();

    /**
    * @return the maximum Z-Axis.
    */

    float getUpperZ();

    /**
    * Sets the axes by a {@link Vector3fc}.
    *
    * @param v3f {@link Vector3fc} to set.
    *
    * @return this {@link AABBfc}.
    */

    AABBfc set(Vector3fc v3f);

    /**
    * Sets the axes by other axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return this {@link AABBfc}.
    */

    AABBfc set(float x, float y, float z);

    /**
    * Sets the axes by a minimum and maximum {@link Vector3fc}.
    *
    * @param minimum Lower Northeast Corner.
    * @param maximum Upper Southwest Corner.
    *
    * @return this {@link AABBfc}.
    */

    AABBfc set(Vector3fc minimum, Vector3fc maximum);

    /**
    * Sets the axes by other axes.
    *
    * @param x1 Minimum X-Axis.
    * @param y1 Minimum Y-Axis.
    * @param z1 Minimum Z-Axis.
    * @param x2 Maximum X-Axis.
    * @param y2 Maximum Y-Axis.
    * @param z2 Maximum Z-Axis.
    *
    * @return this {@link AABBfc}.
    */

    AABBfc set(float x1, float y1, float z1, float x2, float y2, float z2);

    /**
    * Adds the axes by a {@link Vector3fc}.
    *
    * @param v3f {@link Vector3fc} to add.
    *
    * @return this {@link AABBfc}.
    */

    AABBfc add(Vector3fc v3f);

    /**
    * Adds the axes by other axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return this {@link AABBfc}.
    */

    AABBfc add(float x, float y, float z);

    /**
    * Adds the axes by a minimum and maximum {@link Vector3fc}.
    *
    * @param minimum Lower Northeast Corner.
    * @param maximum Upper Southwest Corner.
    *
    * @return this {@link AABBfc}.
    */

    AABBfc add(Vector3fc minimum, Vector3fc maximum);

    /**
    * Adds the axes by other axes.
    *
    * @param x1 Minimum X-Axis.
    * @param y1 Minimum Y-Axis.
    * @param z1 Minimum Z-Axis.
    * @param x2 Maximum X-Axis.
    * @param y2 Maximum Y-Axis.
    * @param z2 Maximum Z-Axis.
    *
    * @return this {@link AABBfc}.
    */

    AABBfc add(float x1, float y1, float z1, float x2, float y2, float z2);

    /**
    * Subtracts the axes by a {@link Vector3fc}.
    *
    * @param vf3 {@link Vector3fc} to subtract.
    *
    * @return this {@link AABBfc}.
    */

    AABBfc subtract(Vector3fc vf3);

    /**
    * Subtracts the axes by other axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return this {@link AABBfc}.
    */

    AABBfc subtract(float x, float y, float z);

    /**
    * Subtracts the axes by a minimum and maximum {@link Vector3fc}.
    *
    * @param minimum Lower Northeast Corner.
    * @param maximum Upper Southwest Corner.
    *
    * @return this {@link AABBfc}.
    */

    AABBfc subtract(Vector3fc minimum, Vector3fc maximum);

    /**
    * Subtracts the axes by other axes.
    *
    * @param x1 Minimum X-Axis.
    * @param y1 Minimum Y-Axis.
    * @param z1 Minimum Z-Axis.
    * @param x2 Maximum X-Axis.
    * @param y2 Maximum Y-Axis.
    * @param z2 Maximum Z-Axis.
    *
    * @return this {@link AABBfc}.
    */

    AABBfc subtract(float x1, float y1, float z1, float x2, float y2, float z2);

    /**
    * Performs a multiplication, multiplies all axes by a scalar.
    *
    * @param scalar Scalar.
    *
    * @return this {@link AABBfc}.
    */

    AABBfc multiply(float scalar);

    /**
    * Performs a multiplication, multiplies all axes by a {@link Vector3fc}.
    *
    * @param v3f {@link Vector3fc} to multiply.
    *
    * @return this {@link AABBfc}.
    */

    AABBfc multiply(Vector3fc v3f);

    /**
    * Performs a multiplication, multiplies all axes by another axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return this {@link AABBfc}.
    */

    AABBfc multiply(float x, float y, float z);

    /**
    * Performs a multiplication, multiplies all axes by a minimum and maximum {@link Vector3fc}.
    *
    * @param minimum Lower Northeast Corner.
    * @param maximum Upper Southwest Corner.
    *
    * @return this {@link AABBfc}.
    */

    AABBfc multiply(Vector3fc minimum, Vector3fc maximum);

    /**
    * Performs a multiplication, multiplies all axes by another axes.
    *
    * @param x1 Minimum X-Axis.
    * @param y1 Minimum Y-Axis.
    * @param z1 Minimum Z-Axis.
    * @param x2 Maximum X-Axis.
    * @param y2 Maximum Y-Axis.
    * @param z2 Maximum Z-Axis.
    *
    * @return this {@link AABBfc}.
    */

    AABBfc multiply(float x1, float y1, float z1, float x2, float y2, float z2);

    /**
    * Performs a division, divides all axes by a divisor.
    *
    * @param divisor Divisor.
    *
    * @return this {@link AABBfc}.
    */

    AABBfc divide(float divisor);

    /**
    * Performs a division by dividing all axes by a {@link Vector3fc}.
    *
    * @param v3f {@link Vector3fc} to divide.
    *
    * @return this {@link AABBic}.
    */

    AABBfc divide(Vector3fc v3f);

    /**
    * Performs a division by dividing all axes by other axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return this {@link AABBfc}.
    */

    AABBfc divide(float x, float y, float z);

    /**
    * Performs a division by dividing all axes by a minimum and maximum {@link Vector3fc}.
    *
    * @param minimum Lower Northeast Corner.
    * @param maximum Upper Southwest Corner.
    *
    * @return this {@link AABBic}.
    */

    AABBfc divide(Vector3fc minimum, Vector3fc maximum);

    /**
    * Performs a division by dividing all axes by other axes.
    *
    * @param x1 Minimum X-Axis.
    * @param y1 Minimum Y-Axis.
    * @param z1 Minimum Z-Axis.
    * @param x2 Maximum X-Axis.
    * @param y2 Maximum Y-Axis.
    * @param z2 Maximum Z-Axis.
    *
    * @return this {@link AABBfc}.
    */

    AABBfc divide(float x1, float y1, float z1, float x2, float y2, float z2);

    /**
    * Checks if this {@link AABBic} intersects a Point.
    *
    * @param v3f {@link Vector3fc} that represents a Point.
    *
    * @return true if it intersects, false otherwise.
    */

    default boolean intersects(Vector3fc v3f) {
        return this.intersects(v3f.getX(), v3f.getY(), v3f.getZ());
    }

    /**
    * Checks if this {@link AABBfc} intersects a Point.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return true if it intersects, false otherwise.
    */

    boolean intersects(float x, float y, float z);

    /**
    * Checks if this {@link AABBfc} intersects an {@link AABBic}
    *
    * @param o {@link AABBic} to be checked.
    *
    * @return true if it intersects, false otherwise.
    */

    default boolean intersects(AABBic o) {
        return this.intersects(o.getLowerX(), o.getLowerY(), o.getLowerZ(), o.getUpperX(), o.getUpperY(), o.getUpperZ());
    }

    /**
    * Checks if this {@link AABBfc} intersects the axes.
    *
    * @param x1 Minimum X-Axis.
    * @param y1 Minimum Y-Axis.
    * @param z1 Minimum Z-Axis.
    * @param x2 Maximum X-Axis.
    * @param y2 Maximum Y-Axis.
    * @param z2 Maximum Z-Axis.
    *
    * @return true if it intersects, false otherwise.
    */

    boolean intersects(int x1, int y1, int z1, int x2, int y2, int z2);

    /**
    * Checks if this {@link AABBfc} intersects the other.
    *
    * @param o Other {@link AABBfc}.
    *
    * @return true if it intersects, false otherwise.
    */

    default boolean intersects(AABBfc o) {
        return this.intersects(o.getLowerX(), o.getLowerY(), o.getLowerZ(), o.getUpperX(), o.getUpperY(), o.getUpperZ());
    }

    /**
    * Checks if this {@link AABBfc} intersects the axes.
    *
    * @param x1 Minimum X-Axis.
    * @param y1 Minimum Y-Axis.
    * @param z1 Minimum Z-Axis.
    * @param x2 Maximum X-Axis.
    * @param y2 Maximum Y-Axis.
    * @param z2 Maximum Z-Axis.
    *
    * @return true if it intersects, false otherwise.
    */

    boolean intersects(float x1, float y1, float z1, float x2, float y2, float z2);

    /**
    * Checks if this {@link AABBfc} is equals to the given {@link Object}.
    *
    * @param o {@link Object} to be compared.
    *
    * @return true if equal, false otherwise.
    */

    boolean equals(Object o);

    /**
    * @return a clone of this {@link AABBfc}.
    */

    AABBfc clone();
}