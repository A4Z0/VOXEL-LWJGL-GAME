package org.a4z0.lwjgl.demo.meta.shader;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.a4z0.lwjgl.demo.resource.Key;
import org.a4z0.lwjgl.demo.resource.shader.ShaderType;
import org.a4z0.lwjgl.demo.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class ShaderProgramReader {

    private static final Gson GSON = new Gson();

    ShaderProgramReader() {}

    /**
    * Reads a Shader Program Meta.
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
    * Reads a Shader Program Meta.
    *
    * @param Stream Stream.
    *
    * @return a {@link ShaderProgramMeta}.
    */

    public static ShaderProgramMeta read(InputStream Stream) {
        try(Reader reader = new InputStreamReader(Stream))  {
            Map<String, Object> Shader = GSON.fromJson(reader, new TypeToken<Map<String, Object>>() {}.getType());
            List<Pair<Key, ShaderType>> Shaders = new ArrayList<>();

            if(Shader.get("vertex") != null)
                Shaders.add(Pair.of(Key.of("vert/" + Shader.get("vertex")), ShaderType.VERTEX));
            if(Shader.get("fragment") != null)
                Shaders.add(Pair.of(Key.of("frag/" + Shader.get("fragment")), ShaderType.FRAGMENT));

            return new ShaderProgramMeta(Key.of((String) Shader.get("name")), Shaders);
        } catch (IOException e) {
            throw new RuntimeException("...", e);
        }
    }
}