package org.a4z0.lwjgl.demo.math.ray;

import org.a4z0.lwjgl.demo.math.vector.Vector3f;
import org.a4z0.lwjgl.demo.math.vector.Vector3fc;

public class Ray3f implements Ray3fc {

    protected final Vector3fc origin = new Vector3f();
    protected final Vector3fc direction = new Vector3f();

    /**
    * Construct a {@link Ray3f}.
    *
    * @param origin Origin.
    * @param direction Direction.
    */

    public Ray3f(Vector3fc origin, Vector3fc direction) {
        this.set(origin, direction);
    }

    /**
    * Construct a {@link Ray3f}.
    *
    * @param x Origin X-Axis.
    * @param y Origin Y-Axis.
    * @param z Origin Z-Axis.
    * @param dx Direction X-Axis.
    * @param dy Direction Y-Axis.
    * @param dz Direction Z-Axis.
    */

    public Ray3f(float x, float y, float z, float dx, float dy, float dz) {
        this.set(x, y, z, dx, dy, dz);
    }

    @Override
    public Vector3fc getOrigin() {
        return this.origin;
    }

    @Override
    public Vector3fc getDirection() {
        return this.direction;
    }

    @Override
    public Ray3f set(Vector3fc origin, Vector3fc direction) {
        return this.set(origin.getX(), origin.getY(), origin.getZ(), direction.getX(), direction.getY(), direction.getZ());
    }

    @Override
    public Ray3f set(float x, float y, float z, float dx, float dy, float dz) {
        this.getOrigin().set(x, y, z);
        this.getDirection().set(dx, dy, dz).normalize();

        return this;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Ray3f)
            && ((Ray3f) o).origin.equals(this.origin)
            && ((Ray3f) o).direction.equals(this.direction);
    }

    @Override
    public Ray3f clone() {
        try {
            return (Ray3f) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}