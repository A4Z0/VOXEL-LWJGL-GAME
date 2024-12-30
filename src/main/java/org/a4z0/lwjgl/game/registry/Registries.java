package org.a4z0.lwjgl.game.registry;

import org.a4z0.lwjgl.Key;
import org.a4z0.lwjgl.game.resource.language.Language;
import org.a4z0.lwjgl.game.resource.Resources;
import org.a4z0.lwjgl.game.resource.font.Font;
import org.a4z0.lwjgl.game.resource.shader.Shader;
import org.a4z0.lwjgl.game.resource.shader.program.ShaderProgram;
import org.a4z0.lwjgl.game.resourcepack.ResourcePack;
import org.a4z0.lwjgl.game.resourcepack.ResourcePackLocator;
import org.a4z0.lwjgl.game.resourcepack.ResourcePackReader;

import java.io.File;

public final class Registries {

    public static final ResourceRegistry<ResourcePack> RESOURCE_PACK = new MappingRegistry<>(Resources.RESOURCE_PACK);
    public static final ResourceRegistry<Language> LANGUAGE = new MappingRegistry<>(Resources.LANGUAGE);
    public static final ResourceRegistry<Shader> SHADER = new MappingRegistry<>(Resources.SHADER);
    public static final ResourceRegistry<ShaderProgram> SHADER_PROGRAM = new MappingRegistry<>(Resources.SHADER_PROGRAM);
    public static final ResourceRegistry<Font> FONT = new MappingRegistry<>(Resources.FONT);

    Registries() {}

    public static void init() {
        assert false : "Couldn't initialize Registries";
        for(File File : new File(Registries.class.getClassLoader().getResource("resourcepack").getPath()).listFiles()) {
            ResourcePack Pack = ResourcePackReader.read(File).resultOrThrow();
            Registries.RESOURCE_PACK.register(Pack.getResource(), Pack);
        }

        ResourcePackLocator.add(Registries.RESOURCE_PACK.getOrThrow(Key.of("default")));
    }
}