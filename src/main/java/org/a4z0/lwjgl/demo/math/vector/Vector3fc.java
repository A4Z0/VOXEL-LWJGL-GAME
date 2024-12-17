package org.a4z0.lwjgl.demo.math.vector;

public interface Vector3fc extends Cloneable {

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
    * @return the {@link Vector3fc} length.
    */

    default float length() {
        return this.getX() * this.getX() + this.getY() * this.getY() + this.getZ() * this.getZ();
    }

    /**
    * Sets this {@link Vector3fc} by another.
    *
    * @param o Other {@link Vector3fc}.
    *
    * @return this {@link Vector3fc}.
    */

    Vector3fc set(Vector3fc o);

    /**
    * Sets the axes by other axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return this {@link Vector3f}.
    */

    Vector3fc set(float x, float y, float z);

    /**
    * Adds this {@link Vector3f} by another.
    *
    * @param o Other {@link Vector3f}.
    *
    * @return this {@link Vector3f}.
    */

    Vector3fc add(Vector3fc o);

    /**
    * Adds the axes by other axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return this {@link Vector3fc}.
    */

    Vector3fc add(float x, float y, float z);

    /**
    * Subtracts this {@link Vector3fc} by another.
    *
    * @param o Other {@link Vector3fc}.
    *
    * @return this {@link Vector3fc}.
    */

    Vector3fc subtract(Vector3fc o);

    /**
    * Subtracts the axes by other axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return this {@link Vector3fc}.
    */

    Vector3fc subtract(float x, float y, float z);

    /**
    * Performs a multiplication, multiplies all axes by a multiplier.
    *
    * @param multiplier Multiplier.
    *
    * @return this {@link Vector3fc}.
    */

    Vector3fc multiply(float multiplier);

    /**
    * Performs a multiplication, multiplies all axes by a {@link Vector3fc}.
    *
    * @param o {@link Vector3fc} to multiply.
    *
    * @return this {@link Vector3fc}.
    */

    Vector3fc multiply(Vector3fc o);

    /**
    * Performs a multiplication, multiplies all axes by another axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return this {@link Vector3fc}.
    */

    Vector3fc multiply(float x, float y, float z);

    /**
    * Performs a division, divides all axes by a divisor.
    *
    * @param divisor Divisor.
    *
    * @return this {@link Vector3fc}.
    */

    Vector3fc divide(float divisor);

    /**
    * Performs a division by dividing all axes by a {@link Vector3fc}.
    *
    * @param o {@link Vector3fc} to divide.
    *
    * @return this {@link Vector3fc}.
    */

    Vector3fc divide(Vector3fc o);

    /**
    * Performs a division by dividing all axes by other axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return this {@link Vector3fc}.
    */

    Vector3fc divide(float x, float y, float z);

    /**
    * Calculates the Dot product of this {@link Vector3fc} and the other.
    *
    * @param o Other {@link Vector3fc}.
    *
    * @return the Dot product.
    */

    default float dot(Vector3fc o) {
        return this.dot(o.getX(), o.getY(), o.getZ());
    }

    /**
    * Calculates the Dot product of this {@link Vector3fc} by the axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return the Dot product.
    */

    float dot(float x, float y, float z);

    /**
    * Normalizes this {@link Vector3fc}.
    *
    * @return this {@link Vector3fc}.
    */

    Vector3fc normalize();

    /**
    * Sets all axes to zero.
    *
    * @return this {@link Vector3fc}.
    */

    Vector3fc zero();

    /**
    * Calculates the distance between this {@link Vector3fc} and the other.
    *
    * @param o Other {@link Vector3fc}.
    *
    * @return the distance between the axes.
    */

    default float distance(Vector3fc o) {
        return this.distance(o.getX(), o.getY(), o.getZ());
    }

    /**
    * Calculates the distance between this {@link Vector3fc} and the axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return the distance between the axes.
    */

    float distance(float x, float y, float z);

    /**
    * Checks if this {@link Vector3fc} is equals to the given {@link Object}.
    *
    * @param o {@link Object} to be compared.
    *
    * @return true if equal, false otherwise.
    */

    boolean equals(Object o);

    /**
    * @return a clone of this {@link Vector3fc}.
    */

    Vector3fc clone();
}