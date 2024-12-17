package org.a4z0.lwjgl.demo.meta.shader.program;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.a4z0.lwjgl.demo.resource.Key;
import org.a4z0.lwjgl.demo.meta.shader.ShaderMetaReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public final class ShaderProgramReader {

    ShaderProgramReader() {}

    /**
    * Loads a Shader Program Meta.
    *
    * @param Path Path.
    *
    * @return a {@link ShaderProgramMeta}.
    */

    public static ShaderProgramMeta read(String Path) {
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

    public static ShaderProgramMeta read(InputStream Stream) {
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

    public static ShaderProgramMeta read(JsonObject Json) {
        if(!Json.has("name"))
            throw new RuntimeException("");
        if(!Json.has("shaders"))
            throw new RuntimeException("...");

        return new ShaderProgramMeta(Key.of(Json.get("name").getAsString()), ShaderMetaReader.read(Json));
    }
}