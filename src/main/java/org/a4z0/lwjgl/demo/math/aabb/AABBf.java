package org.a4z0.lwjgl.demo.math.aabb;

import org.a4z0.lwjgl.demo.math.vector.Vector3f;
import org.a4z0.lwjgl.demo.math.vector.Vector3fc;

public class AABBf implements AABBfc {

    protected float x1, y1, z1;
    protected float x2, y2, z2;

    /**
    * Construct a {@link AABBf}.
    *
    * @param minimum Lower Northeast Corner.
    * @param maximum Upper Southwest Corner.
    */

    public AABBf(Vector3fc minimum, Vector3fc maximum) {
        this.set(minimum, maximum);
    }

    /**
    * Construct a {@link AABBf}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    public AABBf(float x, float y, float z) {
        this.set(x, y, z);
    }

    /**
    * Construct a {@link AABBf}.
    *
    * @param x1 Minimum X-Axis.
    * @param y1 Minimum Y-Axis.
    * @param z1 Minimum Z-Axis.
    * @param x2 Maximum X-Axis.
    * @param y2 Maximum Y-Axis.
    * @param z2 Maximum Z-Axis.
    */

    public AABBf(float x1, float y1, float z1, float x2, float y2, float z2) {
        this.set(x1, y1, z1, x2, y2, z2);
    }

    @Override
    public Vector3fc getUpperSE() {
        return new Vector3f(this.x1, this.y1, this.z1);
    }

    @Override
    public Vector3fc getLowerSW() {
        return new Vector3f(this.x2, this.y2, this.z2);
    }

    @Override
    public Vector3fc getCenter() {
        return new Vector3f((this.x1 + this.x2) / 2f, (this.y1 + this.y2) / 2f, (this.z1 + this.z2) / 2f);
    }

    @Override
    public float getLowerX() {
        return this.x1;
    }

    @Override
    public float getLowerY() {
        return this.y1;
    }

    @Override
    public float getLowerZ() {
        return this.z1;
    }

    @Override
    public float getUpperX() {
        return this.x2;
    }

    @Override
    public float getUpperY() {
        return this.y2;
    }

    @Override
    public float getUpperZ() {
        return this.z2;
    }

    @Override
    public AABBf set(Vector3fc v3f) {
        return this.set(v3f.getX(), v3f.getY(), v3f.getZ());
    }

    @Override
    public AABBf set(float x, float y, float z) {
        return this.set(-x, -y, -z, x, y, z);
    }

    @Override
    public AABBf set(Vector3fc minimum, Vector3fc maximum) {
        return this.set(minimum.getX(), minimum.getY(), minimum.getZ(), maximum.getX(), maximum.getY(), maximum.getZ());
    }

    @Override
    public AABBf set(float x1, float y1, float z1, float x2, float y2, float z2) {
        this.x1 = Math.min(x1, x2);
        this.y1 = Math.min(y1, y2);
        this.z1 = Math.min(z1, z2);
        this.x2 = Math.max(x1, x2);
        this.y2 = Math.max(y1, y2);
        this.z2 = Math.max(z1, z2);

        return this;
    }

    @Override
    public AABBf add(Vector3fc v3f) {
        return this.add(v3f.getX(), v3f.getY(), v3f.getZ());
    }

    @Override
    public AABBf add(float x, float y, float z) {
        return this.add(x, y, z, x, y, z);
    }

    @Override
    public AABBf add(Vector3fc minimum, Vector3fc maximum) {
        return this.add(minimum.getX(), minimum.getY(), minimum.getZ(), maximum.getX(), maximum.getY(), maximum.getZ());
    }

    @Override
    public AABBf add(float x1, float y1, float z1, float x2, float y2, float z2) {
        this.x1 += x1;
        this.y1 += y1;
        this.z1 += z1;
        this.x2 += x2;
        this.y2 += y2;
        this.z2 += z2;

        return this;
    }

