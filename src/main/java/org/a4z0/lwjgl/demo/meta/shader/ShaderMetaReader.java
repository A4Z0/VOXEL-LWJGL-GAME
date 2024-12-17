package org.a4z0.lwjgl.demo.meta.shader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.a4z0.lwjgl.demo.resource.Key;
import org.a4z0.lwjgl.demo.meta.shader.program.ShaderProgramMeta;
import org.a4z0.lwjgl.demo.resource.shader.ShaderType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public final class ShaderMetaReader {

    ShaderMetaReader() {}

    /**
    * Loads a Shader Program Meta.
    *
    * @param Path Path.
    *
    * @return a {@link ShaderProgramMeta}.
    */

    public static List<ShaderMeta> read(String Path) {
        try {
            return read(new FileInputStream(Path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("...", e);
        }
    }

    /**
    * Loads a Shader Program Meta.
    *
    * @param Stream Stream.
    *
    * @return a {@link ShaderProgramMeta}.
    */

    public static List<ShaderMeta> read(InputStream Stream) {
        try {
            return read(JsonParser.parseString(new String(Stream.readAllBytes())).getAsJsonObject());
        } catch (IOException e) {
            throw new RuntimeException("...", e);
        }
    }

    /**
    * Loads a Shader Program Meta.
    *
    * @param Json Json.
    *
    * @return a {@link ShaderProgramMeta}.
    */

    public static List<ShaderMeta> read(JsonObject Json) {
        if(!Json.has("shaders"))
            throw new RuntimeException("...");

        List<ShaderMeta> Shaders = new ArrayList<>();

        Json.get("shaders").getAsJsonArray().forEach(Element -> {
            JsonObject Shader = Element.getAsJsonObject();

            if(!Shader.has("name"))
                throw new RuntimeException("...");
            if(!Shader.has("type"))
                throw new RuntimeException("...");

            Shaders.add(new ShaderMeta(Key.of(ShaderType.of(Shader.get("type").getAsString()).getExtension() + "/" + Shader.get("name").getAsString()), ShaderType.of(Shader.get("type").getAsString())));
        });

        return Shaders;
    }
}