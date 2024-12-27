package org.a4z0.lwjgl.demo.camera;

import org.a4z0.api.location.Location3fc;
import org.joml.Matrix4dc;

public interface Camera {

    int DEFAULT_WIDTH = 800;
    int DEFAULT_HEIGHT = 600;

    double DEFAULT_FOV = 90f;

    double DEFAULT_YAW = -90f;
    double DEFAULT_PITCH = 0f;

    double DEFAULT_FAR_PLANE = 1000f;
    double DEFAULT_NEAR_PLANE = 0.001f;

    /**
    * @return the Width.
    */

    int getWidth();

    /**
    * Sets the Width.
    *
    * @param width Width to be set.
    *
    *
    * @return this Camera.
    */

    Camera setWidth(int width);

    /**
    * @return the Height.
    */

    int getHeight();

    /**
    * Sets the Height.
    *
    * @param height Height to be set.
    *
    * @return this Camera.
    */

    Camera setHeight(int height);

    /**
    * @return the Field of View.
    */

    double getFOV();

    /**
    * Sets the Field of View.
    *
    * @param fov FOV to be set.
    *
    * @return this Camera.
    */

    Camera setFOV(double fov);

    /**
    * @return the X-Axis.
    */

    double getX();

    /**
    * Sets the X-Axis.
    *
    * @param x X-Axis to be set.
    *
    * @return this Camera.
    */

    Camera setX(float x);

    /***
    * Sets the X-Axis.
    *
    * @param x X-Axis to be set.
    *
    * @return this Camera.
    */

    Camera setX(double x);

    /**
    * @return the Y-Axis.
    */

    double getY();

    /**
    * Sets the Y-Axis
    *
    * @param y Y-Axis.
    *
    * @return this Camera.
    */

    Camera setY(float y);

    /**
    * Sets the Y-Axis
    *
    * @param y Y-Axis.
    *
    * @return this Camera.
    */

    Camera setY(double y);

    /**
    * @return the Z-Axis.
    */

    double getZ();

    /**
    * Sets the Z-Axis.
    *
    * @param z Z-Axis to be set.
    *
    * @return this Camera.
    */

    Camera setZ(float z);

    /**
    * Sets the Z-Axis.
    *
    * @param z Z-Axis to be set.
    *
    * @return this Camera.
    */

    Camera setZ(double z);

    /**
    * @return the Yaw.
    */

    double getYaw();

    /**
    * Sets the Yaw.
    *
    * @param yaw Yaw.
    *
    * @return this Camera.
    */

    Camera setYaw(float yaw);

    /**
    * Sets the Yaw.
    *
    * @param yaw Yaw.
    *
    * @return this Camera.
    */

    Camera setYaw(double yaw);

    /**
    * @return the Pitch.
    */

    double getPitch();

    /**
    * Sets the Pitch.
    *
    * @param pitch Pitch.
    *
    * @return this Camera.
    */

    Camera setPitch(float pitch);

    /**
    * Sets the Pitch.
    *
    * @param pitch Pitch.
    *
    * @return this Camera.
    */

    Camera setPitch(double pitch);

    /**
    * @return the Projection.
    */

    Matrix4dc getProjection();

    /**
    * @return the View.
    */

    Matrix4dc getView();

    /**
    * @return the Projection View.
    */

    Matrix4dc getProjectionView();

    /**
    * Sets the axes by a {@link Location3fc}.
    *
    * @param l3f Location.
    *
    * @return this Camera.
    */

    Camera set(Location3fc l3f);

    /**
    * Sets the axes by other axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return this Camera.
    */

    Camera set(float x, float y, float z);

    /**
    * Sets the axes by other axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return this Camera.
    */

    Camera set(double x, double y, double z);

    /**
    * Sets the axes by other axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param yaw Yaw.
    * @param pitch Pitch.
    *
    * @return this Camera.
    */

    Camera set(float x, float y, float z, float yaw, float pitch);

    /**
    * Sets the axes by other axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param yaw Yaw.
    * @param pitch Pitch.
    *
    * @return this Camera.
    */

    Camera set(double x, double y, double z, double yaw, double pitch);

    /**
    * Checks if this is equals to the given {@link Object}.
    *
    * @param o {@link Object} to be compared.
    *
    * @return true if equal, false otherwise.
    */

    @Override
    boolean equals(Object o);

    /**
    * @return this as a {@link String}.
    */

    @Override
    String toString();

    /**
    * @return the Hashcode.
    */

    @Override
    int hashCode();
}