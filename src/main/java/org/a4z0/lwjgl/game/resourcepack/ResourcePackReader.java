package org.a4z0.lwjgl.game.resourcepack;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.a4z0.lwjgl.Key;
import org.a4z0.lwjgl.DataResult;
import org.a4z0.lwjgl.game.resource.ResourceKey;
import org.a4z0.lwjgl.game.resourcepack.font.ResourcePackFontMeta;
import org.a4z0.lwjgl.game.resourcepack.font.ResourcePackFontMetaReader;
import org.a4z0.lwjgl.game.resourcepack.language.ResourcePackLanguageMeta;
import org.a4z0.lwjgl.game.resourcepack.language.ResourcePackLanguageMetaReader;
import org.a4z0.lwjgl.game.resourcepack.shader.ResourcePackShaderMeta;
import org.a4z0.lwjgl.game.resourcepack.shader.ResourcePackShaderMetaReader;
import org.a4z0.lwjgl.game.resourcepack.shader.program.ResourcePackShaderProgramMeta;
import org.a4z0.lwjgl.game.resourcepack.shader.program.ResourcePackShaderProgramMetaReader;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.Collection;

public final class ResourcePackReader {

    @Deprecated
    public ResourcePackReader() {}

    public static DataResult<ResourcePack> read(File file) {
        try(Reader Reader = new FileReader(new File(file, "pack.json"))) {
            JsonObject packMeta = JsonParser.parseReader(Reader).getAsJsonObject();

            if(!packMeta.has("name") || !packMeta.get("name").isJsonPrimitive() || !packMeta.get("name").getAsJsonPrimitive().isString())
                return DataResult.error("Couldn't read \"name\" at: \"" + file.getPath() + "\".");

            DataResult<Collection<ResourcePackLanguageMeta>> languageMetas = ResourcePackLanguageMetaReader.readAll(file);

            if(languageMetas.error().isPresent())
                return DataResult.error(languageMetas.error().get());

            DataResult<Collection<ResourcePackFontMeta>> fontMetas = ResourcePackFontMetaReader.readAll(file);

            if(fontMetas.error().isPresent())
                return DataResult.error(fontMetas.error().get());

            DataResult<Collection<ResourcePackShaderMeta>> shaderMetas = ResourcePackShaderMetaReader.readAll(file);

            if(shaderMetas.error().isPresent())
                return DataResult.error(shaderMetas.error().get());

            DataResult<Collection<ResourcePackShaderProgramMeta>> shaderProgramsMetas = ResourcePackShaderProgramMetaReader.readAll(file);

            if(shaderProgramsMetas.error().isPresent())
                return DataResult.error(shaderProgramsMetas.error().get());

            return DataResult.success(new ResourcePack(file.getPath(), ResourceKey.create(Key.of(packMeta.get("name").getAsString())), languageMetas.resultOrThrow(), fontMetas.resultOrThrow(), shaderMetas.resultOrThrow(), shaderProgramsMetas.resultOrThrow()));
        } catch (Exception e) {
            return DataResult.error("Couldn't read \"" + file.getPath() + "\".");
        }
    }
}