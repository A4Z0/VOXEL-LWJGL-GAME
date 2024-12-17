package org.a4z0.lwjgl.demo.math.vector;

public class Vector2f implements Vector2fc {

    protected float x, y;

    /**
    * Construct a {@link Vector2f}.
    */

    public Vector2f() {
        this.zero();
    }

    /**
    * Construct a {@link Vector2f}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    */

    public Vector2f(float x, float y) {
        this.set(x, y);
    }

    @Override
    public float getX() {
        return this.x;
    }

    @Override
    public float getY() {
        return this.y;
    }

    @Override
    public Vector2f set(Vector2fc o) {
        return this.set(o.getX(), o.getY());
    }

    @Override
    public Vector2f set(float x, float y) {
        this.x = x;
        this.y = y;

        return this;
    }

    @Override
    public Vector2f add(Vector2fc o) {
        return this.add(o.getX(), o.getY());
    }

    @Override
    public Vector2f add(float x, float y) {
        this.x += x;
        this.y += y;

        return this;
    }

    @Override
    public Vector2f subtract(Vector2fc o) {
        return this.subtract(o.getX(), o.getY());
    }

    @Override
    public Vector2f subtract(float x, float y) {
        this.x -= x;
        this.y -= y;

        return this;
    }

    @Override
    public Vector2f multiply(float multiplier) {
        return this.multiply(multiplier, multiplier);
    }

    @Override
    public Vector2f multiply(Vector2fc o) {
        return this.multiply(o.getX(), o.getY());
    }

    @Override
    public Vector2f multiply(float x, float y) {
        this.x *= x;
        this.y *= y;

        return this;
    }

    @Override
    public Vector2f divide(float divisor) {
        return this.divide(divisor, divisor);
    }

    @Override
    public Vector2f divide(Vector2fc o) {
        return this.divide(o.getX(), o.getY());
    }

    @Override
    public Vector2f divide(float x, float y) {
        this.x /= x;
        this.y /= y;

        return this;
    }

    @Override
    public float dot(float x, float y) {
        return this.x * x + this.y * y;
    }

    @Override
    public Vector2f normalize() {
        return this.divide(this.length());
    }

    @Override
    public Vector2f zero() {
        return this.set(0f, 0f);
    }

    @Override
    public float distance(float x, float y) {
        float dx = this.x - x;
        float dy = this.y - y;

        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Vector2f)
            && this.x == ((Vector2f) o).x
            && this.y == ((Vector2f) o).y;
    }

    @Override
    public Vector2f clone() {
        try {
            return (Vector2f) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}