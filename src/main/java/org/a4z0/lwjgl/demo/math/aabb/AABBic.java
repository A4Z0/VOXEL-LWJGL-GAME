package org.a4z0.lwjgl.demo.math.aabb;

import org.a4z0.lwjgl.demo.math.vector.Vector3ic;

public interface AABBic extends Cloneable {

    /**
    * @return the {@link Vector3ic} of the Lower Northeast Corner.
    */

    Vector3ic getUpperSE();

    /**
    * @return the {@link Vector3ic} of the Upper Southwest Corner.
    */

    Vector3ic getLowerSW();

    /**
    * @return a {@link Vector3ic} corresponding to the center.
    */

    Vector3ic getCenter();

    /**
    * @return the minimum X-Axis.
    */

    int getLowerX();

    /**
    * @return the minimum Y-Axis.
    */

    int getLowerY();

    /**
    * @return the minimum Z-Axis.
    */

    int getLowerZ();

    /**
    * @return the maximum X-Axis.
    */

    int getUpperX();

    /**
    * @return the maximum Y-Axis.
    */

    int getUpperY();

    /**
    * @return the maximum Z-Axis.
    */

    int getUpperZ();

    /**
    * Sets the axes by a {@link Vector3ic}.
    *
    * @param v3i {@link Vector3ic} to set.
    *
    * @return this {@link AABBic}.
    */

    AABBic set(Vector3ic v3i);

    /**
    * Sets the axes by other axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return this {@link AABBic}.
    */

    AABBic set(int x, int y, int z);

    /**
    * Sets the axes by a minimum and maximum {@link Vector3ic}.
    *
    * @param minimum Lower Northeast Corner.
    * @param maximum Upper Southwest Corner.
    *
    * @return this {@link AABBic}.
    */

    AABBic set(Vector3ic minimum, Vector3ic maximum);

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
    * @return this {@link AABBic}.
    */

    AABBic set(int x1, int y1, int z1, int x2, int y2, int z2);

    /**
    * Adds the axes by a {@link Vector3ic}.
    *
    * @param v3i {@link Vector3ic} to add.
    *
    * @return this {@link AABBic}.
    */

    AABBic add(Vector3ic v3i);

    /**
    * Adds the axes by other axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return this {@link AABBic}.
    */

    AABBic add(int x, int y, int z);

    /**
    * Adds the axes by a minimum and maximum {@link Vector3ic}.
    *
    * @param minimum Lower Northeast Corner.
    * @param maximum Upper Southwest Corner.
    *
    * @return this {@link AABBic}.
    */

    AABBic add(Vector3ic minimum, Vector3ic maximum);

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
    * @return this {@link AABBic}.
    */

    AABBic add(int x1, int y1, int z1, int x2, int y2, int z2);

    /**
    * Subtracts the axes by a {@link Vector3ic}.
    *
    * @param v3i {@link Vector3ic} to subtract.
    *
    * @return this {@link AABBic}.
    */

    AABBic subtract(Vector3ic v3i);

    /**
    * Subtracts the axes by other axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return this {@link AABBic}.
    */

    AABBic subtract(int x, int y, int z);

    /**
    * Subtracts the axes by a minimum and maximum {@link Vector3ic}.
    *
    * @param minimum Lower Northeast Corner.
    * @param maximum Upper Southwest Corner.
    *
    * @return this {@link AABBic}.
    */

    AABBic subtract(Vector3ic minimum, Vector3ic maximum);

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
    * @return this {@link AABBic}.
    */

    AABBic subtract(int x1, int y1, int z1, int x2, int y2, int z2);

    /**
    * Performs a multiplication, multiplies all axes by a scalar.
    *
    * @param scalar Scalar.
    *
    * @return this {@link AABBic}.
    */

    AABBic multiply(int scalar);

    /**
    * Performs a multiplication, multiplies all axes by a {@link Vector3ic}.
    *
    * @param v3i {@link Vector3ic} to multiply.
    *
    * @return this {@link AABBic}.
    */

    AABBic multiply(Vector3ic v3i);

    /**
    * Performs a multiplication, multiplies all axes by another axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return this {@link AABBic}.
    */

    AABBic multiply(int x, int y, int z);

    /**
    * Performs a multiplication, multiplies all axes by a minimum and maximum {@link Vector3ic}.
    *
    * @param minimum Lower Northeast Corner.
    * @param maximum Upper Southwest Corner.
    *
    * @return this {@link AABBic}.
    */

    AABBic multiply(Vector3ic minimum, Vector3ic maximum);

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
    * @return this {@link AABBic}.
    */

    AABBic multiply(int x1, int y1, int z1, int x2, int y2, int z2);

    /**
    * Performs a division, divides all axes by a divisor.
    *
    * @param divisor Divisor.
    *
    * @return this {@link AABBic}.
    */

    AABBic divide(int divisor);

    /**
    * Performs a division by dividing all axes by a {@link Vector3ic}.
    *
    * @param v3i {@link Vector3ic} to divide.
    *
    * @return this {@link AABBic}.
    */

    AABBic divide(Vector3ic v3i);

    /**
    * Performs a division by dividing all axes by other axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return this {@link AABBic}.
    */

    AABBic divide(int x, int y, int z);

    /**
    * Performs a division by dividing all axes by a minimum and maximum {@link Vector3ic}.
    *
    * @param minimum Lower Northeast Corner.
    * @param maximum Upper Southwest Corner.
    *
    * @return this {@link AABBic}.
    */

    AABBic divide(Vector3ic minimum, Vector3ic maximum);

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
    * @return this {@link AABBic}.
    */

    AABBic divide(int x1, int y1, int z1, int x2, int y2, int z2);

    /**
    * Checks if this {@link AABBic} intersects a Point.
    *
    * @param v3i {@link Vector3ic} that represents a Point.
    *
    * @return true if it intersects, false otherwise.
    */

    default boolean intersects(Vector3ic v3i) {
        return this.intersects(v3i.getX(), v3i.getY(), v3i.getZ());
    }

    /**
    * Checks if this {@link AABBic} intersects a Point.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return true if it intersects, false otherwise.
    */

    boolean intersects(int x, int y, int z);

    /**
    * Checks if this {@link AABBic} intersects the other.
    *
    * @param o Other {@link AABBic}.
    *
    * @return true if it intersects, false otherwise.
    */

    default boolean intersects(AABBic o) {
        return this.intersects(o.getLowerX(), o.getLowerY(), o.getLowerZ(), o.getUpperX(), o.getUpperY(), o.getUpperZ());
    }

    /**
    * Checks if this {@link AABBic} intersects the axes.
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
    * Checks if this {@link AABBic} intersects an {@link AABBfc}
    *
    * @param o {@link AABBfc} to be checked.
    *
    * @return true if it intersects, false otherwise.
    */

    default boolean intersects(AABBfc o) {
        return this.intersects(o.getLowerX(), o.getLowerY(), o.getLowerZ(), o.getUpperX(), o.getUpperY(), o.getUpperZ());
    }

    /**
    * Checks if this {@link AABBic} intersects the axes.
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
    * Checks if this {@link AABBic} is equals to the given {@link Object}.
    *
    * @param o {@link Object} to be compared.
    *
    * @return true if equal, false otherwise.
    */

    boolean equals(Object o);

    /**
    * @return a clone of this {@link AABBic}.
    */

    AABBic clone();
}