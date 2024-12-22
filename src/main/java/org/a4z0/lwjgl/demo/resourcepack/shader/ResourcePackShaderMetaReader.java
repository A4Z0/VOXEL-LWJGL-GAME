package org.a4z0.lwjgl.demo.resourcepack.shader;

import org.a4z0.lwjgl.demo.resource.Key;
import org.a4z0.lwjgl.demo.resource.ResourceKey;
import org.a4z0.lwjgl.demo.resource.shader.ShaderType;
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

public final class ResourcePackShaderMetaReader {

    @Deprecated
    public ResourcePackShaderMetaReader() {}

    public static Result<ResourcePackShaderMeta> read(File File) {
        try(Reader Reader = new FileReader(File)) {
            ShaderType shaderType = ShaderType.of(File.getName().split("\\.")[1]);

            return Result.success(new ResourcePackShaderMeta(
                File.getPath(),
                ResourceKey.create(Key.of(shaderType.getExtension()), Key.of(File.getParentFile().getName() + "/" + File.getName().replace("." + shaderType.getExtension(), ""))),
                shaderType
            ));
        } catch (IOException e) {
            return Result.error("Couldn't read \"" + File.getPath() + "\".");
        }
    }

    public static Result<Collection<ResourcePackShaderMeta>> readAll(File File) {
        List<ResourcePackShaderMeta> Shaders = new ArrayList<>();

        try(Stream<Path> Paths = Files.walk(Path.of(File.getPath(), ResourcePack.SHADER_DIRECTORY))) {
            for(Path Path : Paths.toList())
                for(ShaderType Type : ShaderType.values())
                    if(Path.toString().endsWith("." + Type.getExtension())) {
                        Result<ResourcePackShaderMeta> Meta = ResourcePackShaderMetaReader.read(Path.toFile());

                        if(Meta.getError().isPresent())
                            return Result.error(Meta.getError().get());

                        Shaders.add(Meta.getOrThrow());
                    }

            return Result.success(Collections.unmodifiableCollection(Shaders));
        } catch (IOException e) {
            return Result.error("Couldn't read \"" + ResourcePack.SHADER_DIRECTORY + "\"");
        }
    }
}