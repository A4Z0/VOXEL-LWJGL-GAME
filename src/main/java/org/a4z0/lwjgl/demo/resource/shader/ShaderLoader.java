package org.a4z0.lwjgl.demo.resource.shader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ShaderLoader {

    private static final Pattern IMPORT_PATTERN = Pattern.compile("#import\\s<([a-z]+.[a-z]+)>");

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
            return load(new FileInputStream(path),path + "/../../include", shaderType);
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

    public static Shader load(InputStream stream, String include, ShaderType shaderType) throws ShaderException {
        Matcher matcher = IMPORT_PATTERN.matcher(ShaderResourceReader.read(stream));
        StringBuilder Source = new StringBuilder();

        while(matcher.find()) {
            String Path = matcher.group(1);
            String Content = ShaderResourceReader.read(include + "/" + Path);
            matcher.appendReplacement(Source, Matcher.quoteReplacement(Content));
        }

        matcher.appendTail(Source);

        return new Shader(shaderType, Source.toString());
    }
}