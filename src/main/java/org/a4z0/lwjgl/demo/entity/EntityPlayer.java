package org.a4z0.lwjgl.demo.entity;

import org.a4z0.api.entity.Player;
import org.a4z0.api.level.block.BlockState;
import org.a4z0.api.location.Location3f;
import org.a4z0.api.location.Location3fc;
import org.a4z0.math.aabb.AABBf;
import org.a4z0.math.vector.Vector3f;
import org.a4z0.api.text.TextComponent;
import org.a4z0.lwjgl.demo.camera.FreeCamera;
import org.a4z0.lwjgl.demo.colision.Collision;

public class EntityPlayer implements Player {

    public static final AABBf DEFAULT_PLAYER_BODY = new AABBf(-0.45f, -0.5f, -0.45f, 0.45f, 1.5f, 0.45f);
    public static final AABBf DEFAULT_BLOCK_BODY = new AABBf(-0.5f, -0.5f, -0.5f, 0.5f, 0.5f, 0.5f);

    public static final float DEFAULT_WALK_SPEED = 0.01f;
    public static final float DEFAULT_FLIGHT_SPEED = 0.01f;
    public static final float DEFAULT_JUMP_FORCE = 2f;

    public static final float MOVEMENT_STEP = 0.1f;
    public static final float JUMP_STEP = 0.02f;

    private static final float GRAVITY = 0.00007f; // Gravidade base
    private static final float TERMINAL_VELOCITY = 0.02f; // Velocidade máxima de queda
    private static final float AIR_RESISTANCE = 0.98f; // Resistência ao ar (quanto menor, mais resistente)

    protected TextComponent name;

    protected final Location3f location;

    protected boolean isOnGround;
    protected boolean isFlying = true;
    protected boolean isSprinting;

    protected float walkSpeed;
    protected float flightSpeed;

    protected final FreeCamera camera = new FreeCamera();


    protected float fallSpeed;

    protected float jumpForce;

    protected Vector3f velocity = new Vector3f();

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
        this(name, new Location3f(x, y, z, yaw, pitch));
    }

    /**
    * Construct a {@link EntityPlayer}.
    *
    * @param name Name.
    * @param location Location.
    */

    public EntityPlayer(TextComponent name, Location3f location) {
        this.name = name;
        this.location = location;

        this.setFlight(false);
        this.setWalkSpeed(DEFAULT_WALK_SPEED);
        this.setFlightSpeed(DEFAULT_FLIGHT_SPEED);
    }

    @Override
    public int getID() {
        return 0;
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
    public Location3f getLocation() {
        return this.location;
    }

    /**
    * @return the Velocity.
    */

    public Vector3f getVelocity() {
        return this.velocity;
    }

    @Override
    public boolean isAlive() {
        return true;
    }

    /**
    * @return the Camera.
    */

    public FreeCamera getCamera() {
        return this.camera;
    }

    /**
    * @return the Body.
    */

    public AABBf getBody() {
        return DEFAULT_PLAYER_BODY.clone().add(this.getLocation().getX(), this.getLocation().getY(), this.getLocation().getZ());
    }

    @Override
    public boolean isOnGround() {
        return Collision.Bottom(this.getBody());
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

    /**
    * ...
    */

    public void jump() {
        this.jump(DEFAULT_JUMP_FORCE);
    }

    /**
    * ...
    *
    * @param jumpForce ...
    */

    public void jump(float jumpForce) {
        if(!this.isOnGround() || this.isFlying())
            return;

        this.jumpForce = jumpForce;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof EntityPlayer) && ((EntityPlayer) o).getID() == this.getID();
    }

    @Override
    public String toString() {
        return "EntityPlayer{" + "\"Location\": " + this.getLocation() + "}";
    }

    @Override
    public int hashCode() {
        return 31 * this.getID();
    }

    /**
    * Ticks this {@link EntityPlayer}.
    */

    public void tick() {
        tickMovement();
        tickJump();
        tickFall();

        // Camera.
        this.camera.set(this.getLocation().clone().add(0, 1f, 0));
    }

    private void tickMovement() {
        while(this.getVelocity().getX() != 0) {
            float step = Math.min(this.getVelocity().getX(), MOVEMENT_STEP);

            this.getLocation().add(step, 0, 0);
            this.getVelocity().subtract(step, 0, 0);
        }

        while(this.getVelocity().getZ() != 0) {
            float step = Math.min(this.getVelocity().getZ(), MOVEMENT_STEP);

            this.getLocation().add(0, 0, step);
            this.getVelocity().subtract(0, 0, step);
        }

        while(this.getVelocity().getY() != 0) {
            float step = Math.min(this.getVelocity().getY(), MOVEMENT_STEP);

            this.getLocation().add(0, step, 0);
            this.getVelocity().subtract(0, step, 0);
        }
    }

    private void tickFall() {
        if(this.isFlying() || this.isOnGround()) {
            this.fallSpeed = 0;

            return;
        }

        // Incrementar a velocidade de queda com gravidade, respeitando o limite terminal
        this.fallSpeed = Math.min(this.fallSpeed + GRAVITY, TERMINAL_VELOCITY);

        // Adicionar a alteração ao eixo Y no final
        this.getVelocity().subtract(0, this.fallSpeed * AIR_RESISTANCE, 0);
    }

    private void tickJump() {
        if(this.jumpForce <= 0)
            return;

        // Reduzir a força de salto pela resistência ao ar
        this.jumpForce = Math.max(this.jumpForce * AIR_RESISTANCE, 0);

        // Garantir que o salto respeite a velocidade terminal no eixo Y
        float jumpStep = Math.min(this.jumpForce, TERMINAL_VELOCITY);

        // Aplicar o incremento ao vetor de velocidade
        this.getVelocity().add(0, jumpStep, 0);

        // Reduzir a força restante do salto
        this.jumpForce = Math.max(this.jumpForce - jumpStep, 0f);
    }
}