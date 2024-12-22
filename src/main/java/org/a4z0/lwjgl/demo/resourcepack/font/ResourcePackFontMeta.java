package org.a4z0.lwjgl.demo.resourcepack.font;

import org.a4z0.lwjgl.demo.resource.ResourceKey;
import org.a4z0.lwjgl.demo.resource.font.Font;
import org.a4z0.lwjgl.demo.resourcepack.Meta;

public final class ResourcePackFontMeta extends Meta<Font> {

    /**
    * Construct a {@link ResourcePackFontMeta}.
    *
    * @param path Path.
    * @param resourceKey Resource Key.
    */

    ResourcePackFontMeta(String path, ResourceKey<Font> resourceKey) {
        super(path, resourceKey);
    }

    @Override
    public String toString() {
        return "ResourcePackFontMeta{\"Resource\": " + this.getResource() + "}";
    }
}