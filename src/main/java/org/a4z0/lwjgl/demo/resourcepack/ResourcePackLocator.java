package org.a4z0.lwjgl.demo.resourcepack;

import org.a4z0.lwjgl.demo.language.LanguageLoader;
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
    * @param Resource Resource Pack to be added.
    */

    public static void add(ResourcePack Resource) {
        if(RESOURCE_PACKS.contains(Resource))
            return;

        System.out.println(Resource);

        for(ResourcePackLanguageMeta meta : Resource.getLanguages())
            Registries.LANGUAGE.register(meta.getResource(), LanguageLoader.load(meta.getPath()));
        for(ResourcePackFontMeta meta : Resource.getFonts())
            Registries.FONT.register(meta.getResource(), FontLoader.load(meta.getPath()));
        for(ResourcePackShaderMeta meta : Resource.getShaders())
            Registries.SHADER.register(meta.getResource(), ShaderLoader.load(meta.getPath(), ShaderType.of(meta.getResource().getRegistry().getPath())));
        for(ResourcePackShaderProgramMeta meta : Resource.getShaderPrograms())
            Registries.SHADER_PROGRAM.register(meta.getResource(), new ShaderProgram(List.of(meta.getVertex(), meta.getFragment())));

        RESOURCE_PACKS.add(Resource);
    }

    /**
    * Removes a Resource Pack.
    *
    * @param Resource Resource Pack to be removed.
    */

    public static void remove(ResourcePack Resource) {
        if(!RESOURCE_PACKS.contains(Resource))
            return;

        for(ResourcePackLanguageMeta Meta : Resource.getLanguages())
            Registries.LANGUAGE.unregister(Meta.getResource());
        for(ResourcePackFontMeta Meta : Resource.getFonts())
            Registries.FONT.unregister(Meta.getResource());
        for(ResourcePackShaderMeta Meta : Resource.getShaders())
            Registries.SHADER.unregister(Meta.getResource());
        for(ResourcePackShaderProgramMeta Meta : Resource.getShaderPrograms())
            Registries.SHADER_PROGRAM.unregister(Meta.getResource());

        RESOURCE_PACKS.remove(Resource);
    }
}