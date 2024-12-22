package org.a4z0.lwjgl.demo.resourcepack.language;

import org.a4z0.lwjgl.demo.resource.language.Language;
import org.a4z0.lwjgl.demo.resource.ResourceKey;
import org.a4z0.lwjgl.demo.resourcepack.Meta;

public final class ResourcePackLanguageMeta extends Meta<Language> {

    /**
    * Construct a {@link ResourcePackLanguageMeta}.
    *
    * @param path Path.
    * @param resourceKey Resource Key.
    */

    ResourcePackLanguageMeta(String path, ResourceKey<Language> resourceKey) {
        super(path, resourceKey);
    }

    @Override
    public String toString() {
        return "ResourcePackLanguageMeta{\"Resource\": " + this.getResource() + "}";
    }
}