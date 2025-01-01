package org.a4z0.lwjgl.game.resourcepack.font;

import org.a4z0.lwjgl.Key;
import org.a4z0.lwjgl.DataResult;
import org.a4z0.lwjgl.core.resource.ResourceKey;
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

public final class ResourcePackFontMetaReader {

    @Deprecated
    ResourcePackFontMetaReader() {}

    public static DataResult<ResourcePackFontMeta> read(File File) {
        try(Reader Reader = new FileReader(File)) {
            return DataResult.success(new ResourcePackFontMeta(File.getPath(), ResourceKey.create(Key.of(File.getName().replace(".ttf", "")))));
        } catch (IOException e) {
            return DataResult.error("Couldn't read \"" + File.getPath() + "\".");
        }
    }

    public static DataResult<Collection<ResourcePackFontMeta>> readAll(File File) {
        List<ResourcePackFontMeta> Fonts = new ArrayList<>();

        try(Stream<Path> Paths = Files.walk(Path.of(File.getPath(), ResourcePack.FONT_DIRECTORY))) {
            for(Path Path : Paths.toList())
                if(Path.toString().endsWith(".ttf")) {
                    DataResult<ResourcePackFontMeta> Meta = ResourcePackFontMetaReader.read(Path.toFile());

                    if(Meta.error().isPresent())
                        return DataResult.error(Meta.error().get());

                    Fonts.add(Meta.resultOrThrow());
                }

            return DataResult.success(Collections.unmodifiableCollection(Fonts));
        } catch (IOException e) {
            return DataResult.error("Couldn't read \"" + ResourcePack.FONT_DIRECTORY + "\"");
        }
    }
}