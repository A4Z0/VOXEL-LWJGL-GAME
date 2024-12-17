package org.a4z0.lwjgl.demo.math.vector;

public class Vector3f implements Vector3fc {
    
    protected float x, y, z;

    /**
    * Construct a {@link Vector3f}.
    */

    public Vector3f() {
        this.zero();
    }

    /**
    * Construct a {@link Vector3f}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    public Vector3f(float x, float y, float z) {
        this.set(x, y, z);
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
    public Vector3f set(Vector3fc o) {
        return this.set(o.getX(), o.getY(), o.getZ());
    }

    @Override
    public Vector3f set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;

        return this;
    }

    @Override
    public Vector3f add(Vector3fc o) {
        return this.add(o.getX(), o.getY(), o.getZ());
    }

    @Override
    public Vector3f add(float x, float y, float z) {
        this.x += x;
        this.y += y;
        this.z += z;

        return this;
    }

    @Override
    public Vector3f subtract(Vector3fc o) {
        return this.subtract(o.getX(), o.getY(), o.getZ());
    }

    @Override
    public Vector3f subtract(float x, float y, float z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;

        return this;
    }

    @Override
    public Vector3f multiply(float multiplier) {
        return this.multiply(multiplier, multiplier, multiplier);
    }

    @Override
    public Vector3f multiply(Vector3fc o) {
        return this.multiply(o.getX(), o.getY(), o.getZ());
    }

    @Override
    public Vector3f multiply(float x, float y, float z) {
        this.x *= x;
        this.y *= y;
        this.z *= z;

        return this;
    }

    @Override
    public Vector3f divide(float divisor) {
        return this.divide(divisor, divisor, divisor);
    }

    @Override
    public Vector3f divide(Vector3fc o) {
        return this.divide(o.getX(), o.getY(), o.getZ());
    }

    @Override
    public Vector3f divide(float x, float y, float z) {
        this.x /= x;
        this.y /= y;
        this.z /= z;

        return this;
    }

    @Override
    public float dot(float x, float y, float z) {
        return this.x * x + this.y * y + this.z * z;
    }

    @Override
    public Vector3f normalize() {
        return this.divide(this.length());
    }

    @Override
    public Vector3f zero() {
        return this.set(0f, 0f, 0f);
    }

    @Override
    public float distance(float x, float y, float z) {
        float dx = this.x - x;
        float dy = this.y - y;
        float dz = this.z - z;

        return (float) Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Vector3f)
            && this.x == ((Vector3f) o).x
            && this.y == ((Vector3f) o).y
            && this.z == ((Vector3f) o).z;
    }

    @Override
    public Vector3f clone() {
        try {
            return (Vector3f) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}