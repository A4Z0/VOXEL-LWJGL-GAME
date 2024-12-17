package org.a4z0.lwjgl.demo.level.entity;

import org.a4z0.lwjgl.demo.math.vector.Vector3f;

public abstract class Entity {

    protected final Vector3f position;

    /**
    * Construct a {@link Entity}.
    */

    Entity() {
        this(0f, 0f, 0f);
    }

    /**
    * Construct a {@link Entity}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    Entity(float x, float y, float z) {
        this(new Vector3f(x, y, z));
    }

    /**
    * Construct a {@link Entity}.
    *
    * @param position Position.
    */

    Entity(Vector3f position) {
        this.position = position;
    }

    /**
    * @return the Position.
    */

    public Vector3f getPosition() {
        return this.position;
    }

    /**
    * Ticks this {@link Entity}.
    */

    public abstract void tick();
}
