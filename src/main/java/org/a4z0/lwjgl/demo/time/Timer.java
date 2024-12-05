package org.a4z0.lwjgl.demo.time;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

public final class Timer {

    private double LastTime;
    private double Count;

    private int Frames;
    private int FPS;

    private int Updates;
    private int UPS;

    /**
    * Construct a {@link Timer}.
    */

    public Timer() {
        this.LastTime = glfwGetTime();
    }

    /**
    * @return the Delta Time.
    */

    public double getDeltaTime() {
        double CurrentTime = glfwGetTime();
        double DeltaTime = CurrentTime - this.LastTime;

        this.LastTime = CurrentTime;
        this.Count += DeltaTime;

        return DeltaTime;
    }

    /**
    * Updates the Frames per Second.
    */

    public void updateFPS() {
        this.Frames++;
    }

    /**
    * @return the Frames per Second.
    */

    public double getFPS() {
        return this.FPS;
    }

    /**
    * Updates the Updates per Second.
    */

    public void updateUPS() {
        this.Updates++;
    }

    /**
    * @return the Updates per Second.
    */

    public double getUPS() {
        return this.UPS;
    }

    /**
    * Updates the Frames per Second and Updates per Second if a second has passed.
    */

    public void update() {
        if(this.Count > 1) {
            this.FPS = this.Frames;
            this.Frames = 0;

            this.UPS = this.Updates;
            this.Updates = 0;

            this.Count -= 1;
        }
    }
}