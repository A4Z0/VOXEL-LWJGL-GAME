package org.a4z0.lwjgl.game.resourcepack.language;

import org.a4z0.lwjgl.core.resource.ResourceKey;
import org.a4z0.lwjgl.game.resource.language.Language;
import org.a4z0.lwjgl.game.resourcepack.Meta;

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