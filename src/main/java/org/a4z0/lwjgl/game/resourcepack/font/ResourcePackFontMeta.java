package org.a4z0.lwjgl.game.resourcepack.font;

import org.a4z0.lwjgl.game.resource.ResourceKey;
import org.a4z0.lwjgl.game.resource.font.Font;
import org.a4z0.lwjgl.game.resourcepack.Meta;

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