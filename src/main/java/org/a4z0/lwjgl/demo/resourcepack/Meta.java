package org.a4z0.lwjgl.demo.resourcepack;

import org.a4z0.lwjgl.demo.resource.ResourceKey;

public abstract class Meta<T> {

    private final String path;
    private final ResourceKey<T> resource;

    /**
    * Construct a {@link Meta}.
    *
    * @param path Path.
    * @param resourceKey Resource Key.
    */

    protected Meta(String path, ResourceKey<T> resourceKey) {
        this.path = path;
        this.resource = resourceKey;
    }

    /**
    * @return the Path.
    */

    public String getPath() {
        return this.path;
    }

    /**
    * @return the Resource.
    */

    public ResourceKey<T> getResource() {
        return this.resource;
    }

    /**
    * @return this as a {@link String}.
    */

    @Override
    public abstract String toString();
}