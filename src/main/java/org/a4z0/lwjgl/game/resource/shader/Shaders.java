package org.a4z0.lwjgl.game.resource.shader;

import org.a4z0.lwjgl.Key;
import org.a4z0.lwjgl.game.resource.ResourceKey;
import org.a4z0.lwjgl.game.resource.shader.program.ShaderProgram;

public final class Shaders {

    public static final ResourceKey<ShaderProgram> UI = ResourceKey.create(Key.of("shader/program"), Key.of("ui"));

    Shaders() {}
}