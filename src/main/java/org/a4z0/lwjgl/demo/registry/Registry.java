package org.a4z0.lwjgl.demo.registry;

import org.a4z0.lwjgl.demo.resource.Key;
import org.a4z0.lwjgl.demo.resource.ResourceKey;

import java.util.Collection;
import java.util.Set;

public interface Registry<T> {

    /**
    * @return the Resource Key of this Registry.
    */

    ResourceKey<? extends Registry<T>> getKey();

    /**
    * @return the Location Keys.
    */

    Set<Key> getKeys();

    /**
    * @return the Resource Keys.
    */

    Set<ResourceKey<T>> getResources();

    /**
    * @return ...
    */

    Collection<T> getValues();

    /**
    * Retrieves a Resource attached to the Resource Key.
    *
    * @param resourceKey Resource Key.
    *
    * @return a Value or null.
    */

    T get(ResourceKey<T> resourceKey);

    /**
    * Retrieves a Resource attached to the Location Key.
    *
    * @param key Location Key.
    *
    * @return a Value or null.
    */

    T get(Key key);

    /**
    * Retrieves a Resource attached to the Resource Key or throws an error.
    *
    * @param resourceKey Resource Key.
    *
    * @return a Value.
    */

    default T getOrThrow(ResourceKey<T> resourceKey) {
        T entry = this.get(resourceKey);

        if(entry == null)
            throw new IllegalStateException("Unable to find a Resource at: " + this.getKey() + " -> " + resourceKey);

        return entry;
    }

    /**
    * Retrieves a Resource attached to the Location Key or throws an error.
    *
    * @param key Location Key.
    *
    * @return a Value.
    */

    default T getOrThrow(Key key) {
        T entry = this.get(key);

        if(entry == null)
            throw new IllegalStateException("Unable to find a Resource at: " + this.getKey() + " -> " + key);

        return entry;
    }

    /**
    * Register a Resource to a Resource Key.
    *
    * @param resourceKey Resource Key.
    * @param value Value.
    *
    * @return a {@link ResourceKey}.
    */

    ResourceKey<T> register(ResourceKey<T> resourceKey, T value);

    /**
    * Register a Resource to a Location Key.
    *
    * @param key Location Key.
    * @param value Value.
    *
    * @return a {@link ResourceKey}.
    */

    default ResourceKey<T> register(Key key, T value) {
        return this.register(ResourceKey.create(this.getKey().getLocation(), key), value);
    }

    /**
    * Unregister a Resource from a Resource Key.
    *
    * @param resourceKey Resource Key.
    */

    void unregister(ResourceKey<T> resourceKey);

    /**
    * Unregister a Resource from a Location Key.
    *
    * @param key Location Key.
    */

    default void unregister(Key key) {
        this.unregister(ResourceKey.create(this.getKey().getLocation(), key));
    }
}