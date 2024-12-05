package org.a4z0.lwjgl.demo.resource.resourcepack;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.a4z0.lwjgl.demo.resource.texture.Texture;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public final class ResourcePackReader {

    /**
    * ...
    *
    * @param File ...
    *
    * @return ...
    */

    private static ResourcePack resourcepack(File File) throws IOException {
        String[] Paths = File.list();

        if(Paths == null)
            throw new RuntimeException("...");

        for(String Path : Paths)
           if(Path.endsWith(".meta"))
               return new ResourcePack(meta(new File(Path)));

        throw new RuntimeException("...");
    }

    /**
    * ...
    *
    * @param File ...
    *
    * @return ...
    */

    public static Meta meta(File File) throws IOException {
        try(FileReader Reader = new FileReader(File)) {
            JsonObject Json = JsonParser.parseReader(Reader).getAsJsonObject();

            return new Meta(null, Json.get("description").getAsString());
        }
    }
}