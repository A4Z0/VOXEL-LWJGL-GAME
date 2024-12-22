package org.a4z0.lwjgl.demo.resourcepack.font;

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

public final class ResourcePackFontMetaReader {

    @Deprecated
    ResourcePackFontMetaReader() {}

    public static Result<ResourcePackFontMeta> read(File File) {
        try(Reader Reader = new FileReader(File)) {
            return Result.success(new ResourcePackFontMeta(File.getPath(), ResourceKey.create(Key.of(File.getName().replace(".ttf", "")))));
        } catch (IOException e) {
            return Result.error("Couldn't read \"" + File.getPath() + "\".");
        }
    }

    public static Result<Collection<ResourcePackFontMeta>> readAll(File File) {
        List<ResourcePackFontMeta> Fonts = new ArrayList<>();

        try(Stream<Path> Paths = Files.walk(Path.of(File.getPath(), ResourcePack.FONT_DIRECTORY))) {
            for(Path Path : Paths.toList())
                if(Path.toString().endsWith(".ttf")) {
                    Result<ResourcePackFontMeta> Meta = ResourcePackFontMetaReader.read(Path.toFile());

                    if(Meta.getError().isPresent())
                        return Result.error(Meta.getError().get());

                    Fonts.add(Meta.getOrThrow());
                }

            return Result.success(Collections.unmodifiableCollection(Fonts));
        } catch (IOException e) {
            return Result.error("Couldn't read \"" + ResourcePack.FONT_DIRECTORY + "\"");
        }
    }
}