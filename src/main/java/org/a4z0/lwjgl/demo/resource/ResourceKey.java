package org.a4z0.lwjgl.demo.resource;

import com.google.common.collect.MapMaker;
import org.a4z0.lwjgl.demo.util.Pair;

import java.util.Objects;
import java.util.concurrent.ConcurrentMap;

public class ResourceKey<T> {

    private static final Key ROOT_REGISTRY_NAME = Key.of("root");
    private static final ConcurrentMap<Pair<Key, Key>, ResourceKey<?>> RESOURCES = (new MapMaker()).weakValues().makeMap();

    protected final Key registry;
    protected final Key location;

    /**
    * Construct a {@link ResourceKey}.
    *
    * @param registry Registry Key.
    * @param location Location Key.
    */

    protected ResourceKey(final Key registry, final Key location) {
        this.registry = registry;
        this.location = location;
    }

    /**
    * @return the Registry Key.
    */

    public Key getRegistry() {
        return this.registry;
    }

    /**
    * @return the Location Key.
    */

    public Key getLocation() {
        return this.location;
    }

    /**
    * Checks if this is equals to the given {@link Object}.
    *
    * @param o {@link Object} to be compared.
    *
    * @return true if equal, false otherwise.
    */

    @Override
    public boolean equals(Object o) {
        return (o instanceof ResourceKey)
            && ((ResourceKey<?>) o).getRegistry().equals(this.getRegistry())
            && ((ResourceKey<?>) o).getLocation().equals(this.getLocation());
    }

    /**
    * @return this as a {@link String}.
    */

    @Override
    public String toString() {
        return "ResourceKey["
            + "\"Registry\": \"" + this.getRegistry() + "\""
            + ", "
            + "\"Location\": \"" + this.getLocation() + "\""
            + "]";
    }

    /**
    * @return the Hashcode.
    */

    @Override
    public int hashCode() {
        return Objects.hash(this.getRegistry(), this.getLocation());
    }

    /**
    * Construct a {@link ResourceKey}.
    *
    * @param location Location Key.
    *
    * @return a new {@link ResourceKey}.
    */

    public static <T> ResourceKey<T> create(final Key location) {
        return ResourceKey.create(ResourceKey.ROOT_REGISTRY_NAME, location);
    }

    /**
    * Construct a {@link ResourceKey}.
    *
    * @param registry Registry Key.
    * @param location Location Key.
    *
    * @return a new {@link ResourceKey}.
    */

    public static <T> ResourceKey<T> create(final Key registry, final Key location) {
        //noinspection unchecked
        return (ResourceKey<T>) RESOURCES.computeIfAbsent(Pair.of(registry, location), (resource) -> {
            return new ResourceKey<>(resource.getFirst(), resource.getSecond());
        });
    }
}