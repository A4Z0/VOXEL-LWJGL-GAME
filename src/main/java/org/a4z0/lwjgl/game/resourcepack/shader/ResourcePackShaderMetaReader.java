package org.a4z0.lwjgl.game.resourcepack.shader;

import org.a4z0.lwjgl.Key;
import org.a4z0.lwjgl.DataResult;
import org.a4z0.lwjgl.core.resource.ResourceKey;
import org.a4z0.lwjgl.game.resource.shader.ShaderType;
import org.a4z0.lwjgl.game.resourcepack.ResourcePack;

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

    public static DataResult<ResourcePackShaderMeta> read(File File) {
        try(Reader Reader = new FileReader(File)) {
            ShaderType shaderType = ShaderType.of(File.getName().split("\\.")[1]);

            return DataResult.success(new ResourcePackShaderMeta(
                File.getPath(),
                ResourceKey.create(Key.of(shaderType.getExtension() + "/" + File.getName().replace("." + shaderType.getExtension(), ""))),
                shaderType
            ));
        } catch (IOException e) {
            return DataResult.error("Couldn't read \"" + File.getPath() + "\".");
        }
    }

    public static DataResult<Collection<ResourcePackShaderMeta>> readAll(File File) {
        List<ResourcePackShaderMeta> Shaders = new ArrayList<>();

        try(Stream<Path> Paths = Files.walk(Path.of(File.getPath(), ResourcePack.SHADER_DIRECTORY))) {
            for(Path Path : Paths.toList())
                for(ShaderType Type : ShaderType.values())
                    if(Path.toString().endsWith("." + Type.getExtension())) {
                        DataResult<ResourcePackShaderMeta> Meta = ResourcePackShaderMetaReader.read(Path.toFile());

                        if(Meta.error().isPresent())
                            return DataResult.error(Meta.error().get());

                        Shaders.add(Meta.resultOrThrow());
                    }

            return DataResult.success(Collections.unmodifiableCollection(Shaders));
        } catch (IOException e) {
            return DataResult.error("Couldn't read \"" + ResourcePack.SHADER_DIRECTORY + "\"");
        }
    }
}