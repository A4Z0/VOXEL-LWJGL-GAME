package org.a4z0.lwjgl.demo.meta.shader.program;

import org.a4z0.lwjgl.demo.resource.Key;
import org.a4z0.lwjgl.demo.meta.shader.ShaderMeta;

import java.util.Collection;
import java.util.List;

public final class ShaderProgramMeta {

    private final Key name;
    private final List<ShaderMeta> shaders;

    /**
    * Construct a {@link ShaderProgramMeta}.
    *
    * @param name Name.
    * @param shaders Shaders.
    */

    public ShaderProgramMeta(Key name, List<ShaderMeta> shaders) {
        this.name = name;
        this.shaders = shaders;
    }

    /**
    * @return the Name.
    */

    public Key getName() {
        return this.name;
    }

    /**
    * @return the Shaders.
    */

    public Collection<ShaderMeta> getShaders() {
        return this.shaders;
    }
}