package org.a4z0.lwjgl.demo.resource.shader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class ShaderResourceReader {

    ShaderResourceReader() {}

    public static String read(String path) throws ShaderException {
        try {
            return read(new FileInputStream(path));
        } catch (IOException e) {
            throw new ShaderException("Unable to locate Shader.", e);
        }
    }

    public static String read(InputStream stream) throws ShaderException {
        try {
            return new String(stream.readAllBytes());
        } catch (IOException e) {
            throw new ShaderException("Unable to load Shader.", e);
        }
    }
}