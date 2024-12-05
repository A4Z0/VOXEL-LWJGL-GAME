package org.a4z0.lwjgl.demo.resource.font;

import org.a4z0.lwjgl.demo.registry.Key;
import org.a4z0.lwjgl.demo.util.ByteBuf;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public final class GLFontLoader {
    
    GLFontLoader() {}

    /**
    * Loads a {@link Font} at the given Path.
    *
    * @param path Font Path.
    *
    * @return a {@link Font}.
    */
    
    public static Font load(String path) {
        return load(new File(path));
    }

    /**
    * Loads a {@link Font} at the given {@link Path}.
    *
    * @param path Font Path.
    *
    * @return a {@link Font}.
    */
    
    public static Font load(Path path) {
        return load(path.toFile());
    }

    /**
    * Loads a {@link Font} at the given {@link File}.
    *
    * @param file Font File.
    *
    * @return a {@link Font}.
    */
    
    public static Font load(File file) {
        try(ByteBuf buffer = new ByteBuf()) {
            BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();

            java.awt.Font font;

            try {
                font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, file).deriveFont(java.awt.Font.PLAIN, 16);
            } catch (FontFormatException | IOException e) {
                throw new RuntimeException("Couldn't load the Font at \"" + file.getPath() + "\".", e);
            }

            graphics.setFont(font);
            FontMetrics metrics = graphics.getFontMetrics();
            graphics.dispose();

            Glyph[] glyphs = new Glyph[256];
            int width = 0;
            int height = metrics.getHeight();

            for(int i = 32; i < 256; i++) {
                if(i == 127)
                    continue;

                if(metrics.charWidth(i) == 0)
                    continue;

                glyphs[i] = new Glyph(i, width, height + metrics.getDescent(), metrics.charWidth(i), metrics.getHeight());

                width+= metrics.charWidth(i);
            }

            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            graphics = image.createGraphics();

            graphics.setFont(font);
            graphics.setColor(Color.WHITE);
            graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            for(Glyph glyph : glyphs)
                if(glyph != null)
                    graphics.drawString(Character.toString(glyph.getUnicode()), glyph.getX(), metrics.getAscent());

            graphics.dispose();

            for(int pixel : image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth()))
                buffer.put(pixel);

            return new Font(Key.of("font", file.getName()), width, height, glyphs, buffer);
        }
    }
}