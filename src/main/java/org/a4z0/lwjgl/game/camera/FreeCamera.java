package org.a4z0.lwjgl.game.camera;

import org.a4z0.lwjgl.api.location.Location3fc;
import org.joml.Matrix4d;
import org.joml.Matrix4dc;

import java.util.Objects;

public class FreeCamera implements Camera {

    protected int width, height;

    protected double fov;
    protected double x, y, z;
    protected double yaw, pitch;

    protected final Matrix4d projection = new Matrix4d();
    protected final Matrix4d view = new Matrix4d();
    protected final Matrix4d projectionView = new Matrix4d();

    /**
    * Construct a {@link FreeCamera}.
    */

    public FreeCamera() {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_FOV);
    }

    /**
    * Construct a {@link FreeCamera}.
    *
    * @param width Width.
    * @param height Height.
    * @param fov Field of View.
    */

    public FreeCamera(int width, int height, double fov) {
        this(width, height, fov, 0, 0, 0);
    }

    /**
    * Construct a {@link FreeCamera}.
    *
    * @param width Width.
    * @param height Height.
    * @param fov Field of View.
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    public FreeCamera(int width, int height, double fov, double x, double y, double z) {
        this(width, height, fov, x, y, z, DEFAULT_YAW, DEFAULT_PITCH);
    }

    /**
    * Construct a {@link FreeCamera}.
    *
    * @param width Width.
    * @param height Height.
    * @param fov Field of View.
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param yaw Yaw.
    * @param pitch Pitch.
    */

    public FreeCamera(int width, int height, double fov, double x, double y, double z, double yaw, double pitch) {
        this.setWidth(width).setHeight(height).setFOV(fov).set(x, y, z, yaw, pitch);
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public Camera setWidth(int width) {
        this.width = width;

        this.updateProjection();

        return this;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public Camera setHeight(int height) {
        this.height = height;

        this.updateProjection();

        return this;
    }

    @Override
    public double getFOV() {
        return this.fov;
    }

    @Override
    public Camera setFOV(double fov) {
        this.fov = fov;

        this.updateProjection();

        return this;
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public FreeCamera setX(float x) {
        return this.setX((double) x);
    }

    @Override
    public FreeCamera setX(double x) {
        this.x = x;

        this.updateProjectionView();

        return this;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public FreeCamera setY(float y) {
        return this.setY((double) y);
    }

    @Override
    public FreeCamera setY(double y) {
        this.y = y;

        this.updateProjectionView();

        return this;
    }

    @Override
    public double getZ() {
        return this.z;
    }

    @Override
    public FreeCamera setZ(float z) {
        return this.setZ((double) z);
    }

    @Override
    public FreeCamera setZ(double z) {
        this.z = z;

        this.updateProjectionView();

        return this;
    }

    @Override
    public double getYaw() {
        return this.yaw;
    }

    @Override
    public FreeCamera setYaw(float yaw) {
        return this.setYaw((double) yaw);
    }

    @Override
    public FreeCamera setYaw(double yaw) {
        this.yaw = yaw;

        this.updateView();

        return this;
    }

    @Override
    public double getPitch() {
        return this.pitch;
    }

    @Override
    public FreeCamera setPitch(float pitch) {
        return this.setPitch((double) pitch);
    }

    @Override
    public FreeCamera setPitch(double pitch) {
        this.pitch = pitch;

        this.updateView();

        return this;
    }

    @Override
    public Matrix4dc getProjection() {
        return this.projection;
    }

    @Override
    public Matrix4dc getView() {
        return this.view;
    }

    @Override
    public Matrix4dc getProjectionView() {
        return this.projectionView;
    }

    @Override
    public FreeCamera set(Location3fc l3f) {
        return this.set(l3f.getX(), l3f.getY(), l3f.getZ(), l3f.getYaw(), l3f.getPitch());
    }

    @Override
    public FreeCamera set(float x, float y, float z) {
        return this.set((double) x, (double) y, (double) z);
    }

    @Override
    public FreeCamera set(double x, double y, double z) {
        return this.set(x, y, z, this.getYaw(), this.getPitch());
    }

    @Override
    public FreeCamera set(float x, float y, float z, float yaw, float pitch) {
        return this.set((double) x, (double) y, (double) z, (double) yaw, (double) pitch);
    }

    @Override
    public FreeCamera set(double x, double y, double z, double yaw, double pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;

        this.updateProjection();
        this.updateView();

        return this;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof FreeCamera)
            && ((FreeCamera) o).getX() == this.getX()
            && ((FreeCamera) o).getY() == this.getY()
            && ((FreeCamera) o).getZ() == this.getZ()
            && ((FreeCamera) o).getYaw() == this.getYaw()
            && ((FreeCamera) o).getPitch() == this.getPitch();
    }

    @Override
    public String toString() {
        return "FreeCamera{"
            + "\"X\": " + this.getX()
            + ", "
            + "\"Y\": " + this.getY()
            + ", "
            + "\"Z\": " + this.getZ()
            + ", "
            + "\"Yaw\": " + this.getYaw()
            + ", "
            + "\"Pitch\": " + this.getPitch()
        + "}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y, this.z, this.yaw, this.pitch);
    }

    /**
    * Updates the Projection.
    */

    protected void updateProjection() {
        double radiansFov = Math.toRadians(this.getFOV());
        double aspectRatio = (double) this.getWidth() / this.getHeight();

        this.projection.identity().perspective(
            radiansFov,
            aspectRatio,
            DEFAULT_NEAR_PLANE, DEFAULT_FAR_PLANE
        );

        this.updateProjectionView();
    }

    /**
    * Updates the View.
    */

    protected void updateView() {
        double yawRadians = Math.toRadians(this.getYaw());
        double yawCos = Math.cos(yawRadians);
        double yawSin = Math.sin(yawRadians);

        double pitchRadians = Math.toRadians(this.getPitch());
        double pitchCos = Math.cos(pitchRadians);
        double pitchSin = Math.sin(pitchRadians);

        double dx = -pitchCos * yawSin;
        double dy = -pitchSin;
        double dz = pitchCos * yawCos;

        this.view.identity().lookAt(0, 0, 0, dx, dy, dz, 0, 1, 0);

        this.updateProjectionView();
    }

    /**
    * Updates the Projection View.
    */

    protected void updateProjectionView() {
        this.projectionView.set(this.projection).mul(this.view).translate(-this.getX(), -this.getY(), -this.getZ());
    }
}