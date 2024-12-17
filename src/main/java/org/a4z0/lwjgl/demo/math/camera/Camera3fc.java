package org.a4z0.lwjgl.demo.math.camera;

import org.a4z0.lwjgl.demo.math.vector.Vector3fc;
import org.joml.Matrix4f;

public interface Camera3fc<T extends Camera3fc<T>> {

    float DEFAULT_FAR_PLANE = 1000f;
    float DEFAULT_NEAR_PLANE = 0.001f;

    float DEFAULT_YAW = -90f;
    float DEFAULT_PITCH = 0f;
    float DEFAULT_ROLL = 0f;

    int DEFAULT_WIDTH = 800;
    int DEFAULT_HEIGHT = 600;

    float DEFAULT_FOV = 90f;

    /**
    * @return the X-Axis.
    */

    float getX();

    /**
    * Sets the X-Axis.
    *
    * @param x X-Axis.
    *
    * @return this {@link Camera3fc}.
    */

    T setX(float x);

    /**
    * @return the Y-Axis.
    */

    float getY();

    /**
    * Sets the Y-Axis.
    *
    * @param y Y-Axis.
    *
    * @return this {@link Camera3fc}.
    */

    T setY(float y);

    /**
    * @return the Z-Axis.
    */

    float getZ();

    /**
    * Sets the Z-Axis.
    *
    * @param z Z-Axis.
    *
    * @return this {@link Camera3fc}.
    */

    T setZ(float z);

    /**
    * ...
    *
    * @param x ...
    * @param y ...
    * @param z ...
    *
    * @return this {@link Camera3fc}.
    */

    T setPosition(float x, float y, float z);

    /**
    * @return ...
    */

    Vector3fc getPosition();

    /**
    * @return ...
    */

    float getYaw();

    /**
    * ...
    *
    * @param yaw ...
    *
    * @return this {@link Camera3fc}.
    */

    T setYaw(float yaw);

    /**
    * @return ...
    */

    float getPitch();

    /**
    * ...
    *
    * @param pitch ...
    *
    * @return this {@link Camera3fc}.
    */

    T setPitch(float pitch);

    /**
    * @return ...
    */

    float getRoll();

    /**
    * ...
    *
    * @param roll ...
    *
    * @return this {@link Camera3fc}.
    */

    T setRoll(float roll);

    /**
    * ...
    *
    * @param yaw ...
    * @param pitch ...
    *
    * @return this {@link Camera3fc}.
    */

    default T setRotation(float yaw, float pitch) {
        return this.setRotation(yaw, pitch, this.getRoll());
    }

    /**
    * ...
    *
    * @param yaw ...
    * @param pitch ...
    * @param roll ...
    *
    * @return this {@link Camera3fc}.
    */

    T setRotation(float yaw, float pitch, float roll);

    /**
    * @return ...
    */

    Vector3fc getRotation();

    /**
    * @return ...
    */

    Vector3fc getDirection();

    /**
    * @return the Width.
    */

    int getWidth();

    /**
    * Sets the Width.
    *
    * @param width Width.
    *
    * @return this {@link Camera3fc}.
    */

    T setWidth(int width);

    /**
    * @return the Height.
    */

    int getHeight();

    /**
    * Sets the Height.
    *
    * @param height Height.
    *
    * @return this {@link Camera3fc}.
    */

    T setHeight(int height);

    /**
    * ...
    *
    * @param width ...
    * @param height ...
    *
    * @return this {@link Camera3fc}.
    */

    T setDimension(int width, int height);

    /**
    * @return the Field of View.
    */

    float getFOV();

    /**
    * Sets the Field of View.
    *
    * @param fov Field of View.
    *
    * @return this {@link Camera3fc}.
    */

    T setFOV(float fov);

    /**
    * @return the {@link Matrix4f Projection}.
    */

    Matrix4f getProjection();

    /**
    * @return the {@link Matrix4f View}.
    */

    Matrix4f getView();

    /**
    * @return the {@link Matrix4f Projection View}.
    */

    Matrix4f getProjectionView();
}