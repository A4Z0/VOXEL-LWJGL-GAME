package org.a4z0.lwjgl.demo.registry;

import org.a4z0.lwjgl.demo.resource.Key;
import org.a4z0.lwjgl.demo.resource.ResourceKey;

import java.util.Optional;
import java.util.Set;

public interface ResourceRegistry<T> extends Registry<T> {

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
    * Checks if this contains the given Location Key.
    *
    * @param key Location Key.
    *
    * @return true if it contains the Location Key, false otherwise.
    */

    boolean contains(Key key);

    /**
    * Checks if this contains the given Resource Key.
    *
    * @param resourceKey Resource Key.
    *
    * @return true if it contains the Resource Key, false otherwise.
    */

    boolean contains(ResourceKey<T> resourceKey);

    /**
    * Checks if this contains the given Value.
    *
    * @param value Value.
    *
    * @return true if it contains the Value, false otherwise.
    */

    boolean contains(T value);

    /**
    * Retrieves a Location Key attached to the Value.
    *
    * @param value Value.
    *
    * @return an Optional of a {@link Key}.
    */

    Optional<Key> getKey(T value);

    /**
    * Retrieves a Location Key attached to the Value.
    *
    * @param value Value.
    *
    * @return a {@link Key} or throws an error.
    */

    Key getKeyOrThrow(T value);

    /**
    * Retrieves a Resource attached to the Value.
    *
    * @param value ...
    *
    * @return an Optional of a {@link ResourceKey}.
    */

    Optional<ResourceKey<T>> getResource(T value);

    /**
    * Retrieves a Resource attached to the Value.
    *
    * @param value ...
    *
    * @return a {@link ResourceKey} or throws an error.
    */

    ResourceKey<T> getResourceOrThrow(T value);

    /**
    * Retrieves a Value attached to the Resource Key.
    *
    * @param resourceKey Resource Key.
    *
    * @return an Optional of a Value.
    */

    Optional<T> get(ResourceKey<T> resourceKey);

    /**
    * Retrieves a Value attached to the Location Key.
    *
    * @param key Location Key.
    *
    * @return an Optional of a Value.
    */

    Optional<T> get(Key key);

    /**
    * Retrieves a Value attached to the Location Key or throws an error.
    *
    * @param key Location Key.
    *
    * @return a Value or throws an error.
    */

    T getOrThrow(Key key);

    /**
    * Retrieves a Value attached to the Resource Key or throws an error.
    *
    * @param resourceKey Resource Key.
    *
    * @return a Value or throws an error.
    */

    T getOrThrow(ResourceKey<T> resourceKey);

    /**
    * Register a Value.
    *
    * @param resourceKey Resource Key.
    * @param value Value.
    *
    * @return a {@link ResourceKey}.
    */

    ResourceKey<T> register(ResourceKey<T> resourceKey, T value);

    /**
    * Register a Value.
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