package org.a4z0.lwjgl.demo.meta.shader;

import org.a4z0.lwjgl.demo.resource.Key;
import org.a4z0.lwjgl.demo.resource.shader.ShaderType;

public final class ShaderMeta {

    private final Key name;
    private final ShaderType shaderType;

    /**
    * Construct a {@link ShaderMeta}.
    *
    * @param name Name.
    * @param shaderType Type.
    */

    public ShaderMeta(Key name, ShaderType shaderType) {
        this.name = name;
        this.shaderType = shaderType;
    }

    /**
    * @return the Name.
    */

    public Key getName() {
        return this.name;
    }

    /**
    * @return the Shader Type.
    */

    public ShaderType getType() {
        return this.shaderType;
    }
}