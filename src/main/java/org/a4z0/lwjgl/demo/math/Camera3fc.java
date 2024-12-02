package org.a4z0.lwjgl.demo.math;

public interface Camera3fc {

    float DEFAULT_FAR_PLANE = 1000f;
    float DEFAULT_NEAR_PLANE = 0.001f;

    float DEFAULT_FOV = 90f;

    float DEFAULT_PITCH = 0f;
    float DEFAULT_YAW = -90f;

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
    * @return the Yaw.
    */

    float getYaw();

    /**
    * ...
    *
    * @param yaw Yaw.
    *
    * @return this {@link Camera3fc}.
    */

    Camera3fc setYaw(float yaw);

    /**
    * @return the Pitch.
    */

    float getPitch();

    /**
    * ...
    *
    * @param pitch Pitch.
    *
    * @return this {@link Camera3f}.
    */

    Camera3fc setPitch(float pitch);

    /**
    * ...
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return this {@link Camera3fc}.
    */

    Camera3fc set(float x, float y, float z);

    /**
    * ...
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param yaw Yaw.
    * @param pitch Pitch.
    *
    * @return this {@link Camera3fc}.
    */

    Camera3fc set(float x, float y, float z, float yaw, float pitch);
}