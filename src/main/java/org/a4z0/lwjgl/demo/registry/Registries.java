package org.a4z0.lwjgl.demo.registry;

import org.a4z0.lwjgl.demo.language.Language;
import org.a4z0.lwjgl.demo.resource.Resources;
import org.a4z0.lwjgl.demo.resource.font.Font;
import org.a4z0.lwjgl.demo.resource.Key;
import org.a4z0.lwjgl.demo.resource.shader.Shader;
import org.a4z0.lwjgl.demo.resource.shader.program.ShaderProgram;
import org.a4z0.lwjgl.demo.resourcepack.ResourcePack;
import org.a4z0.lwjgl.demo.resourcepack.ResourcePackLocator;
import org.a4z0.lwjgl.demo.resourcepack.ResourcePackReader;

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
            ResourcePack Pack = ResourcePackReader.read(File).getOrThrow();
            Registries.RESOURCE_PACK.register(Pack.getResource(), Pack);
        }

        ResourcePackLocator.add(Registries.RESOURCE_PACK.getOrThrow(Key.of("default")));
    }
}