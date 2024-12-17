package org.a4z0.lwjgl.demo.resource.shader;

import org.a4z0.lwjgl.demo.resource.Key;
import org.a4z0.lwjgl.demo.resource.ResourceKey;
import org.a4z0.lwjgl.demo.resource.shader.program.ShaderProgram;

public final class Shaders {

    public static final ResourceKey<ShaderProgram> UI = ResourceKey.create(Key.of("shader/program"), Key.of("ui"));

    Shaders() {}
}