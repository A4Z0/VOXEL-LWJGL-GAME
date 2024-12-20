package org.a4z0.lwjgl.demo.registry;

import org.a4z0.lwjgl.demo.bootstrap.*;
import org.a4z0.lwjgl.demo.language.Language;
import org.a4z0.lwjgl.demo.resource.Resources;
import org.a4z0.lwjgl.demo.resource.font.Font;
import org.a4z0.lwjgl.demo.resource.Key;
import org.a4z0.lwjgl.demo.resource.shader.Shader;
import org.a4z0.lwjgl.demo.resource.shader.program.ShaderProgram;

public final class Registries {

    public static final Key ROOT_REGISTRY_NAME = Key.of("root");

    public static final ResourceRegistry<Bootstrap> BOOTSTRAP = new MappingRegistry<>(Resources.BOOTSTRAP);
    public static final ResourceRegistry<Language> LANGUAGE = new MappingRegistry<>(Resources.LANGUAGE);
    public static final ResourceRegistry<Shader> SHADER = new MappingRegistry<>(Resources.SHADER);
    public static final ResourceRegistry<ShaderProgram> SHADER_PROGRAM = new MappingRegistry<>(Resources.SHADER_PROGRAM);
    public static final ResourceRegistry<Font> FONT = new MappingRegistry<>(Resources.FONT);

    Registries() {}

    @Deprecated
    public static void init() {
        BOOTSTRAP.register(Key.of("language"), new LanguageBootstrap());
        BOOTSTRAP.register(Key.of("shader"), new ShaderBootstrap());
        BOOTSTRAP.register(Key.of("shader/program"), new ShaderProgramBootstrap());
        BOOTSTRAP.register(Key.of("font"), new FontBootstrap());
    }

    public static void bootstrap() {
        for(Bootstrap Bootstrap : BOOTSTRAP.getValues())
            Bootstrap.run();
    }
}