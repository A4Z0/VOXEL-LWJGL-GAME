package org.a4z0.lwjgl.demo.event.client.window;

import org.a4z0.lwjgl.demo.event.client.ClientEvent;

/**
* Called when the Window is resized.
*/

public final class WindowReziseEvent extends ClientEvent {

    private final int width, height;

    /**
    * Construct a {@link WindowReziseEvent}.
    *
    * @param width Width.
    * @param height Height.
    */

    public WindowReziseEvent(final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    /**
    * @return the Width.
    */

    public int getWidth() {
        return this.width;
    }

    /**
    * @return the Height.
    */

    public int getHeight() {
        return this.height;
    }
}