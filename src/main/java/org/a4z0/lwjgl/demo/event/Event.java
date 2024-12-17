package org.a4z0.lwjgl.demo.event;

public abstract class Event {

    /**
    * Construct a {@link Event}.
    */

    protected Event() {}

    /**
    * @return the Name.
    */

    public final String getName() {
        return this.getClass().getSimpleName();
    }
}