package org.a4z0.lwjgl.demo.math.frustum;

import org.a4z0.api.math.plane.Plane3fc;
import org.joml.Matrix4f;

public class Frustum3f implements Frustum3fc {

    protected final Plane3fc[] planes;

    /**
    * Construct a {@link Frustum3f}.
    */

    public Frustum3f() {
        this(DEFAULT_RIGHT_PLANE, DEFAULT_LEFT_PLANE, DEFAULT_TOP_PLANE, DEFAULT_BOTTOM_PLANE, DEFAULT_NEAR_PLANE, DEFAULT_FAR_PLANE);
    }

    /**
    * Construct a {@link Frustum3f}.
    *
    * @param l Left.
    * @param r Right.
    * @param t Top.
    * @param b Bottom.
    * @param n Near.
    * @param f Far.
    */

    public Frustum3f(Plane3fc l, Plane3fc r, Plane3fc t, Plane3fc b, Plane3fc n, Plane3fc f) {
        this.planes = new Plane3fc[]{l, r, t, b, n, f};
    }

    @Override
    public Plane3fc getLeft() {
        return this.planes[0];
    }

    @Override
    public Plane3fc getRight() {
        return this.planes[1];
    }

    @Override
    public Plane3fc getTop() {
        return this.planes[2];
    }

    @Override
    public Plane3fc getBottom() {
        return this.planes[3];
    }

    @Override
    public Plane3fc getNear() {
        return this.planes[4];
    }

    @Override
    public Plane3fc getFar() {
        return this.planes[5];
    }

    @Override
    public Frustum3f set(Matrix4f projectionView) {
        this.planes[0].set(projectionView.m03() + projectionView.m00(), projectionView.m13() + projectionView.m10(), projectionView.m23() + projectionView.m20(), projectionView.m33() + projectionView.m30()).normalize();
        this.planes[1].set(projectionView.m03() - projectionView.m00(), projectionView.m13() - projectionView.m10(), projectionView.m23() - projectionView.m20(), projectionView.m33() - projectionView.m30()).normalize();
        this.planes[2].set(projectionView.m03() + projectionView.m01(), projectionView.m13() + projectionView.m11(), projectionView.m23() + projectionView.m21(), projectionView.m33() + projectionView.m31()).normalize();
        this.planes[3].set(projectionView.m03() - projectionView.m01(), projectionView.m13() - projectionView.m11(), projectionView.m23() - projectionView.m21(), projectionView.m33() - projectionView.m31()).normalize();
        this.planes[4].set(projectionView.m03() + projectionView.m02(), projectionView.m13() + projectionView.m12(), projectionView.m23() + projectionView.m22(), projectionView.m33() + projectionView.m32()).normalize();
        this.planes[5].set(projectionView.m03() - projectionView.m02(), projectionView.m13() - projectionView.m12(), projectionView.m23() - projectionView.m22(), projectionView.m33() - projectionView.m32()).normalize();

        return this;
    }

    @Override
    public boolean testPoint(float x, float y, float z, float r) {
        for(Plane3fc plane : this.planes)
            if(plane.testPoint(x, y, z) < -r)
                return false;

        return true;
    }
}