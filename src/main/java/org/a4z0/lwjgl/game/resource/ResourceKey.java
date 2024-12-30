package org.a4z0.lwjgl.game.resource;

import com.google.common.collect.MapMaker;
import org.a4z0.lwjgl.Key;
import org.a4z0.lwjgl.Pair;

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

    @Override
    public boolean equals(Object o) {
        return (o instanceof ResourceKey)
            && ((ResourceKey<?>) o).getRegistry().equals(this.getRegistry())
            && ((ResourceKey<?>) o).getLocation().equals(this.getLocation());
    }

    @Override
    public String toString() {
        return "ResourceKey["
            + "\"Registry\": \"" + this.getRegistry() + "\""
            + ", "
            + "\"Location\": \"" + this.getLocation() + "\""
            + "]";
    }

    @Override
    public int hashCode() {
        return 31 * ((31 * this.getRegistry().hashCode()) + this.getLocation().hashCode());
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