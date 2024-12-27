package org.a4z0.lwjgl.demo.camera;

import org.a4z0.api.location.Location3fc;
import org.joml.Matrix4dc;

public interface Camera {

    double DEFAULT_FAR_PLANE = 1000f;
    double DEFAULT_NEAR_PLANE = 0.001f;

    double DEFAULT_YAW = -90f;
    double DEFAULT_PITCH = 0f;
    double DEFAULT_ROLL = 0f;

    int DEFAULT_WIDTH = 800;
    int DEFAULT_HEIGHT = 600;

    double DEFAULT_FOV = 90f;

    /**
    * @return the Width.
    */

    int getWidth();

    Camera setWidth(int width);

    /**
    * @return the Height.
    */

    int getHeight();

    Camera setHeight(int height);

    /**
    * @return the Field of View.
    */

    double getFOV();

    Camera setFOV(double fov);

    /**
    * @return the X-Axis.
    */

    double getX();

    Camera setX(float x);

    Camera setX(double x);

    /**
    * @return the Y-Axis.
    */

    double getY();

    Camera setY(float y);

    Camera setY(double y);

    /**
    * @return the Z-Axis.
    */

    double getZ();

    Camera setZ(float z);

    Camera setZ(double z);

    /**
    * @return the Yaw.
    */

    double getYaw();

    Camera setYaw(float yaw);

    Camera setYaw(double yaw);

    /**
    * @return the Pitch.
    */

    double getPitch();

    Camera setPitch(float pitch);

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
    * ...
    *
    * @param l3f ...
    *
    * @return ...
    */

    Camera set(Location3fc l3f);

    /**
    * ...
    *
    * @param x ...
    * @param y ...
    * @param z ...
    *
    * @return ...
    */

    Camera set(float x, float y, float z, float yaw, float pitch);

    /**
    * ...
    *
    * @param x ...
    * @param y ...
    * @param z ...
    *
    * @return ...
    */

    Camera set(double x, double y, double z, double yaw, double pitch);
}