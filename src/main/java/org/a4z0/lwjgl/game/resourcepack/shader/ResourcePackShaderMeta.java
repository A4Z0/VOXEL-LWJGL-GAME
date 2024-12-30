package org.a4z0.lwjgl.game.resourcepack.shader;

import org.a4z0.lwjgl.game.resource.ResourceKey;
import org.a4z0.lwjgl.game.resource.shader.Shader;
import org.a4z0.lwjgl.game.resource.shader.ShaderType;
import org.a4z0.lwjgl.game.resourcepack.Meta;

public final class ResourcePackShaderMeta extends Meta<Shader> {

    private final ShaderType shaderType;

    /**
    * Construct a {@link ResourcePackShaderMeta}.
    *
    * @param path Path.
    * @param resourceKey Resource Key.
    * @param shaderType Shader Type.
    */

    ResourcePackShaderMeta(String path, ResourceKey<Shader> resourceKey, ShaderType shaderType) {
        super(path, resourceKey);

        this.shaderType = shaderType;
    }

    /**
    * @return the Shader Type.
    */

    public ShaderType getShaderType() {
        return this.shaderType;
    }

    @Override
    public String toString() {
        return "ResourcePackShaderMeta{"
            + "\"Resource\": " + this.getResource()
            + ", "
            + "\"ShaderType\": " + this.getShaderType()
        + "}";
    }
}