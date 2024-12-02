package org.a4z0.lwjgl.demo.entity;

import org.a4z0.lwjgl.demo.behavior.ClientCameraBehavior;
import org.a4z0.lwjgl.demo.util.Input;
import org.a4z0.lwjgl.demo.level.Level;
import org.a4z0.lwjgl.demo.level.Location;
import org.a4z0.lwjgl.demo.nbt.NBTTagCompound;
import org.a4z0.lwjgl.demo.math.AABBf;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;

public class EntityPlayer extends EntityLiving {

    public static final float GRAVITY_FORCE = 0.01f;

    public static final float DEFAULT_WALK_SPEED = 0.1f;
    public static final float DEFAULT_FLIGHT_SPEED = 0.1f;

    public static final AABBf DEFAULT_PLAYER_BODY = new AABBf(0f, 0f, 0f, 1f, 2f, 1f);

    protected boolean GROUNDED;
    protected boolean is_flying;
    protected boolean sprinting;

    private float vertical_speed_x;
    private float vertical_speed_z;

    private float horizontal_speed;

    private float sprint_speed;

    private float FALL_SPEED;

    /**
    * Construct a {@link EntityPlayer}.
    *
    * @param name {@link EntityPlayer}'s Name.
    * @param level {@link Level} this {@link EntityPlayer} will be at.
    */

    public EntityPlayer(@Deprecated String name, Level level) {
        this(name, level, 0f, 0f, 0f);
    }

    /**
    * Construct a {@link EntityPlayer}.
    *
    * @param name {@link EntityPlayer}'s Name.
    * @param level {@link Level} this {@link EntityPlayer} will be at.
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    public EntityPlayer(@Deprecated String name, Level level, float x, float y, float z) {
        this(name, level, x, y, z, 0f, 0f);
    }

    /**
    * Construct a {@link EntityPlayer}.
    *
    * @param name {@link EntityPlayer}'s Name.
    * @param level {@link Level} this {@link EntityPlayer} will be at.
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param yaw Yaw.
    * @param pitch Pitch.
    */

    public EntityPlayer(@Deprecated String name, Level level, float x, float y, float z, float yaw, float pitch) {
        this(name, new Location(level, x, y, z, yaw, pitch));
    }

    /**
    * Construct a {@link EntityPlayer}.
    *
    * @param name {@link EntityPlayer}'s Name.
    * @param location {@link Location} where this {@link EntityPlayer} will be.
    */

    public EntityPlayer(@Deprecated String name, Location location) {
        super(name, location);

        this.tag.set("attribute", new NBTTagCompound());

        this.setFlying(true);
        this.setWalkSpeed(DEFAULT_WALK_SPEED);
        this.setFlightSpeed(DEFAULT_FLIGHT_SPEED);
    }

    /**
    * @return true if it is on the ground, false otherwise.
    */

    public boolean isOnGround() {
        return this.GROUNDED;
    }

    /**
    * @return true if flying, false otherwise.
    */

    public boolean isFlying() {
        return this.is_flying;
    }

    /**
    * Sets if the {@link EntityPlayer} is flying.
    *
    * @param flying Flying?
    */

    public void setFlying(boolean flying) {
        this.is_flying = flying;
    }

    /**
    * @return true if sprinting, false otherwise.
    */

    public boolean isSprinting() {
        return this.sprinting;
    }

    /**
    * Sets if the {@link EntityPlayer} is sprinting.
    *
    * @param sprinting Sprinting?
    */

    public void setSprinting(boolean sprinting) {
        this.sprinting = sprinting;
    }

    /**
    * @return the Walk speed.
    */

    public float getWalkSpeed() {
        return this.tag.getFloat("attribute.walk_speed");
    }

    /**
    * Sets the Walk speed.
    *
    * @param walkSpeed Walk Speed to be set.
    */

    public void setWalkSpeed(float walkSpeed) {
        this.tag.setFloat("attribute.walk_speed", walkSpeed);
    }

    /**
    * @return the Flight Speed.
    */

    public float getFlightSpeed() {
        return this.tag.getFloat("attribute.flight_speed");
    }