    @Override
    public AABBf subtract(Vector3fc vf3) {
        return this.subtract(vf3.getX(), vf3.getY(), vf3.getZ());
    }

    @Override
    public AABBf subtract(float x, float y, float z) {
        return this.subtract(x, y, z, x, y, z);
    }

    @Override
    public AABBf subtract(Vector3fc minimum, Vector3fc maximum) {
        return this.subtract(minimum.getX(), minimum.getY(), minimum.getZ(), maximum.getX(), maximum.getY(), maximum.getZ());
    }

    @Override
    public AABBf subtract(float x1, float y1, float z1, float x2, float y2, float z2) {
        this.x1 -= x1;
        this.y1 -= y1;
        this.z1 -= z1;
        this.x2 -= x2;
        this.y2 -= y2;
        this.z2 -= z2;

        return this;
    }

    @Override
    public AABBf multiply(float scalar) {
        return this.multiply(scalar, scalar, scalar);
    }

    @Override
    public AABBf multiply(Vector3fc v3f) {
        return this.multiply(v3f.getX(), v3f.getY(), v3f.getZ());
    }

    @Override
    public AABBf multiply(float x, float y, float z) {
        return this.multiply(x, y, z, x, y, z);
    }

    @Override
    public AABBf multiply(Vector3fc minimum, Vector3fc maximum) {
        return this.multiply(minimum.getX(), minimum.getY(), minimum.getZ(), maximum.getX(), maximum.getY(), maximum.getZ());
    }

    @Override
    public AABBf multiply(float x1, float y1, float z1, float x2, float y2, float z2) {
        this.x1 *= x1;
        this.y1 *= y1;
        this.z1 *= z1;
        this.x2 *= x2;
        this.y2 *= y2;
        this.z2 *= z2;

        return this;
    }

    @Override
    public AABBf divide(float divisor) {
        return this.divide(divisor, divisor, divisor);
    }

    @Override
    public AABBf divide(Vector3fc v3f) {
        return this.divide(v3f.getX(), v3f.getY(), v3f.getZ());
    }

    @Override
    public AABBf divide(float x, float y, float z) {
        return this.divide(x, y, z, x, y, z);
    }

    @Override
    public AABBf divide(Vector3fc minimum, Vector3fc maximum) {
        return this.divide(minimum.getX(), minimum.getY(), minimum.getZ(), maximum.getX(), maximum.getY(), maximum.getZ());
    }

    @Override
    public AABBf divide(float x1, float y1, float z1, float x2, float y2, float z2) {
        this.x1 /= x1;
        this.y1 /= y1;
        this.z1 /= z1;
        this.x2 /= x2;
        this.y2 /= y2;
        this.z2 /= z2;

        return this;
    }

    @Override
    public boolean intersects(float x, float y, float z) {
        return (x >= this.x1 && x <= this.x2)
            && (y >= this.y1 && y <= this.y2)
            && (z >= this.z1 && z <= this.z2);
    }

    @Override
    public boolean intersects(int x1, int y1, int z1, int x2, int y2, int z2) {
        return (this.x1 <= x2 && this.x2 >= x1)
            && (this.y1 <= y2 && this.y2 >= y1)
            && (this.z1 <= z2 && this.z2 >= z1);
    }

    @Override
    public boolean intersects(float x1, float y1, float z1, float x2, float y2, float z2) {
        return (this.x1 <= x2 && this.x2 >= x1)
            && (this.y1 <= y2 && this.y2 >= y1)
            && (this.z1 <= z2 && this.z2 >= z1);
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof AABBf)
            && (this.x1 == ((AABBf) o).x1 && this.x2 == ((AABBf) o).x2)
            && (this.y1 == ((AABBf) o).y1 && this.y2 == ((AABBf) o).y2)
            && (this.z1 == ((AABBf) o).z1 && this.z2 == ((AABBf) o).z2);
    }

    @Override
    public AABBf clone() {
        try {
            return (AABBf) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}