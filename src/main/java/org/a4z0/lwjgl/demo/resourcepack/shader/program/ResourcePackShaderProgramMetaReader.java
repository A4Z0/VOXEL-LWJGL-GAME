package org.a4z0.lwjgl.demo.resourcepack.shader.program;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.a4z0.lwjgl.demo.resource.Key;
import org.a4z0.lwjgl.demo.resource.ResourceKey;
import org.a4z0.lwjgl.demo.resourcepack.ResourcePack;
import org.a4z0.lwjgl.demo.util.Result;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public final class ResourcePackShaderProgramMetaReader {

    @Deprecated
    public ResourcePackShaderProgramMetaReader() {}

    public static Result<ResourcePackShaderProgramMeta> read(File File) {
        try(Reader Reader = new FileReader(File)) {
            JsonObject Json = JsonParser.parseReader(Reader).getAsJsonObject();

            return Result.success(new ResourcePackShaderProgramMeta(File.getPath(), ResourceKey.create(Key.of(Json.get("name").getAsString())), ResourceKey.create(Key.of("vert"), Key.of(Json.get("vertex").getAsString())), ResourceKey.create(Key.of("frag"), Key.of(Json.get("fragment").getAsString()))));
        } catch (IOException e) {
            return Result.error("Couldn't read \"" + File.getPath() + "\".");
        }
    }

    public static Result<Collection<ResourcePackShaderProgramMeta>> readAll(File File) {
        List<ResourcePackShaderProgramMeta> ShaderPrograms = new ArrayList<>();

        try(Stream<Path> Paths = Files.walk(Path.of(File.getPath(), ResourcePack.SHADER_PROGRAM_DIRECTORY))) {
            for(Path Path : Paths.toList())
                if(Path.toString().endsWith(".json")) {
                    Result<ResourcePackShaderProgramMeta> Meta = ResourcePackShaderProgramMetaReader.read(Path.toFile());

                    if(Meta.getError().isPresent())
                        return Result.error(Meta.getError().get());

                    ShaderPrograms.add(Meta.getOrThrow());
                }

            return Result.success(Collections.unmodifiableCollection(ShaderPrograms));
        } catch (IOException e) {
            return Result.error("Couldn't read \"" + ResourcePack.SHADER_PROGRAM_DIRECTORY + "\"");
        }
    }
}