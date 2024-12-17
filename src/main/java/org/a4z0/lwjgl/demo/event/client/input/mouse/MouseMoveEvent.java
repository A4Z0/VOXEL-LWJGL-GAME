package org.a4z0.lwjgl.demo.event.client.input.mouse;

import org.a4z0.lwjgl.demo.event.Event;

public final class MouseMoveEvent extends Event {

    private final double x, y;

    /**
    * Construct a {@link MouseMoveEvent}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    */

    public MouseMoveEvent(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
    * @return the Z-Axis.
    */

    public double getX() {
        return this.x;
    }

    /**
    * @return the Y-Axis.
    */

    public double getY() {
        return this.y;
    }
}
