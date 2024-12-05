package org.a4z0.lwjgl.demo.resource.texture;

import org.a4z0.lwjgl.demo.util.ByteBuf;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public final class GLTextureLoader {

    GLTextureLoader() {}

    /**
    * Loads a {@link Texture} at the given Path.
    *
    * @param str_path Texture Path.
    *
    * @return a {@link Texture}.
    */

    public static Texture getFrom(String str_path) {
        return getFrom(new File(str_path.replaceAll("\\.", "\\")));
    }

    /**
    * Loads a {@link Texture} at the given {@link Path}.
    *
    * @param text_path Texture Path.
    *
    * @return a {@link Texture}.
    */

    public static Texture getFrom(Path text_path) {
        return getFrom(text_path.toFile());
    }

    /**
    * Loads a {@link Texture} at the given {@link File}.
    *
    * @param text_file Texture File.
    *
    * @return a {@link Texture}.
    */

    public static Texture getFrom(File text_file) {
        try(ByteBuf buffer = new ByteBuf()) {
            BufferedImage text_image;

            try {
                text_image = ImageIO.read(text_file);
            } catch (IOException e) {
                throw new RuntimeException("Couldn't load the Texture at \"" + text_file.getPath() + "\".", e);
            }

            for(int pixel : text_image.getRGB(0, 0, text_image.getWidth(), text_image.getHeight(), null, 0, text_image.getWidth())) {
                buffer.put((byte) ((pixel >> 16) & 0xFF));
                buffer.put((byte) ((pixel >> 8) & 0xFF));
                buffer.put((byte) (pixel & 0xFF));
                buffer.put((byte) ((pixel >> 24) & 0xFF));
            }

            return new Texture(text_image.getWidth(), text_image.getHeight(), buffer);
        }
    }
}