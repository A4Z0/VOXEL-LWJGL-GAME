package org.a4z0.lwjgl.demo.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class RegisteredHandler {

    private final Object eventListener;
    private final Class<? extends Event> eventType;
    private final Method eventHandler;
    private final EventPriority eventPriority;

    /**
    * Construct a {@link RegisteredHandler}.
    *
    * @param eventListener Listener.
    * @param eventType Type.
    * @param eventPriority Priority.
    * @param eventHandler Handler.
    */

    RegisteredHandler(Object eventListener, Class<? extends Event> eventType, EventPriority eventPriority, Method eventHandler) {
        // Ensure that the method can be accessed.
        if(!eventHandler.canAccess(eventListener))
            eventHandler.setAccessible(true);

        this.eventListener = eventListener;
        this.eventType = eventType;
        this.eventHandler = eventHandler;
        this.eventPriority = eventPriority;
    }

    /**
    * @return the Priority.
    */

    public EventPriority getPriority() {
        return this.eventPriority;
    }

    /**
    * Executes the Handler.
    *
    * @param event Event to be sent.
    *
    * @throws EventException When an exception on execution occurs.
    */

    void execute(final Event event) throws EventException {
        try {
            this.eventHandler.invoke(this.eventListener, event);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // If it somehow fails, just throws.
            throw new EventException("Couldn't execute -> " + this, e);
        }
    }

    /**
    * @return this {@link RegisteredHandler} as a {@link String}.
    */

    @Override
    public String toString() {
        return
        "\""
        + this.eventListener.getClass().getSimpleName()
        + ":"
        + this.eventHandler.getName()
        + ":"
        + this.eventType.getSimpleName()
        + "\"";
    }
}