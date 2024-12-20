package org.a4z0.lwjgl.demo.meta.shader;

import org.a4z0.lwjgl.demo.resource.Key;
import org.a4z0.lwjgl.demo.resource.shader.ShaderType;
import org.a4z0.lwjgl.demo.util.Pair;

import java.util.Collection;
import java.util.List;

public final class ShaderProgramMeta {

    private final Key name;
    private final List<Pair<Key, ShaderType>> shaders;

    /**
    * Construct a {@link ShaderProgramMeta}.
    *
    * @param name Name.
    * @param shaders Shaders.
    */

    public ShaderProgramMeta(Key name, List<Pair<Key, ShaderType>> shaders) {
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

    public Collection<Pair<Key, ShaderType>> getShaders() {
        return this.shaders;
    }
}