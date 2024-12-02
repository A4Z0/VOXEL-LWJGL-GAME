package org.a4z0.lwjgl.demo.math;

public class Plane3f implements Plane3fc {

    protected float x;
    protected float y;
    protected float z;
    protected float d;

    /**
    * Construct a {@link Plane3f}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param d Direction.
    */

    public Plane3f(float x, float y, float z, float d) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.d = d;
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
    public float getZ() {
        return this.z;
    }

    @Override
    public float getDistance() {
        return this.d;
    }

    @Override
    public Plane3f set(float x, float y, float z, float d) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.d = d;

        return this;
    }

    @Override
    public Plane3fc divide(float divisor) {
        return this.divide(divisor, divisor, divisor, divisor);
    }

    @Override
    public Plane3fc divide(float x, float y, float z, float d) {
        this.x /= x;
        this.y /= y;
        this.z /= z;
        this.d /= d;

        return this;
    }

    @Override
    public Plane3fc normalize() {
        return this.divide((this.x * this.x) + (this.y * this.y) + (this.z * this.z));
    }

    @Override
    public float testPoint(float x, float y, float z) {
        return (this.x * x + this.y * y + this.z * z) + this.d;
    }
}