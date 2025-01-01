package org.a4z0.lwjgl.game.util;

public class Timer {

    public static final double NANOSECONDS_IN_SECOND = 1.0E9d;

    protected long lastTime;
    protected long nextFrameTime;

    protected double frameInterval;
    protected double deltaTime;
    protected double count;

    protected int frames;
    protected int framesPerSecond;
    protected int targetFramesPerSecond;

    /**
    * Construct a {@link Timer}.
    */

    public Timer() {
        this(240);
    }

    /**
    * Construct a {@link Timer}.
    *
    * @param targetFPS Target Frames Per Second.
    */

    public Timer(int targetFPS) {
        this.setTargetFPS(targetFPS).lastTime = System.nanoTime();
    }

    /**
    * @return the target Frames per Second.
    */

    public int getTargetFPS() {
        return this.targetFramesPerSecond;
    }

    /**
    * Sets the target Frames per Second.
    *
    * @param targetFPS Target Frames Per Second.
    */

    public Timer setTargetFPS(int targetFPS) {
        this.targetFramesPerSecond = targetFPS;
        this.frameInterval = NANOSECONDS_IN_SECOND / this.targetFramesPerSecond;

        return this;
    }

    /**
    * @return the FPS.
    */

    public int getFPS() {
        return this.framesPerSecond;
    }

    /**
    * @return the Delta Time.
    */

    public double getDeltaTime() {
        return this.deltaTime;
    }

    /**
    * Updates this {@link Timer}.
    */

    public void update() {
        long currentTime = System.nanoTime();

        this.deltaTime = (currentTime - this.lastTime) / NANOSECONDS_IN_SECOND;
        this.lastTime = currentTime;

        this.count += this.deltaTime;
        this.frames++;

        if(this.count >= 1.0d) {
            this.framesPerSecond = this.frames;
            this.frames = 0;
            this.count -= 1.0d;
        }

        while(System.nanoTime() < this.nextFrameTime)
            Thread.onSpinWait();

        this.nextFrameTime += (long) this.frameInterval;

        if(System.nanoTime() > this.nextFrameTime)
            this.nextFrameTime = System.nanoTime() + (long) this.frameInterval;
    }
}