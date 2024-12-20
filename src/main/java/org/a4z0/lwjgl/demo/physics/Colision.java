package org.a4z0.lwjgl.demo.physics;

import org.a4z0.lwjgl.demo.math.aabb.AABBfc;
import org.a4z0.lwjgl.demo.math.vector.Vector3f;

public final class Colision {

    Colision() {}

    public static boolean grounded(Vector3f Pos) {
        return false;
    }

    public static int getLowerX(AABBfc Body) {
        return (int) Math.floor(Body.getLowerX());
    }

    public static int getUpperX(AABBfc Body) {
        return (int) Math.floor(Body.getUpperX());
    }

    public static int getLowerZ(AABBfc Body) {
        return (int) Math.floor(Body.getLowerZ());
    }

    public static int getUpperZ(AABBfc Body) {
        return (int) Math.floor(Body.getUpperZ());
    }
}