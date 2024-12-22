package org.a4z0.lwjgl.demo.resourcepack.language;

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

public final class ResourcePackLanguageMetaReader {

    @Deprecated
    public ResourcePackLanguageMetaReader() {}

    public static Result<ResourcePackLanguageMeta> read(File File) {
        try(Reader Reader = new FileReader(File)) {
            return Result.success(new ResourcePackLanguageMeta(File.getPath(), ResourceKey.create(Key.of(File.getName().replace(".json", "")))));
        } catch (IOException e) {
            return Result.error("Couldn't read \"" + File.getPath() + "\".");
        }
    }

    public static Result<Collection<ResourcePackLanguageMeta>> readAll(File File) {
        List<ResourcePackLanguageMeta> Languages = new ArrayList<>();

        try(Stream<Path> Paths = Files.walk(Path.of(File.getPath(), ResourcePack.LANGUAGE_DIRECTORY))) {
            for(Path Path : Paths.toList())
                if(Path.toString().endsWith(".json")) {
                    Result<ResourcePackLanguageMeta> Meta = ResourcePackLanguageMetaReader.read(Path.toFile());

                    if(Meta.getError().isPresent())
                        return Result.error(Meta.getError().get());

                    Languages.add(Meta.getOrThrow());
                }

            return Result.success(Collections.unmodifiableCollection(Languages));
        } catch (IOException e) {
            return Result.error("Couldn't read \"" + ResourcePack.LANGUAGE_DIRECTORY + "\"");
        }
    }
}