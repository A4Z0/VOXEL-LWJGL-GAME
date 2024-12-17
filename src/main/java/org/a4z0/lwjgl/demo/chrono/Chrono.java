package org.a4z0.lwjgl.demo.chrono;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

public final class Chrono {

    private int targetFps;
    private int frames;
    private int fps;
    private double frameInterval;
    private double lastTime;
    private double count;

    /**
    * Construct a {@link Chrono}.
    */

    public Chrono() {
        this.lastTime = glfwGetTime();
        this.setTargetFPS(60);
    }

    /**
    * Sets the Target Frames per Second.
    *
    * @param targetFps Target Frames per Second.
    */

    public void setTargetFPS(final int targetFps) {
        this.frameInterval = 1.0d / (this.targetFps = targetFps);
    }

    /**
    * @return the Target Frames per Second.
    */

    public int getTargetFPS() {
        return this.targetFps;
    }

    /**
    * @return the Frames per Second.
    */

    public int getFPS() {
        return this.fps;
    }

    /**
    * @return the Delta Time.
    */

    public double getDeltaTime() {
        return glfwGetTime() - this.lastTime;
    }

    /**
    * Updates this {@link Chrono}.
    */

    public boolean update() {
        double currentTime = glfwGetTime();
        double deltaTime = currentTime - this.lastTime;

        if(deltaTime >= this.frameInterval) {
            this.lastTime = currentTime;
            this.count += deltaTime;

            if(this.count >= 1.0) {
                this.fps = this.frames;
                this.frames = 0;
                this.count -= 1.0;
            }

            this.frames++;

            return true;
        }

        return false;
    }
}