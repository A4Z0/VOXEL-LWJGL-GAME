package org.a4z0.lwjgl.demo.resourcepack.language;

import org.a4z0.common.DataResult;
import org.a4z0.lwjgl.demo.resource.Key;
import org.a4z0.lwjgl.demo.resource.ResourceKey;
import org.a4z0.lwjgl.demo.resourcepack.ResourcePack;

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

    public static DataResult<ResourcePackLanguageMeta> read(File File) {
        try(Reader Reader = new FileReader(File)) {
            return DataResult.success(new ResourcePackLanguageMeta(File.getPath(), ResourceKey.create(Key.of(File.getName().replace(".json", "")))));
        } catch (IOException e) {
            return DataResult.error("Couldn't read \"" + File.getPath() + "\".");
        }
    }

    public static DataResult<Collection<ResourcePackLanguageMeta>> readAll(File File) {
        List<ResourcePackLanguageMeta> Languages = new ArrayList<>();

        try(Stream<Path> Paths = Files.walk(Path.of(File.getPath(), ResourcePack.LANGUAGE_DIRECTORY))) {
            for(Path Path : Paths.toList())
                if(Path.toString().endsWith(".json")) {
                    DataResult<ResourcePackLanguageMeta> Meta = ResourcePackLanguageMetaReader.read(Path.toFile());

                    if(Meta.error().isPresent())
                        return DataResult.error(Meta.error().get());

                    Languages.add(Meta.resultOrThrow());
                }

            return DataResult.success(Collections.unmodifiableCollection(Languages));
        } catch (IOException e) {
            return DataResult.error("Couldn't read \"" + ResourcePack.LANGUAGE_DIRECTORY + "\"");
        }
    }
}