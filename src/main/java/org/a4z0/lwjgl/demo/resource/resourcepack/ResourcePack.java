package org.a4z0.lwjgl.demo.resource.resourcepack;

public final class ResourcePack {

    private final Meta Meta;

    /**
    * Construct a {@link ResourcePack}.
    */

    ResourcePack(Meta Meta) {
        this.Meta = Meta;
    }

    /**
    * @return ...
    */

    public Meta getMeta() {
        return this.Meta;
    }
}