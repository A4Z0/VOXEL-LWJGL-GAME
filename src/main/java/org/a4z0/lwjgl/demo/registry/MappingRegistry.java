package org.a4z0.lwjgl.demo.registry;

import org.a4z0.lwjgl.demo.resource.Key;
import org.a4z0.lwjgl.demo.resource.ResourceKey;

import java.util.*;

public final class MappingRegistry<T> implements ResourceRegistry<T> {

    private final ResourceKey<? extends Registry<T>> resourceKey;

    private final Map<ResourceKey<T>, T> queryValueWithResource;
    private final Map<Key, ResourceKey<T>> queryResourceWithKey;

    /**
    * Construct a {@link MappingRegistry}.
    *
    * @param resourceKey Resource Key.
    */

    public MappingRegistry(ResourceKey<? extends Registry<T>> resourceKey) {
        this.resourceKey = resourceKey;

        this.queryValueWithResource = new LinkedHashMap<>();
        this.queryResourceWithKey = new LinkedHashMap<>();
    }

    @Override
    public ResourceKey<? extends Registry<T>> getKey() {
        return this.resourceKey;
    }

    @Override
    public Set<Key> getKeys() {
        return this.queryResourceWithKey.keySet();
    }

    @Override
    public Set<ResourceKey<T>> getResources() {
        return this.queryValueWithResource.keySet();
    }

    @Override
    public Collection<T> getValues() {
        return this.queryValueWithResource.values();
    }

    @Override
    public boolean contains(Key key) {
        return this.queryResourceWithKey.containsKey(key);
    }

    @Override
    public boolean contains(ResourceKey<T> resourceKey) {
        return this.queryValueWithResource.containsKey(resourceKey);
    }

    @Override
    public boolean contains(T value) {
        return this.queryValueWithResource.containsValue(value);
    }

    @Override
    public Optional<Key> getKey(T value) {
        return Optional.of(this.getResource(value)).get().map(ResourceKey::getLocation);
    }

    @Override
    public Key getKeyOrThrow(T value) {
        return this.getKey(value).orElseThrow(() -> new RuntimeException("Couldn't find the Key."));
    }

    @Override
    public Optional<ResourceKey<T>> getResource(T value) {
        return this.queryValueWithResource.entrySet().stream().filter(e -> Objects.equals(e.getValue(), value)).map(Map.Entry::getKey).findFirst();
    }

    @Override
    public ResourceKey<T> getResourceOrThrow(T value) {
        return this.getResource(value).orElseThrow(() -> new RuntimeException("Couldn't find the Resource Key."));
    }

    @Override
    public Optional<T> get(ResourceKey<T> resourceKey) {
        return Optional.ofNullable(this.queryValueWithResource.get(resourceKey));
    }

    @Override
    public Optional<T> get(Key key) {
        return this.get(this.queryResourceWithKey.get(key));
    }

    @Override
    public T getOrThrow(Key key) {
        return this.getOrThrow(this.queryResourceWithKey.get(key));
    }

    @Override
    public T getOrThrow(ResourceKey<T> resourceKey) {
        return this.get(resourceKey).orElseThrow(() -> new IllegalStateException("Couldn't find the Value."));
    }

    @Override
    public ResourceKey<T> register(ResourceKey<T> resourceKey, T value) {
        this.queryValueWithResource.put(resourceKey, value);
        this.queryResourceWithKey.put(resourceKey.getLocation(), resourceKey);

        return resourceKey;
    }

    @Override
    public void unregister(ResourceKey<T> resourceKey) {
        this.queryValueWithResource.remove(resourceKey);
        this.queryResourceWithKey.remove(resourceKey.getLocation());
    }
}