package org.a4z0.lwjgl.demo.math.camera;

import org.a4z0.lwjgl.demo.math.frustum.Frustum3f;
import org.a4z0.lwjgl.demo.math.vector.Vector3f;
import org.a4z0.lwjgl.demo.math.vector.Vector3fc;
import org.joml.Math;
import org.joml.Matrix4f;

public final class Camera3fa<T extends Camera3fa<T>> implements Camera3fc<T> {

    private float x, y, z;
    private float pitch, yaw, roll;

    private int width, height;
    private float fov;

    private final Matrix4f projection = new Matrix4f();
    private final Matrix4f view = new Matrix4f();
    private final Matrix4f projectionView = new Matrix4f();

    private final Frustum3f frustum = new Frustum3f();

    /**
    * Construct a {@link Camera3fa}.
    */

    public Camera3fa() {
        this(0, 0, 0);
    }

    /**
    * Construct a {@link Camera3fa}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    public Camera3fa(float x, float y, float z) {
        this(x, y, z, DEFAULT_YAW, DEFAULT_PITCH);
    }

    /**
    * Construct a {@link Camera3fa}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param yaw Yaw.
    * @param pitch Pitch.
    */

    public Camera3fa(float x, float y, float z, float yaw, float pitch) {
        this(x, y, z, yaw, pitch, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    /**
    * Construct a {@link Camera3fa}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param yaw Yaw.
    * @param pitch Pitch.
    * @param roll Roll.
    * @param width Width.
    * @param height Height.
    */

    public Camera3fa(float x, float y, float z, float yaw, float pitch, float roll, int width, int height) {
        this(x, y, z, yaw, pitch, roll, width, height, DEFAULT_FOV);
    }

    /**
    * Construct a {@link Camera3fa}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param yaw Yaw.
    * @param pitch Pitch.
    * @param roll Roll.
    * @param width Width.
    * @param height Height.
    * @param fov Field of View.
    */

    public Camera3fa(float x, float y, float z, float yaw, float pitch, float roll, int width, int height, float fov) {
        this.setPosition(x, y, z).setRotation(yaw, pitch, roll).setDimension(width, height).setFOV(fov);
    }

    @Override
    public float getX() {
        return this.x;
    }

    @Override
    public T setX(float x) {
        this.x = x;

        tickProjectionView();

        //noinspection unchecked
        return (T) this;
    }

    @Override
    public float getY() {
        return this.y;
    }

    @Override
    public T setY(float y) {
        this.y = y;

        tickProjectionView();

        //noinspection unchecked
        return (T) this;
    }

    @Override
    public float getZ() {
        return this.z;
    }

    @Override
    public T setZ(float z) {
        this.z = z;

        tickProjectionView();

        //noinspection unchecked
        return (T) this;
    }

    @Override
    public T setPosition(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;

        tickProjectionView();

        //noinspection unchecked
        return (T) this;
    }

    @Override
    public Vector3fc getPosition() {
        return new Vector3f(this.x, this.y, this.z);
    }

    @Override
    public float getYaw() {
        return this.yaw;
    }

    @Override
    public T setYaw(float yaw) {
        this.yaw = yaw;

        tickView();

        //noinspection unchecked
        return (T) this;
    }

    @Override
    public float getPitch() {
        return this.pitch;
    }

    @Override
    public T setPitch(float pitch) {
        this.pitch = pitch;

        tickView();

        //noinspection unchecked
        return (T) this;
    }

    @Override
    public float getRoll() {
        return this.roll;
    }

    @Override
    public T setRoll(float roll) {
        this.roll = roll;

        tickView();

        //noinspection unchecked
        return (T) this;
    }

    @Override
    public T setRotation(float yaw, float pitch, float roll) {
        this.yaw = yaw;
        this.pitch = pitch;
        this.roll = roll;

        tickView();

        //noinspection unchecked
        return (T) this;
    }

    @Override
    public Vector3fc getRotation() {
        return new Vector3f(this.yaw, this.pitch, this.roll);
    }

    @Override
    public Vector3fc getDirection() {
        float _YAW_RADIANS = Math.toRadians(this.getYaw());
        float _PITCH_RADIANS = Math.toRadians(this.getPitch());

        float x = -Math.cos(_PITCH_RADIANS) * Math.sin(_YAW_RADIANS);
        float y = -Math.sin(_PITCH_RADIANS);
        float z = Math.cos(_PITCH_RADIANS) * Math.cos(_YAW_RADIANS);

        return new Vector3f(x, y, z);
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public T setWidth(int width) {
        this.width = width;

        tickProjection();

        //noinspection unchecked
        return (T) this;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public T setHeight(int height) {
        this.height = height;

        tickProjection();

        //noinspection unchecked
        return (T) this;
    }

    @Override
    public T setDimension(int width, int height) {
        this.width = width;
        this.height = height;

        tickProjection();

        //noinspection unchecked
        return (T) this;
    }

    @Override
    public float getFOV() {
        return this.fov;
    }

    @Override
    public T setFOV(float fov) {
        this.fov = fov;

        tickProjection();

        //noinspection unchecked
        return (T) this;
    }

    @Override
    public Matrix4f getProjection() {
        return this.projection;
    }

    @Override
    public Matrix4f getView() {
        return this.view;
    }

    @Override
    public Matrix4f getProjectionView() {
        return this.projectionView;
    }

    public Frustum3f getFrustum() {
        return this.frustum;
    }

    /**
    * Ticks the {@link Matrix4f Projection}.
    */

    void tickProjection() {
        float _RADIANS_FOV = Math.toRadians(this.getFOV());
        float _ASPECT_RATIO = (float) this.getWidth() / this.getHeight();

        this.projection.identity().perspective(_RADIANS_FOV, _ASPECT_RATIO, DEFAULT_NEAR_PLANE, DEFAULT_FAR_PLANE);

        tickProjectionView();
    }

    /**
    * Ticks the {@link Matrix4f View}.
    */

    void tickView() {
        float _YAW_RADIANS = Math.toRadians(this.getYaw());
        float _PITCH_RADIANS = Math.toRadians(this.getPitch());

        float _YAW_COS = Math.cos(_YAW_RADIANS);
        float _YAW_SIN = Math.sin(_YAW_RADIANS);
        float _PITCH_COS = Math.cos(_PITCH_RADIANS);
        float _PITCH_SIN = Math.sin(_PITCH_RADIANS);

        float _DIR_X = -_PITCH_COS * _YAW_SIN;
        float _DIR_Y = -_PITCH_SIN;
        float _DIR_Z = _PITCH_COS * _YAW_COS;

        this.view.identity().lookAt(this.getX(), this.getY(), this.getZ(), this.getX() + _DIR_X, this.getY() + _DIR_Y, this.getZ() + _DIR_Z, 0, 1, 0);

        tickProjectionView();
    }

    /**
    * Ticks the {@link Matrix4f Projection View}.
    */

    void tickProjectionView() {
        this.projectionView.set(this.projection).mul(this.view).translate(-this.getX(), -this.getY(), -this.getZ());

       /*if(!FrustumUpdate.FRUSTUM_UPDATE)
            this.frustum.set(this.projectionView);*/
    }
}