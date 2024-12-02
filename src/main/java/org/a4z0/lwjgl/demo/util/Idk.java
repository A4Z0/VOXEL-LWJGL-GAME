package org.a4z0.lwjgl.demo.util;

import org.a4z0.lwjgl.demo.shader.Shaders;

import java.io.IOException;
import java.io.InputStream;

public final class Idk {
    public static String getResource(String URI) {
        try(InputStream i = Shaders.class.getClassLoader().getResourceAsStream(URI)) {
            assert i != null;
            return new String(i.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}