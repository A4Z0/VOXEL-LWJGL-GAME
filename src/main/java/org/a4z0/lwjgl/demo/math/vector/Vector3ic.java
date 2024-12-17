package org.a4z0.lwjgl.demo.math.vector;

public interface Vector3ic extends Cloneable {

    /**
    * @return the X-Axis.
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
    * @return the {@link Vector3ic} length.
    */

    default int length() {
        return this.getX() * this.getX() + this.getY() * this.getY() + this.getZ() * this.getZ();
    }

    /**
    * Sets this {@link Vector3ic} by another.
    *
    * @param o Other {@link Vector3ic}.
    *
    * @return this {@link Vector3ic}.
    */

    Vector3ic set(Vector3ic o);

    /**
    * Sets the axes by other axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return this {@link Vector3i}.
    */

    Vector3ic set(int x, int y, int z);

    /**
    * Adds this {@link Vector3i} by another.
    *
    * @param o Other {@link Vector3i}.
    *
    * @return this {@link Vector3i}.
    */

    Vector3ic add(Vector3ic o);

    /**
    * Adds the axes by other axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return this {@link Vector3ic}.
    */

    Vector3ic add(int x, int y, int z);

    /**
    * Subtracts this {@link Vector3ic} by another.
    *
    * @param o Other {@link Vector3ic}.
    *
    * @return this {@link Vector3ic}.
    */

    Vector3ic subtract(Vector3ic o);

    /**
    * Subtracts the axes by other axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return this {@link Vector3ic}.
    */

    Vector3ic subtract(int x, int y, int z);

    /**
    * Performs a multiplication, multiplies all axes by a multiplier.
    *
    * @param multiplier Multiplier.
    *
    * @return this {@link Vector3ic}.
    */

    Vector3ic multiply(int multiplier);

    /**
    * Performs a multiplication, multiplies all axes by a {@link Vector3fc}.
    *
    * @param o {@link Vector3ic} to multiply.
    *
    * @return this {@link Vector3ic}.
    */

    Vector3ic multiply(Vector3ic o);

    /**
    * Performs a multiplication, multiplies all axes by another axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return this {@link Vector3ic}.
    */

    Vector3ic multiply(int x, int y, int z);

    /**
    * Performs a division, divides all axes by a divisor.
    *
    * @param divisor Divisor.
    *
    * @return this {@link Vector3ic}.
    */

    Vector3ic divide(int divisor);

    /**
    * Performs a division by dividing all axes by a {@link Vector3ic}.
    *
    * @param o {@link Vector3ic} to divide.
    *
    * @return this {@link Vector3ic}.
    */

    Vector3ic divide(Vector3ic o);

    /**
    * Performs a division by dividing all axes by other axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return this {@link Vector3ic}.
    */

    Vector3ic divide(int x, int y, int z);

    /**
    * Calculates the Dot product of this {@link Vector3ic} and the other.
    *
    * @param o Other {@link Vector3ic}.
    *
    * @return the Dot product.
    */

    default int dot(Vector3ic o) {
        return this.dot(o.getX(), o.getY(), o.getZ());
    }

    /**
    * Calculates the Dot product of this {@link Vector3ic} by the axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return the Dot product.
    */

    int dot(int x, int y, int z);

    /**
    * Normalizes this {@link Vector3ic}.
    *
    * @return this {@link Vector3ic}.
    */

    Vector3ic normalize();

    /**
    * Sets all axes to zero.
    *
    * @return this {@link Vector3ic}.
    */

    Vector3ic zero();

    /**
    * Calculates the distance between this {@link Vector3ic} and the other.
    *
    * @param o Other {@link Vector3ic}.
    *
    * @return the distance between the axes.
    */

    default int distance(Vector3ic o) {
        return this.distance(o.getX(), o.getY(), o.getZ());
    }

    /**
    * Calculates the distance between this {@link Vector3ic} and the axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return the distance between the axes.
    */

    int distance(int x, int y, int z);

    /**
    * Checks if this {@link Vector3ic} is equals to the given {@link Object}.
    *
    * @param o {@link Object} to be compared.
    *
    * @return true if equal, false otherwise.
    */

    boolean equals(Object o);

    /**
    * @return a clone of this {@link Vector3ic}.
    */

    Vector3ic clone();
}