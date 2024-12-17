package org.a4z0.lwjgl.demo.resource.shader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class ShaderLoader {

    ShaderLoader() {}

    /**
    * Loads a Shader.
    *
    * @param path Path.
    * @param shaderType Shader Type.
    *
    * @return a {@link Shader}.
    */

    public static Shader load(String path, ShaderType shaderType) throws ShaderException {
        try {
            return load(new FileInputStream(path), shaderType);
        } catch (IOException e) {
            throw new ShaderException("Unable to locate Shader.", e);
        }
    }

    /**
    * Loads a Shader.
    *
    * @param stream Stream.
    * @param shaderType Shader Type.
    *
    * @return a {@link Shader}.
    */

    public static Shader load(InputStream stream, ShaderType shaderType) throws ShaderException {
        try {
            return new Shader(shaderType, new String(stream.readAllBytes()));
        } catch (IOException e) {
            throw new ShaderException("Unable to load Shader.", e);
        }
    }
}