package org.a4z0.lwjgl.game.resource;

import org.a4z0.lwjgl.Key;
import org.a4z0.lwjgl.game.resource.language.Language;
import org.a4z0.lwjgl.game.resource.font.Font;
import org.a4z0.lwjgl.game.registry.Registry;
import org.a4z0.lwjgl.game.resource.shader.Shader;
import org.a4z0.lwjgl.game.resource.shader.program.ShaderProgram;
import org.a4z0.lwjgl.game.resourcepack.ResourcePack;

public final class Resources {

    public static final ResourceKey<Registry<ResourcePack>> RESOURCE_PACK = ResourceKey.create(Key.of("resourcepack"));
    public static final ResourceKey<Registry<Language>> LANGUAGE = ResourceKey.create(Key.of("language"));
    public static final ResourceKey<Registry<Shader>> SHADER = ResourceKey.create(Key.of("shader"));
    public static final ResourceKey<Registry<ShaderProgram>> SHADER_PROGRAM = ResourceKey.create(Key.of("shader/program"));
    public static final ResourceKey<Registry<Font>> FONT = ResourceKey.create(Key.of("font"));

    Resources() {}
}