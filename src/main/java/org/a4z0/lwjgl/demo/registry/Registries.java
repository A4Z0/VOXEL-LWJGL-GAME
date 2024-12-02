package org.a4z0.lwjgl.demo.registry;

import org.a4z0.lwjgl.demo.shader.Shader;
import org.a4z0.lwjgl.demo.shader.ShaderProgram;

public class Registries {

    public static final Registry<Shader> SHADER_REGISTRY = new Registry<>();
    public static final Registry<ShaderProgram> SHADER_PROGRAM_REGISTRY = new Registry<>();

    Registries() {}
}