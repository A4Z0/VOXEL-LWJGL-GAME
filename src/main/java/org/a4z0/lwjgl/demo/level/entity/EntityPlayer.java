package org.a4z0.lwjgl.demo.level.entity;

import org.a4z0.api.level.entity.Player;
import org.a4z0.api.math.Location;
import org.a4z0.api.text.TextComponent;

public class EntityPlayer implements Player {

    public static final float DEFAULT_WALK_SPEED = 0.1f;
    public static final float DEFAULT_FLIGHT_SPEED = 0.1f;

    protected TextComponent name;

    protected final Location location;

    protected boolean isOnGround;
    protected boolean isFlying = true;
    protected boolean isSprinting;

    protected float walkSpeed;
    protected float flightSpeed;

    /**
    * Construct a {@link EntityPlayer}.
    */

    public EntityPlayer() {
        this(0, 0, 0, 90, 0);
    }

    /**
    * Construct a {@link EntityPlayer}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param pitch Pitch.
    * @param yaw Yaw.
    */

    public EntityPlayer(float x, float y, float z, float yaw, float pitch) {
        this(TextComponent.empty(), x, y, z, yaw, pitch);
    }

    /**
    * Construct a {@link EntityPlayer}.
    *
    * @param name Name.
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param pitch Pitch.
    * @param yaw Yaw.
    */

    public EntityPlayer(TextComponent name, float x, float y, float z, float yaw, float pitch) {
        this(name, new Location(x, y, z, yaw, pitch));
    }

    /**
    * Construct a {@link EntityPlayer}.
    *
    * @param name Name.
    * @param location Location.
    */

    public EntityPlayer(TextComponent name, Location location) {
        this.name = name;
        this.location = location;

        this.setFlight(true);
        this.setWalkSpeed(DEFAULT_WALK_SPEED);
        this.setFlightSpeed(DEFAULT_FLIGHT_SPEED);
    }

    @Override
    public TextComponent getName() {
        return this.name;
    }

    @Override
    public void setName(TextComponent name) {
        this.name = name;
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public boolean isOnGround() {
        return this.isOnGround;
    }

    @Override
    public boolean isFlying() {
        return this.isFlying;
    }

    @Override
    public void setFlight(boolean flight) {
        this.isFlying = flight;
    }

    @Override
    public boolean isSprinting() {
        return this.isSprinting;
    }

    @Override
    public void setSprinting(boolean sprinting) {
        this.isSprinting = sprinting;
    }

    @Override
    public float getWalkSpeed() {
        return this.walkSpeed;
    }

    @Override
    public void setWalkSpeed(float walkSpeed) {
        this.walkSpeed = walkSpeed;
    }

    @Override
    public float getFlightSpeed() {
        return this.flightSpeed;
    }

    @Override
    public void setFlightSpeed(float flightSpeed) {
        this.flightSpeed = flightSpeed;
    }
}