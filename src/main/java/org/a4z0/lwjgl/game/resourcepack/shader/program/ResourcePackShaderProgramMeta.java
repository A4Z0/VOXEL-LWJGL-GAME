package org.a4z0.lwjgl.game.resourcepack.shader.program;

import org.a4z0.lwjgl.game.resource.ResourceKey;
import org.a4z0.lwjgl.game.resource.shader.Shader;
import org.a4z0.lwjgl.game.resource.shader.program.ShaderProgram;
import org.a4z0.lwjgl.game.resourcepack.Meta;

public final class ResourcePackShaderProgramMeta extends Meta<ShaderProgram> {

    private final ResourceKey<Shader> vertexResoruceKey;
    private final ResourceKey<Shader> fragmentResourceKey;

    /**
    * Construct a {@link ResourcePackShaderProgramMeta}.
    *
    * @param resourceKey Resource Key.
    * @param vertexResourceKey Vertex Resource Key.
    * @param fragmentResourceKey Fragment Resource Key.
    */

    ResourcePackShaderProgramMeta(String path, ResourceKey<ShaderProgram> resourceKey, ResourceKey<Shader> vertexResourceKey, ResourceKey<Shader> fragmentResourceKey) {
        super(path, resourceKey);

        this.vertexResoruceKey = vertexResourceKey;
        this.fragmentResourceKey = fragmentResourceKey;
    }

    /**
    * @return the Vertex Resource Key.
    */

    public ResourceKey<Shader> getVertex() {
        return this.vertexResoruceKey;
    }

    /**
    * @return the Fragment Resource Key.
    */

    public ResourceKey<Shader> getFragment() {
        return this.fragmentResourceKey;
    }

    @Override
    public String toString() {
        return "ResourcePackShaderProgramMeta{\"Resource\": " + this.getResource() + ", \"Vertex\": " + this.getVertex() + ", \"Fragment\": " + this.getFragment() + "}";
    }
}