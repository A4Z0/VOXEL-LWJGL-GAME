package org.a4z0.lwjgl.demo.resource;

import org.a4z0.lwjgl.demo.bootstrap.Bootstrap;
import org.a4z0.lwjgl.demo.lang.Language;
import org.a4z0.lwjgl.demo.resource.font.Font;
import org.a4z0.lwjgl.demo.registry.Registry;
import org.a4z0.lwjgl.demo.resource.shader.Shader;
import org.a4z0.lwjgl.demo.resource.shader.program.ShaderProgram;

public final class Resources {

    public static final ResourceKey<Registry<Bootstrap>> BOOTSTRAP = ResourceKey.create(Key.of("bootstrap"));
    public static final ResourceKey<Registry<Language>> LANGUAGE = ResourceKey.create(Key.of("language"));
    public static final ResourceKey<Registry<Shader>> SHADER = ResourceKey.create(Key.of("shader"));
    public static final ResourceKey<Registry<ShaderProgram>> SHADER_PROGRAM = ResourceKey.create(Key.of("shader/program"));
    public static final ResourceKey<Registry<Font>> FONT = ResourceKey.create(Key.of("font"));

    Resources() {}
}