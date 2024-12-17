package org.a4z0.lwjgl.demo.lang;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.Map;

public final class LanguageLoader {

    private static final Gson GSON = new Gson();

    LanguageLoader() {}

    /**
    * Loads a Language.
    *
    * @param path Path.
    *
    * @return a {@link Language}.
    */

    public static Language load(String path) {
        try(InputStream stream = new FileInputStream(path)) {
            return load(stream);
        } catch (IOException e) {
            throw new RuntimeException("Unable to locate Language.", e);
        }
    }

    /**
    * Loads a Language.
    *
    * @param stream Stream.
    *
    * @return a {@link Language}.
    */

    public static Language load(InputStream stream) {
        try(Reader reader = new InputStreamReader(stream)) {
            return new Language(null, GSON.fromJson(reader, new TypeToken<Map<String, String>>() {}.getType()));
        } catch (IOException e) {
            throw new RuntimeException("Unable to load Language.", e);
        }
    }
}