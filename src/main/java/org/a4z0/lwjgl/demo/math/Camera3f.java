package org.a4z0.lwjgl.demo.math;

public class Camera3f implements Camera3fc {

    protected float x, y, z;
    protected float yaw, pitch;

    /**
    * Construct a {@link Camera3f}.
    */

    public Camera3f() {
        this(0, 0, 0);
    }

    /**
    * Construct a {@link Camera3f}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    public Camera3f(float x, float y, float z) {
        this(x, y, z, DEFAULT_YAW, DEFAULT_PITCH);
    }

    /**
    * Construct a {@link Camera3f}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param yaw Yaw.
    * @param pitch Pitch.
    */

    public Camera3f(float x, float y, float z, float yaw, float pitch) {
        this.set(x, y, z, yaw, pitch);
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
    public float getYaw() {
        return this.yaw;
    }

    @Override
    public Camera3fc setYaw(float yaw) {
        this.yaw = yaw;

        return this;
    }

    @Override
    public float getPitch() {
        return this.pitch;
    }

    @Override
    public Camera3fc setPitch(float pitch) {
        this.pitch = pitch;

        return this;
    }

    @Override
    public Camera3fc set(float x, float y, float z) {
        return this.set(x, y, z, this.yaw, this.pitch);
    }

    @Override
    public Camera3fc set(float x, float y, float z, float yaw, float pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;

        return this;
    }
}