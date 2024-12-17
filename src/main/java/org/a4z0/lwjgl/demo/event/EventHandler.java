package org.a4z0.lwjgl.demo.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventHandler {

    /**
    * @return the {@link EventPriority Priority}.
    */

    EventPriority priority() default EventPriority.NORMAL;
}