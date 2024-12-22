package org.a4z0.lwjgl.demo.resourcepack;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.a4z0.lwjgl.demo.resource.Key;
import org.a4z0.lwjgl.demo.resource.ResourceKey;
import org.a4z0.lwjgl.demo.resourcepack.font.ResourcePackFontMeta;
import org.a4z0.lwjgl.demo.resourcepack.font.ResourcePackFontMetaReader;
import org.a4z0.lwjgl.demo.resourcepack.language.ResourcePackLanguageMeta;
import org.a4z0.lwjgl.demo.resourcepack.language.ResourcePackLanguageMetaReader;
import org.a4z0.lwjgl.demo.resourcepack.shader.ResourcePackShaderMeta;
import org.a4z0.lwjgl.demo.resourcepack.shader.ResourcePackShaderMetaReader;
import org.a4z0.lwjgl.demo.resourcepack.shader.program.ResourcePackShaderProgramMeta;
import org.a4z0.lwjgl.demo.resourcepack.shader.program.ResourcePackShaderProgramMetaReader;
import org.a4z0.lwjgl.demo.util.Result;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.Collection;

public final class ResourcePackReader {

    @Deprecated
    public ResourcePackReader() {}

    public static Result<ResourcePack> read(File file) {
        try(Reader Reader = new FileReader(new File(file, "pack.json"))) {
            JsonObject packMeta = JsonParser.parseReader(Reader).getAsJsonObject();

            if(!packMeta.has("name") || !packMeta.get("name").isJsonPrimitive() || !packMeta.get("name").getAsJsonPrimitive().isString())
                return Result.error("Couldn't read \"name\" at: \"" + file.getPath() + "\".");

            Result<Collection<ResourcePackLanguageMeta>> languageMetas = ResourcePackLanguageMetaReader.readAll(file);

            if(languageMetas.getError().isPresent())
                return Result.error(languageMetas.getError().get());

            Result<Collection<ResourcePackFontMeta>> fontMetas = ResourcePackFontMetaReader.readAll(file);

            if(fontMetas.getError().isPresent())
                return Result.error(fontMetas.getError().get());

            Result<Collection<ResourcePackShaderMeta>> shaderMetas = ResourcePackShaderMetaReader.readAll(file);

            if(shaderMetas.getError().isPresent())
                return Result.error(shaderMetas.getError().get());

            Result<Collection<ResourcePackShaderProgramMeta>> shaderProgramsMetas = ResourcePackShaderProgramMetaReader.readAll(file);

            if(shaderProgramsMetas.getError().isPresent())
                return Result.error(shaderProgramsMetas.getError().get());

            return Result.success(new ResourcePack(file.getPath(), ResourceKey.create(Key.of(packMeta.get("name").getAsString())), languageMetas.getOrThrow(), fontMetas.getOrThrow(), shaderMetas.getOrThrow(), shaderProgramsMetas.getOrThrow()));
        } catch (Exception e) {
            return Result.error("Couldn't read \"" + file.getPath() + "\".");
        }
    }
}