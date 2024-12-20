package org.a4z0.lwjgl.demo.registry;

import java.util.Collection;

public interface Registry<T> {

    /**
    * @return ...
    */

    Collection<T> getValues();
}