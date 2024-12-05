package org.a4z0.lwjgl.demo.resource.resourcepack;

import org.a4z0.lwjgl.demo.resource.Resource;

public final class Meta implements Resource {

    private final Object Icon;
    private final String Description;

    /**
    * Construct a {@link Meta}.
    */

    Meta(Object Icon, String Description) {
        this.Icon = Icon;
        this.Description = Description;
    }

    /**
    * @return the Icon.
    */

    public Object getIcon() {
        return this.Icon;
    }

    /**
    * @return the Description.
    */

    public String getDescription() {
        return this.Description;
    }
}