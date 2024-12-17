package org.a4z0.lwjgl.demo.registry;

import org.a4z0.lwjgl.demo.resource.Key;
import org.a4z0.lwjgl.demo.resource.ResourceKey;

import java.util.*;

public final class MappingRegistry<T> implements Registry<T> {

    private final ResourceKey<? extends Registry<T>> resourceKey;

    private final Map<Key, T> queryWithKey;
    private final Map<ResourceKey<T>, T> queryWithResource;

    /**
    * Construct a {@link MappingRegistry}.
    *
    * @param resourceKey Resource Key.
    */

    public MappingRegistry(ResourceKey<? extends Registry<T>> resourceKey) {
        this.resourceKey = resourceKey;
        this.queryWithKey = new LinkedHashMap<>();
        this.queryWithResource = new LinkedHashMap<>();
    }

    @Override
    public ResourceKey<? extends Registry<T>> getKey() {
        return this.resourceKey;
    }

    @Override
    public Set<Key> getKeys() {
        return this.queryWithKey.keySet();
    }

    @Override
    public Set<ResourceKey<T>> getResources() {
        return this.queryWithResource.keySet();
    }

    @Override
    public Collection<T> getValues() {
        return this.queryWithKey.values();
    }

    @Override
    public T get(ResourceKey<T> resourceKey) {
        return this.queryWithResource.get(resourceKey);
    }

    @Override
    public T get(Key key) {
        return this.queryWithKey.get(key);
    }

    @Override
    public ResourceKey<T> register(ResourceKey<T> resourceKey, T value) {
        this.queryWithResource.put(resourceKey, value);
        this.queryWithKey.put(resourceKey.getLocation(), value);

        return resourceKey;
    }

    @Override
    public void unregister(ResourceKey<T> resourceKey) {
        this.queryWithResource.remove(resourceKey);
        this.queryWithKey.remove(resourceKey.getLocation());
    }
}