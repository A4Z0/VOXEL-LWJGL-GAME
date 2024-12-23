package org.a4z0.lwjgl.demo.resourcepack.shader.program;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.a4z0.lwjgl.demo.resource.Key;
import org.a4z0.lwjgl.demo.resource.ResourceKey;
import org.a4z0.lwjgl.demo.resourcepack.ResourcePack;
import org.a4z0.lwjgl.demo.util.DataResult;

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

    public static DataResult<ResourcePackShaderProgramMeta> read(File File) {
        try(Reader Reader = new FileReader(File)) {
            JsonObject Json = JsonParser.parseReader(Reader).getAsJsonObject();

            return DataResult.success(new ResourcePackShaderProgramMeta(File.getPath(), ResourceKey.create(Key.of(Json.get("name").getAsString())), ResourceKey.create(Key.of("vert/" + Json.get("vertex").getAsString())), ResourceKey.create(Key.of("frag/" + Json.get("fragment").getAsString()))));
        } catch (IOException e) {
            return DataResult.error("Couldn't read \"" + File.getPath() + "\".");
        }
    }

    public static DataResult<Collection<ResourcePackShaderProgramMeta>> readAll(File File) {
        List<ResourcePackShaderProgramMeta> ShaderPrograms = new ArrayList<>();

        try(Stream<Path> Paths = Files.walk(Path.of(File.getPath(), ResourcePack.SHADER_PROGRAM_DIRECTORY))) {
            for(Path Path : Paths.toList())
                if(Path.toString().endsWith(".json")) {
                    DataResult<ResourcePackShaderProgramMeta> Meta = ResourcePackShaderProgramMetaReader.read(Path.toFile());

                    if(Meta.error().isPresent())
                        return DataResult.error(Meta.error().get());

                    ShaderPrograms.add(Meta.resultOrThrow());
                }

            return DataResult.success(Collections.unmodifiableCollection(ShaderPrograms));
        } catch (IOException e) {
            return DataResult.error("Couldn't read \"" + ResourcePack.SHADER_PROGRAM_DIRECTORY + "\"");
        }
    }
}