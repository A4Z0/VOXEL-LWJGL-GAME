package org.a4z0.lwjgl.demo.resource.shader;

import org.a4z0.lwjgl.demo.registry.Key;

import static org.a4z0.lwjgl.demo.registry.Registries.SHADER_PROGRAM_REGISTRY;

public final class ShaderPrograms {

    public static final Key WORLD_SHADER_PROGRAM_KEY = Key.of("shader/program", "world");
    public static final Key TEXT_SHADER_PROGRAM_KEY = Key.of("shader/program", "text");
    public static final Key CROSS_SHADER_PROGRAM_KEY = Key.of("shader/program", "cross");

    public static ShaderProgram WORLD_SHADER_PROGRAM;
    public static ShaderProgram TEXT_SHADER_PROGRAM;
    public static ShaderProgram CROSS_SHADER_PROGRAM;

    ShaderPrograms() {}

    public static void init() {
        System.out.println("[ShaderProgramLoader]: -> \"shader/program/world\".");
        SHADER_PROGRAM_REGISTRY.register(WORLD_SHADER_PROGRAM_KEY, WORLD_SHADER_PROGRAM = new ShaderProgram()
            .attribute(0, "vertex_position")
            .attribute(1, "vertex_color")
            .addShader(Shaders.WORLD_VERTEX_SHADER)
            .addShader(Shaders.WORLD_FRAGMENT_SHADER)
            .link()
        );
        System.out.println("[ShaderProgramLoader]: -> \"shader/program/text\".");
        SHADER_PROGRAM_REGISTRY.register(TEXT_SHADER_PROGRAM_KEY, TEXT_SHADER_PROGRAM = new ShaderProgram()
            .attribute(0, "vertex_position")
            .addShader(Shaders.TEXT_VERTEX_SHADER)
            .addShader(Shaders.TEXT_FRAGMENT_SHADER)
            .link()
        );
        System.out.println("[ShaderProgramLoader]: -> \"shader/program/cross\".");
        SHADER_PROGRAM_REGISTRY.register(CROSS_SHADER_PROGRAM_KEY, CROSS_SHADER_PROGRAM = new ShaderProgram()
                .attribute(0, "vertex_position")
                .addShader(Shaders.CROSS_VERTEX_SHADER)
                .addShader(Shaders.CROSS_FRAGMENT_SHADER)
                .link()
        );
    }
}