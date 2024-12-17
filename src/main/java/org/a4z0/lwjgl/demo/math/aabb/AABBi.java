package org.a4z0.lwjgl.demo.math.aabb;

import org.a4z0.lwjgl.demo.math.vector.Vector3i;
import org.a4z0.lwjgl.demo.math.vector.Vector3ic;

public class AABBi implements AABBic {

    protected int x1, y1, z1;
    protected int x2, y2, z2;

    /**
    * Construct a {@link AABBi}.
    *
    * @param minimum Lower Northeast Corner.
    * @param maximum Upper Southwest Corner.
    */

    public AABBi(Vector3ic minimum, Vector3ic maximum) {
        this.set(minimum, maximum);
    }

    /**
    * Construct a {@link AABBi}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    public AABBi(int x, int y, int z) {
        this.set(x, y, z);
    }

    /**
    * Construct a {@link AABBi}.
    *
    * @param x1 Minimum X-Axis.
    * @param y1 Minimum Y-Axis.
    * @param z1 Minimum Z-Axis.
    * @param x2 Maximum X-Axis.
    * @param y2 Maximum Y-Axis.
    * @param z2 Maximum Z-Axis.
    */

    public AABBi(int x1, int y1, int z1, int x2, int y2, int z2) {
        this.set(x1, y1, z1, x2, y2, z2);
    }

    @Override
    public Vector3ic getUpperSE() {
        return new Vector3i(this.x1, this.y1, this.z1);
    }

    @Override
    public Vector3ic getLowerSW() {
        return new Vector3i(this.x2, this.y2, this.z2);
    }

    @Override
    public Vector3ic getCenter() {
        return new Vector3i((this.x1 + this.x2) / 2, (this.y1 + this.y2) / 2, (this.z1 + this.z2) / 2);
    }

    @Override
    public int getLowerX() {
        return this.x1;
    }

    @Override
    public int getLowerY() {
        return this.y1;
    }

    @Override
    public int getLowerZ() {
        return this.z1;
    }

    @Override
    public int getUpperX() {
        return this.x2;
    }

    @Override
    public int getUpperY() {
        return this.y2;
    }

    @Override
    public int getUpperZ() {
        return this.z2;
    }

    @Override
    public AABBi set(Vector3ic v3i) {
        return this.set(v3i.getX(), v3i.getY(), v3i.getZ());
    }

    @Override
    public AABBi set(int x, int y, int z) {
        return this.set(-x, -y, -z, x, y, z);
    }

    @Override
    public AABBi set(Vector3ic minimum, Vector3ic maximum) {
        return this.set(minimum.getX(), minimum.getY(), minimum.getZ(), maximum.getX(), maximum.getY(), maximum.getZ());
    }

    @Override
    public AABBi set(int x1, int y1, int z1, int x2, int y2, int z2) {
        this.x1 = Math.min(x1, x2);
        this.y1 = Math.min(y1, y2);
        this.z1 = Math.min(z1, z2);
        this.x2 = Math.max(x1, x2);
        this.y2 = Math.max(y1, y2);
        this.z2 = Math.max(z1, z2);

        return this;
    }

    @Override
    public AABBi add(Vector3ic v3i) {
        return this.add(v3i.getX(), v3i.getY(), v3i.getZ());
    }

    @Override
    public AABBi add(int x, int y, int z) {
        return this.add(x, y, z, x, y, z);
    }

    @Override
    public AABBi add(Vector3ic minimum, Vector3ic maximum) {
        return this.add(minimum.getX(), minimum.getY(), minimum.getZ(), maximum.getX(), maximum.getY(), maximum.getZ());
    }

    @Override
    public AABBi add(int x1, int y1, int z1, int x2, int y2, int z2) {
        this.x1 += x1;
        this.y1 += y1;
        this.z1 += z1;
        this.x2 += x2;
        this.y2 += y2;
        this.z2 += z2;

        return this;
    }

    @Override
    public AABBi subtract(Vector3ic v3i) {
        return this.subtract(v3i.getX(), v3i.getY(), v3i.getZ());
    }

    @Override
    public AABBi subtract(int x, int y, int z) {
        return this.subtract(x, y, z, x, y, z);
    }

    @Override
    public AABBi subtract(Vector3ic minimum, Vector3ic maximum) {
        return this.subtract(minimum.getX(), minimum.getY(), minimum.getZ(), maximum.getX(), maximum.getY(), maximum.getZ());
    }

    @Override
    public AABBi subtract(int x1, int y1, int z1, int x2, int y2, int z2) {
        this.x1 -= x1;
        this.y1 -= y1;
        this.z1 -= z1;
        this.x2 -= x2;
        this.y2 -= y2;
        this.z2 -= z2;

        return this;
    }

    @Override
    public AABBi multiply(int scalar) {
        return this.multiply(scalar, scalar, scalar);
    }

    @Override
    public AABBi multiply(Vector3ic v3i) {
        return this.multiply(v3i.getX(), v3i.getY(), v3i.getZ());
    }

    @Override
    public AABBi multiply(int x, int y, int z) {
        return this.multiply(x, y, z, x, y, z);
    }

    @Override
    public AABBi multiply(Vector3ic minimum, Vector3ic maximum) {
        return this.multiply(minimum.getX(), minimum.getY(), minimum.getZ(), maximum.getX(), maximum.getY(), maximum.getZ());
    }

    @Override
    public AABBi multiply(int x1, int y1, int z1, int x2, int y2, int z2) {
        this.x1 *= x1;
        this.y1 *= y1;
        this.z1 *= z1;
        this.x2 *= x2;
        this.y2 *= y2;
        this.z2 *= z2;

        return this;
    }

    @Override
    public AABBi divide(int divisor) {
        return this.divide(divisor, divisor, divisor);
    }

    @Override
    public AABBi divide(Vector3ic v3i) {
        return this.divide(v3i.getX(), v3i.getY(), v3i.getZ());
    }

    @Override
    public AABBi divide(int x, int y, int z) {
        return this.divide(x, y, z, x, y, z);
    }

    @Override
    public AABBi divide(Vector3ic minimum, Vector3ic maximum) {
        return this.divide(minimum.getX(), minimum.getY(), minimum.getZ(), maximum.getX(), maximum.getY(), maximum.getZ());
    }

    @Override
    public AABBi divide(int x1, int y1, int z1, int x2, int y2, int z2) {
        this.x1 /= x1;
        this.y1 /= y1;
        this.z1 /= z1;
        this.x2 /= x2;
        this.y2 /= y2;
        this.z2 /= z2;

        return this;
    }

    @Override
    public boolean intersects(int x, int y, int z) {
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
        return (o instanceof AABBi)
            && (this.x1 == ((AABBi) o).x1 && this.x2 == ((AABBi) o).x2)
            && (this.y1 == ((AABBi) o).y1 && this.y2 == ((AABBi) o).y2)
            && (this.z1 == ((AABBi) o).z1 && this.z2 == ((AABBi) o).z2);
    }

    @Override
    public AABBi clone() {
        try {
            return (AABBi) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}