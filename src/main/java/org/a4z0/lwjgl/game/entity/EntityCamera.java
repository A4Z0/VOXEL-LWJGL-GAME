package org.a4z0.lwjgl.game.entity;

import org.a4z0.lwjgl.api.entity.Camera;
import org.a4z0.lwjgl.api.entity.EntityLiving;
import org.a4z0.lwjgl.api.location.Location3f;

public class EntityCamera implements Camera {

    public static final float DEFAULT_YAW = -90f;
    public static final float DEFAULT_PITCH = 0f;

    protected final Location3f location;

    protected EntityLiving e;

    /**
    * Construct a {@link EntityCamera}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    public EntityCamera(float x, float y, float z) {
        this(x, y, z, DEFAULT_YAW, DEFAULT_PITCH);
    }

    /**
    * Construct a {@link EntityCamera}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param yaw Yaw.
    * @param pitch Pitch.
    */

    public EntityCamera(float x, float y, float z, float yaw, float pitch) {
        this(new Location3f(x, y, z, yaw, pitch));
    }

    /**
    * Construct a {@link EntityCamera}.
    *
    * @param location Location.
    */

    public EntityCamera(Location3f location) {
        this.location = location;
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public Location3f getLocation() {
        return this.location;
    }

    /**
    * ...
    */

    public void attach(EntityLiving e) {

    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof EntityCamera) && ((EntityCamera) o).getID() == this.getID();
    }

    @Override
    public String toString() {
        return "EntityCamera{" + "\"Location\": " + this.getLocation() + "}";
    }

    @Override
    public int hashCode() {
        return 31 * this.getID();
    }

    /**
    * Ticks this {@link Camera}.
    */

    public void tick() {

    }
}