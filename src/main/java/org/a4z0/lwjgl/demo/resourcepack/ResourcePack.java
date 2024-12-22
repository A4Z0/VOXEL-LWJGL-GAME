package org.a4z0.lwjgl.demo.resourcepack;

import org.a4z0.lwjgl.demo.resource.ResourceKey;
import org.a4z0.lwjgl.demo.resourcepack.font.ResourcePackFontMeta;
import org.a4z0.lwjgl.demo.resourcepack.language.ResourcePackLanguageMeta;
import org.a4z0.lwjgl.demo.resourcepack.shader.ResourcePackShaderMeta;
import org.a4z0.lwjgl.demo.resourcepack.shader.program.ResourcePackShaderProgramMeta;

import java.util.Collection;

public final class ResourcePack extends Meta<ResourcePack> {

    public static final String ASSETS_DIRECTORY             = "assets\\";
    public static final String LANGUAGE_DIRECTORY           = ASSETS_DIRECTORY + "lang\\";
    public static final String FONT_DIRECTORY               = ASSETS_DIRECTORY + "fonts\\";
    public static final String SHADER_DIRECTORY             = ASSETS_DIRECTORY + "shaders\\";
    public static final String SHADER_PROGRAM_DIRECTORY     = SHADER_DIRECTORY + "program\\";

    private final Collection<ResourcePackLanguageMeta> languageMetas;
    private final Collection<ResourcePackFontMeta> fontMetas;
    private final Collection<ResourcePackShaderMeta> shaderMetas;
    private final Collection<ResourcePackShaderProgramMeta> shaderProgramMetas;

    /**
    * Construct a {@link ResourcePack}.
    *
    * @param path Path.
    * @param resourceKey Resource Key.
    * @param languageMetas Language Metas.
    * @param fontMetas Font Metas.
    * @param shaderMetas Shader Metas.
    * @param shaderProgramMetas Shader Program Metas.
    */

    ResourcePack(String path, ResourceKey<ResourcePack> resourceKey, Collection<ResourcePackLanguageMeta> languageMetas, Collection<ResourcePackFontMeta> fontMetas, Collection<ResourcePackShaderMeta> shaderMetas, Collection<ResourcePackShaderProgramMeta> shaderProgramMetas) {
        super(path, resourceKey);

        this.languageMetas = languageMetas;
        this.fontMetas = fontMetas;
        this.shaderMetas = shaderMetas;
        this.shaderProgramMetas = shaderProgramMetas;
    }

    /**
    * @return the Language Metas.
    */

    public Collection<ResourcePackLanguageMeta> getLanguages() {
        return this.languageMetas;
    }

    /**
    * @return the Font Metas.
    */

    public Collection<ResourcePackFontMeta> getFonts() {
        return this.fontMetas;
    }

    /**
    * @return the Shader Metas.
    */

    public Collection<ResourcePackShaderMeta> getShaders() {
        return this.shaderMetas;
    }

    /**
    * @return the Shader Program Metas.
    */

    public Collection<ResourcePackShaderProgramMeta> getShaderPrograms() {
        return this.shaderProgramMetas;
    }

    @Override
    public String toString() {
        return "ResourecePackMeta{"
            + "\"Resource\": " + this.getResource()
            + ", "
            + "\"Languages\": " + this.getLanguages()
            + ", "
            + "\"Fonts\": " + this.getFonts()
            + ", "
            + "\"Shaders\": " + this.getShaders()
            + ", "
            + "\"ShaderPrograms\": " + this.getShaderPrograms()
        + "}";
    }
}