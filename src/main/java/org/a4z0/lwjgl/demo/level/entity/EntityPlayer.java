package org.a4z0.lwjgl.demo.level.entity;

import org.a4z0.lwjgl.demo.math.vector.Vector2f;
import org.a4z0.lwjgl.demo.math.vector.Vector3f;
import org.a4z0.lwjgl.demo.physics.Colision;
import org.a4z0.lwjgl.demo.math.aabb.AABBf;

public class EntityPlayer extends EntityLiving {

    public static final float GRAVITY_FORCE = 0.01f;

    public static final float DEFAULT_WALK_SPEED = 0.1f;
    public static final float DEFAULT_FLIGHT_SPEED = 0.1f;

    public static final AABBf DEFAULT_PLAYER_BODY = new AABBf(0f, 0, 0f, 1f, 2f, 1f);
    public static final AABBf DEFAULT_PLAYER_FEET = new AABBf(0f, 0f, 0f, 1f, 0.0625f, 1f);

    private boolean is_flying;
    private boolean is_falling;
    private boolean is_sprinting;

    private float walk_speed;
    private float sprint_speed;
    private float flight_speed;

    private float vertical_speed_x;
    private float vertical_speed_z;
    private float horizontal_speed;

    public float fall_speed;

    /**
    * Construct a {@link EntityPlayer}.
    *
    * @param name Name.
    */

    public EntityPlayer(String name) {
        this(name, 0f, 0f, 0f);
    }

    /**
    * Construct a {@link EntityPlayer}.
    *
    * @param name Name.
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    public EntityPlayer(String name, float x, float y, float z) {
        this(name, x, y, z, 0f, 0f);
    }

    /**
    * Construct a {@link EntityPlayer}.
    *
    * @param name Name.
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param yaw Yaw.
    * @param pitch Pitch.
    */

    public EntityPlayer(String name, float x, float y, float z, float yaw, float pitch) {
        this(name, new Vector3f(x, y, z), new Vector2f(yaw, pitch));
    }

    /**
    * Construct a {@link EntityPlayer}.
    *
    * @param name Name.
    * @param position Position.
    */

    public EntityPlayer(String name, Vector3f position, Vector2f rotation) {
        super(name, position, rotation);

        this.setFlying(true);
        this.setWalkSpeed(DEFAULT_WALK_SPEED);
        this.setFlightSpeed(DEFAULT_FLIGHT_SPEED);
    }

    /**
    * @return true if it is on the ground, false otherwise.
    */

    public boolean isOnGround() {
        return !this.is_falling;
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
        return this.is_sprinting;
    }

    /**
    * Sets if the {@link EntityPlayer} is sprinting.
    *
    * @param sprinting Sprinting?
    */

    public void setSprinting(boolean sprinting) {
        this.is_sprinting = sprinting;
    }

    /**
    * @return the Walk speed.
    */

    public float getWalkSpeed() {
        return this.walk_speed;
    }

    /**
    * Sets the Walk speed.
    *
    * @param walkSpeed Walk Speed to be set.
    */

    public void setWalkSpeed(float walkSpeed) {
        this.walk_speed = walkSpeed;
    }

    /**
    * @return the Flight Speed.
    */

    public float getFlightSpeed() {
        return this.flight_speed;
    }

    /**
    * Sets the Flight Speed.
    *
    * @param flightSpeed Flight Speed to be set.
    */

    public void setFlightSpeed(float flightSpeed) {
        this.flight_speed = flightSpeed;
    }

    @Override
    public void tick() {
        tickInput();
        tickMovement();
        tickGravity();
    }

    private void tickInput() {
        /*ClientCameraBehavior.tick();

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
        /*if(Input.isKeyPressed(GLFW_KEY_F))
            this.setFlying(!this.is_flying);*/
        /*if(Input.isKeyDown(GLFW_KEY_SPACE) && this.isFlying())
            horizontal_speed += 1f;
        if(Input.isKeyDown(GLFW_KEY_LEFT_SHIFT) && this.isFlying())
            horizontal_speed -= 1f;

        // Sprint
        if(Input.isKeyDown(GLFW_KEY_LEFT_CONTROL))
            sprint_speed = 2f;

        this.vertical_speed_x = vertical_speed_x;
        this.vertical_speed_z = vertical_speed_z;
        this.horizontal_speed = horizontal_speed;
        this.sprint_speed = sprint_speed;*/
    }

    private void tickMovement() {
        float vertical_speed_x = this.vertical_speed_x * sprint_speed * this.getWalkSpeed();
        float vertical_speed_z = this.vertical_speed_z * sprint_speed * this.getWalkSpeed();
        float horizontal_speed = this.horizontal_speed * sprint_speed * this.getFlightSpeed();

        float RADIANS = (float) Math.toRadians(this.getYaw());

        float DIR_X = (float) Math.sin(-RADIANS);
        float DIR_Z = (float) Math.cos(RADIANS);

        this.getPosition().add(DIR_X * vertical_speed_x, 0, DIR_Z *  vertical_speed_x);
        this.getPosition().add(DIR_Z * vertical_speed_z, 0, -DIR_X * vertical_speed_z);
        this.getPosition().add(0, horizontal_speed, 0);
    }

    private void tickGravity() {
        if(this.is_flying) {
            this.is_falling = false;
            this.fall_speed = 0;

            return;
        }

        this.is_falling = !Colision.grounded(this.getPosition());

        if(!this.is_falling)
            return;

        this.fall_speed += GRAVITY_FORCE;
        this.fall_speed = Math.min(this.fall_speed, 0.03125f);

        float remaining_fall_speed = this.fall_speed;

        while(remaining_fall_speed > 0) {
            float distance = Math.min(remaining_fall_speed, 0.0625f);

            this.getPosition().subtract(0, remaining_fall_speed, 0);

            if(Colision.grounded(this.getPosition())) {
                this.is_falling = false;
                this.fall_speed = 0;

                return;
            }

            remaining_fall_speed -= distance;
        }
    }
}