package org.a4z0.lwjgl.demo.event;

import java.lang.reflect.Method;
import java.util.*;

public final class EventBus {

    private final Map<Class<?>, List<RegisteredHandler>> REGISTERED_HANDLERS;

    /**
    * Construct a {@link EventBus}.
    */

    public EventBus() {
        this.REGISTERED_HANDLERS = new HashMap<>();
    }

    /**
    * Register a Listener.
    *
    * @param listener Listener.
    */

    public void register(Object listener) {
        for(Method method : listener.getClass().getDeclaredMethods()) {
            if(!method.isAnnotationPresent(EventHandler.class))
                continue;

            if(method.getParameterCount() != 1 || !Event.class.isAssignableFrom(method.getParameterTypes()[0]))
                continue;

            REGISTERED_HANDLERS.computeIfAbsent(method.getParameterTypes()[0], (_ignored) -> new ArrayList<>()).add(new RegisteredHandler(listener, (Class<? extends Event>) method.getParameterTypes()[0], method.getAnnotation(EventHandler.class).priority(), method));
        }

        REGISTERED_HANDLERS.values().forEach(handlers -> handlers.sort(Comparator.comparingInt(handler -> handler.getPriority().ordinal())));
    }

    /**
    * Submits an {@link Event}.
    *
    * @param event Event.
    */

    public void submit(Event event) {
        for(RegisteredHandler handler : REGISTERED_HANDLERS.computeIfAbsent(event.getClass(), (_ignored) -> new ArrayList<>())) {
            try {
                handler.execute(event);
            } catch (EventException e) {
                throw new RuntimeException(e);
            }
        }
    }
}