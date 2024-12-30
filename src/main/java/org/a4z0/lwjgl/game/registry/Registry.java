package org.a4z0.lwjgl.game.registry;

import java.util.Collection;

public interface Registry<T> {

    /**
    * @return true if it is empty, false otherwise.
    */

    boolean isEmpty();

    /**
    * @return the Values.
    */

    Collection<T> values();

    /**
    * Checks if this contains the given Value.
    *
    * @param value Value.
    *
    * @return true if it contains the Value, false otherwise.
    */

    boolean contains(T value);
}