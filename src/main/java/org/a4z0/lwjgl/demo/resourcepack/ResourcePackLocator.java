package org.a4z0.lwjgl.demo.resourcepack;

import org.a4z0.lwjgl.demo.resource.language.LanguageLoader;
import org.a4z0.lwjgl.demo.registry.Registries;
import org.a4z0.lwjgl.demo.resource.font.FontLoader;
import org.a4z0.lwjgl.demo.resource.shader.ShaderLoader;
import org.a4z0.lwjgl.demo.resource.shader.ShaderType;
import org.a4z0.lwjgl.demo.resource.shader.program.ShaderProgram;
import org.a4z0.lwjgl.demo.resourcepack.font.ResourcePackFontMeta;
import org.a4z0.lwjgl.demo.resourcepack.language.ResourcePackLanguageMeta;
import org.a4z0.lwjgl.demo.resourcepack.shader.ResourcePackShaderMeta;
import org.a4z0.lwjgl.demo.resourcepack.shader.program.ResourcePackShaderProgramMeta;

import java.util.ArrayList;
import java.util.List;

public final class ResourcePackLocator {

    private static final List<ResourcePack> RESOURCE_PACKS = new ArrayList<>();

    @Deprecated
    public ResourcePackLocator() {}

    /**
    * Adds a Resource Pack.
    *
    * @param resourcePack Resource Pack to be added.
    */

    public static void add(ResourcePack resourcePack) {
        if(RESOURCE_PACKS.contains(resourcePack))
            return;

        for(ResourcePackLanguageMeta meta : resourcePack.getLanguages())
            Registries.LANGUAGE.register(meta.getResource(), LanguageLoader.load(meta.getPath()));
        for(ResourcePackFontMeta meta : resourcePack.getFonts())
            Registries.FONT.register(meta.getResource(), FontLoader.load(meta.getPath()));
        for(ResourcePackShaderMeta meta : resourcePack.getShaders())
            Registries.SHADER.register(meta.getResource(), ShaderLoader.load(meta.getPath(), meta.getShaderType()));
        for(ResourcePackShaderProgramMeta meta : resourcePack.getShaderPrograms())
            Registries.SHADER_PROGRAM.register(meta.getResource(), new ShaderProgram(List.of(meta.getVertex(), meta.getFragment())));

        RESOURCE_PACKS.add(resourcePack);
    }

    /**
    * Removes a Resource Pack.
    *
    * @param resourcePack Resource Pack to be removed.
    */

    public static void remove(ResourcePack resourcePack) {
        if(!RESOURCE_PACKS.contains(resourcePack))
            return;

        for(ResourcePackLanguageMeta meta : resourcePack.getLanguages())
            Registries.LANGUAGE.unregister(meta.getResource());
        for(ResourcePackFontMeta meta : resourcePack.getFonts())
            Registries.FONT.unregister(meta.getResource());
        for(ResourcePackShaderMeta meta : resourcePack.getShaders())
            Registries.SHADER.unregister(meta.getResource());
        for(ResourcePackShaderProgramMeta meta : resourcePack.getShaderPrograms())
            Registries.SHADER_PROGRAM.unregister(meta.getResource());

        RESOURCE_PACKS.remove(resourcePack);
    }
}