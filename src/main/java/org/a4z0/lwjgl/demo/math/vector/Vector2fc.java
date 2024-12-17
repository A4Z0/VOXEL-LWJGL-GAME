package org.a4z0.lwjgl.demo.math.vector;

public interface Vector2fc extends Cloneable {

    /**
    * @return the X-Axis.
    */

    float getX();

    /**
    * @return the Y-Axis.
    */

    float getY();

    /**
    * @return the {@link Vector2fc} length.
    */

    default float length() {
        return this.getX() * this.getX() + this.getY() * this.getY();
    }

    /**
    * Sets this {@link Vector2fc} by another.
    *
    * @param o Other {@link Vector2fc}.
    *
    * @return this {@link Vector2fc}.
    */

    Vector2fc set(Vector2fc o);

    /**
    * Sets the axes by other axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    *
    * @return this {@link Vector3f}.
    */

    Vector2fc set(float x, float y);

    /**
    * Adds this {@link Vector3f} by another.
    *
    * @param o Other {@link Vector3f}.
    *
    * @return this {@link Vector3f}.
    */

    Vector2fc add(Vector2fc o);

    /**
    * Adds the axes by other axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    *
    * @return this {@link Vector2fc}.
    */

    Vector2fc add(float x, float y);

    /**
    * Subtracts this {@link Vector2fc} by another.
    *
    * @param o Other {@link Vector2fc}.
    *
    * @return this {@link Vector2fc}.
    */

    Vector2fc subtract(Vector2fc o);

    /**
    * Subtracts the axes by other axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    *
    * @return this {@link Vector2fc}.
    */

    Vector2fc subtract(float x, float y);

    /**
    * Performs a multiplication, multiplies all axes by a multiplier.
    *
    * @param multiplier Multiplier.
    *
    * @return this {@link Vector2fc}.
    */

    Vector2fc multiply(float multiplier);

    /**
    * Performs a multiplication, multiplies all axes by a {@link Vector2fc}.
    *
    * @param o {@link Vector2fc} to multiply.
    *
    * @return this {@link Vector2fc}.
    */

    Vector2fc multiply(Vector2fc o);

    /**
    * Performs a multiplication, multiplies all axes by another axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    *
    * @return this {@link Vector2fc}.
    */

    Vector2fc multiply(float x, float y);

    /**
    * Performs a division, divides all axes by a divisor.
    *
    * @param divisor Divisor.
    *
    * @return this {@link Vector2fc}.
    */

    Vector2fc divide(float divisor);

    /**
    * Performs a division by dividing all axes by a {@link Vector2fc}.
    *
    * @param o {@link Vector2fc} to divide.
    *
    * @return this {@link Vector2fc}.
    */

    Vector2fc divide(Vector2fc o);

    /**
    * Performs a division by dividing all axes by other axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    *
    * @return this {@link Vector2fc}.
    */

    Vector2fc divide(float x, float y);

    /**
    * Calculates the Dot product of this {@link Vector2fc} and the other.
    *
    * @param o Other {@link Vector2fc}.
    *
    * @return the Dot product.
    */

    default float dot(Vector2fc o) {
        return this.dot(o.getX(), o.getY());
    }

    /**
    * Calculates the Dot product of this {@link Vector2fc} by the axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    *
    * @return the Dot product.
    */

    float dot(float x, float y);

    /**
    * Normalizes this {@link Vector2fc}.
    *
    * @return this {@link Vector2fc}.
    */

    Vector2fc normalize();

    /**
    * Sets all axes to zero.
    *
    * @return this {@link Vector2fc}.
    */

    Vector2fc zero();

    /**
    * Calculates the distance between this {@link Vector2fc} and the other.
    *
    * @param o Other {@link Vector2fc}.
    *
    * @return the distance between the axes.
    */

    default float distance(Vector2fc o) {
        return this.distance(o.getX(), o.getY());
    }

    /**
    * Calculates the distance between this {@link Vector2fc} and the axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    *
    * @return the distance between the axes.
    */

    float distance(float x, float y);

    /**
    * Checks if this {@link Vector2fc} is equals to the given {@link Object}.
    *
    * @param o {@link Object} to be compared.
    *
    * @return true if equal, false otherwise.
    */

    boolean equals(Object o);

    /**
    * @return a clone of this {@link Vector2fc}.
    */

    Vector2fc clone();
}