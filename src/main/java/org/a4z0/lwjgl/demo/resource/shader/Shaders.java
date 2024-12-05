package org.a4z0.lwjgl.demo.resource.shader;

import org.a4z0.lwjgl.demo.registry.Key;
import org.a4z0.lwjgl.demo.util.Idk;

import static org.a4z0.lwjgl.demo.registry.Registries.SHADER_REGISTRY;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;

public final class Shaders {

    public static final Key WORLD_VERTEX_SHADER_KEY = Key.of("shader", "world_vertex");
    public static final Key WORLD_FRAGMENT_SHADER_KEY = Key.of("shader", "world_fragment");
    public static final Key TEXT_VERTEX_SHADER_KEY = Key.of("shader", "text_vertex");
    public static final Key TEXT_FRAGMENT_SHADER_KEY = Key.of("shader", "text_fragment");
    public static final Key CROSS_VERTEX_SHADER_KEY = Key.of("shader", "cross_vertex");
    public static final Key CROSS_FRAGMENT_SHADER_KEY = Key.of("shader", "cross_fragment");

    public static Shader WORLD_VERTEX_SHADER;
    public static Shader WORLD_FRAGMENT_SHADER;
    public static Shader TEXT_VERTEX_SHADER;
    public static Shader TEXT_FRAGMENT_SHADER;
    public static Shader CROSS_VERTEX_SHADER;
    public static Shader CROSS_FRAGMENT_SHADER;

    Shaders() {}

    public static void init() {
        System.out.println("[ShaderLoader]: -> \"shader/world.vert\".");
        SHADER_REGISTRY.register(
            WORLD_VERTEX_SHADER_KEY,
            WORLD_VERTEX_SHADER = new Shader(GL_VERTEX_SHADER).source(Idk.getResource("assets/shader/world.vert")).compile()
        );
        System.out.println("[ShaderLoader]: -> \"shader/world.frag\".");
        SHADER_REGISTRY.register(
            WORLD_FRAGMENT_SHADER_KEY,
            WORLD_FRAGMENT_SHADER = new Shader(GL_FRAGMENT_SHADER).source(Idk.getResource("assets/shader/world.frag")).compile()
        );
        System.out.println("[ShaderLoader]: -> \"shader/text.vert\".");
        SHADER_REGISTRY.register(
            TEXT_VERTEX_SHADER_KEY,
            TEXT_VERTEX_SHADER = new Shader(GL_VERTEX_SHADER).source(Idk.getResource("assets/shader/text.vert")).compile()
        );
        System.out.println("[ShaderLoader]: -> \"shader/text.frag\".");
        SHADER_REGISTRY.register(
            TEXT_FRAGMENT_SHADER_KEY,
            TEXT_FRAGMENT_SHADER = new Shader(GL_FRAGMENT_SHADER).source(Idk.getResource("assets/shader/text.frag")).compile()
        );
        System.out.println("[ShaderLoader]: -> \"shader/cross.vert\".");
        SHADER_REGISTRY.register(
            CROSS_VERTEX_SHADER_KEY,
            CROSS_VERTEX_SHADER = new Shader(GL_VERTEX_SHADER).source(Idk.getResource("assets/shader/cross.vert")).compile()
        );
        System.out.println("[ShaderLoader]: -> \"shader/cross.frag\".");
        SHADER_REGISTRY.register(
            CROSS_FRAGMENT_SHADER_KEY,
            CROSS_FRAGMENT_SHADER = new Shader(GL_FRAGMENT_SHADER).source(Idk.getResource("assets/shader/cross.frag")).compile()
        );
    }
}