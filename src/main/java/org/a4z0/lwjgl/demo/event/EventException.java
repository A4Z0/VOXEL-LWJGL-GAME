package org.a4z0.lwjgl.demo.event;

public final class EventException extends Exception {

    /**
    * Construct a {@link EventException}.
    *
    * @param message Message.
    */

    public EventException(String message) {
        super(message);
    }

    /**
    * Construct a {@link EventException}.
    *
    * @param message Message.
    * @param cause Cause.
    */

    public EventException(String message, Throwable cause) {
        super(message, cause);
    }
}
