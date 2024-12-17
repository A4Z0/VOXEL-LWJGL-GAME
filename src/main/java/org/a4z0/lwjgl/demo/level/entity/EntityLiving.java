package org.a4z0.lwjgl.demo.level.entity;

import org.a4z0.lwjgl.demo.math.vector.Vector2f;
import org.a4z0.lwjgl.demo.math.vector.Vector3f;

public abstract class EntityLiving extends Entity {

    protected final Vector2f rotation;
    protected String name;

    /**
    * Construct a {@link EntityLiving}.
    *
    * @param name Name.
    */

    EntityLiving(String name) {
        this(name, 0f, 0f, 0f);
    }

    /**
    * Construct a {@link EntityLiving}.
    *
    * @param name Name.
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    EntityLiving(String name, float x, float y, float z) {
        this(name, x, y, z, 0f, 0f);
    }

    /**
    * Construct a {@link EntityLiving}.
    *
    * @param name Name.
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param yaw Yaw.
    * @param pitch Pitch.
    */

    EntityLiving(String name, float x, float y, float z, float yaw, float pitch) {
        this(name, new Vector3f(x, y, z), new Vector2f(yaw, pitch));
    }

    /**
    * Construct a {@link EntityLiving}.
    *
    * @param name Name.
    * @param position Position.
    * @param rotation Rotation.
    */

    EntityLiving(String name, Vector3f position, Vector2f rotation) {
        super(position);

        this.rotation = rotation;
        this.name = name;
    }

    /**
    * Retrieves the Rotation.
    *
    * @return the Rotation.
    */

    public float getYaw() {
        return this.rotation.getX();
    }

    public void setYaw(float yaw) {
        this.rotation.set(yaw % 360, getPitch());
    }

    public float getPitch() {
        return this.rotation.getY();
    }

    public void setPitch(float pitch) {
        this.rotation.set(getYaw(), Math.min(Math.max(pitch, -89.999f), 89.999f));
    }

    /**
    * Retrieves the Name.
    *
    * @return the Name.
    */

    public String getName() {
        return this.name;
    }

    /**
    * Sets the Name.
    *
    * @param name Name to be set.
    */

    public void setName(String name) {
        this.name = name;
    }
}