    /**
    * Sets the Flight Speed.
    *
    * @param flightSpeed Flight Speed to be set.
    */

    public void setFlightSpeed(float flightSpeed) {
        this.tag.setFloat("attribute.flight_speed", flightSpeed);
    }

    @Override
    public void tick() {
        tickInput();
        tickMovement();
    }

    private void tickInput() {
        ClientCameraBehavior.tick();

        float vertical_speed_x = 0;
        float vertical_speed_z = 0;
        float horizontal_speed = 0;
        float sprint_speed = 1f;

        // Walk
        if(Input.isKeyDown(GLFW_KEY_W))
            vertical_speed_x += 1f;
        if(Input.isKeyDown(GLFW_KEY_A))
            vertical_speed_z += 1f;
        if(Input.isKeyDown(GLFW_KEY_S))
            vertical_speed_x -= 1f;
        if(Input.isKeyDown(GLFW_KEY_D))
            vertical_speed_z -= 1f;

        // Fly
        if(Input.isKeyPressed(GLFW_KEY_F))
            this.setFlying(!this.is_flying);
        if(Input.isKeyDown(GLFW_KEY_SPACE) && this.isFlying())
            horizontal_speed += 1f;
        if(Input.isKeyDown(GLFW_KEY_LEFT_SHIFT) && this.isFlying())
            horizontal_speed -= 1f;

        // Sprint
        if(Input.isKeyDown(GLFW_KEY_LEFT_CONTROL))
            sprint_speed = 2f;

        this.vertical_speed_x = vertical_speed_x;
        this.vertical_speed_z = vertical_speed_z;
        this.horizontal_speed = horizontal_speed;
        this.sprint_speed = sprint_speed;
    }

    private void tickMovement() {
        float vertical_speed_x = this.vertical_speed_x * sprint_speed * this.getWalkSpeed();
        float vertical_speed_z = this.vertical_speed_z * sprint_speed * this.getWalkSpeed();
        float horizontal_speed = this.horizontal_speed * sprint_speed * this.getFlightSpeed();

        float RADIANS = (float) Math.toRadians(this.getLocation().getYaw());

        float DIR_X = (float) Math.sin(-RADIANS);
        float DIR_Z = (float) Math.cos(RADIANS);

        this.getLocation().add(DIR_X * vertical_speed_x, 0, DIR_Z *  vertical_speed_x);
        this.getLocation().add(DIR_Z * vertical_speed_z, 0, -DIR_X * vertical_speed_z);
        this.getLocation().add(0, horizontal_speed, 0);
    }

    /*private void tickGravity() {
        this.FALL_SPEED += GRAVITY_FORCE;

        float MAX_FALL_SPEED = 0.03125f;

        float FALL_DISTANCE = Math.min(this.FALL_SPEED, MAX_FALL_SPEED);
        float REMAINING_FALL_SPEED = FALL_DISTANCE;

        while(REMAINING_FALL_SPEED > 0) {
            if(this.checkGroundCollision()) {
                float DISTANCE = Math.min(REMAINING_FALL_SPEED, 0.0625f);

                this.getLocation().subtract(0, DISTANCE, 0);

                if(this.checkGroundCollision()) {
                    this.GROUNDED = true;
                    this.FALL_SPEED = 0;

                    return;
                }

                REMAINING_FALL_SPEED -= DISTANCE;
            }
        }

        this.GROUNDED = false;
    }*/

    /*private boolean checkGroundCollision() {
        AABBfc CURRENT_BODY = DEFAULT_PLAYER_BODY.clone().add(this.getLocation()).divide(0.0625f);

        for(int x = (int) Math.floor(CURRENT_BODY.getLowerX()); x <= (int) Math.floor(CURRENT_BODY.getUpperX()); x++) {
            for(int z = (int) Math.floor(CURRENT_BODY.getLowerZ()); z <= (int) Math.floor(CURRENT_BODY.getUpperZ()); z++) {
                Voxel Voxel = Main.CHUNK.getVoxelAt(x, (int) (this.getLocation().getY() / 0.0625), z);

                if(Voxel.intersects(CURRENT_BODY)) {
                    return true;
                }
            }
        }

        return false;
    }*/
}