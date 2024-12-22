package org.a4z0.lwjgl.demo.resource.font;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public final class FontLoader {

    FontLoader() {}

    /**
    * Loads a Font.
    *
    * @param path Path.
    *
    * @return a {@link Font}.
    */

    public static Font load(String path) {
        return load(path, 16);
    }

    public static Font load(File file) {
        return load(file.getPath());
    }

    /**
    * Loads a Font.
    *
    * @param path Path.
    * @param fontSize Font Size.
    *
    * @return a {@link Font}.
    */

    public static Font load(String path, int fontSize) {
        try(InputStream Stream = new FileInputStream(path)) {
            return load(Stream, fontSize);
        } catch (IOException e) {
            throw new RuntimeException("...", e);
        }
    }

    /**
    * Loads a Font.
    *
    * @param fontStream Stream.
    * @param fontSize Font Size.
    *
    * @return a {@link Font}.
    */

    public static Font load(InputStream fontStream, int fontSize) {
        java.awt.Font awtFont;

        try {
            awtFont = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, fontStream).deriveFont(java.awt.Font.PLAIN, fontSize);
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException("...", e);
        }

        BufferedImage Image = new BufferedImage(1, 1, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D Graphics = Image.createGraphics();

        Graphics.setFont(awtFont);
        java.awt.FontMetrics awtFontMetrics = Graphics.getFontMetrics();
        Graphics.dispose();

        GlyphMetrics[] glyphMetrics = GetGlyphMetrics(awtFontMetrics);
        FontMetrics fontMetrics = GetFontMetrics(awtFontMetrics, glyphMetrics);

        return new Font(fontMetrics.width(), fontMetrics.height(), fontMetrics.ascent(), fontMetrics.descent(), fontMetrics.lineGap(), GetGlyphs(fontMetrics, glyphMetrics), BakeFontBitmap(awtFont, fontMetrics, glyphMetrics));
    }

    /**
    * ...
    *
    * @param awtFontMetrics ...
    *
    * @return ...
    */

    private static GlyphMetrics[] GetGlyphMetrics(java.awt.FontMetrics awtFontMetrics) {
        GlyphMetrics[] Glyphs = new GlyphMetrics[256];

        int OffsetX = 0;
        int OffsetY = 0;

        for(int i = 32; i < 256; i++) {
            if(i == 127)
                continue;

            Glyphs[i] = new GlyphMetrics(0, 0, awtFontMetrics.charWidth(i), awtFontMetrics.getHeight(), OffsetX, OffsetY, 0, 0);
            OffsetX += awtFontMetrics.charWidth(i);
        }

        return Glyphs;
    }

    /**
    * ...
    *
    * @param GlyphMetrics ...
    *
    * @return ...
    */

    private static FontMetrics GetFontMetrics(java.awt.FontMetrics awtFontMetrics, GlyphMetrics[] GlyphMetrics) {
        int Width = 0;
        int Height = 0;

        for(int i = 32; i < 256; i++) {
            if(i == 127)
                continue;

            Width += GlyphMetrics[i].width();
            Height = Math.max(Height, GlyphMetrics[i].height());
        }

        return new FontMetrics(Width, Height, awtFontMetrics.getAscent(), awtFontMetrics.getDescent(), awtFontMetrics.getLeading());
    }

    /**
    * ...
    *
    * @param fontMetrics ...
    * @param glyphMetrics ...
    *
    * @return ...
    */

    private static Glyph[] GetGlyphs(FontMetrics fontMetrics, GlyphMetrics[] glyphMetrics) {
        Glyph[] Glyphs = new Glyph[256];

        for(int i = 32; i < 256; i++) {
            if(i == 127)
                continue;

            float U1 = (glyphMetrics[i].offsetX()) / (float) fontMetrics.width();
            float V1 = (glyphMetrics[i].offsetY()) / (float) fontMetrics.height();
            float U2 = (glyphMetrics[i].offsetX() + glyphMetrics[i].width()) / (float) fontMetrics.width();
            float V2 = (glyphMetrics[i].offsetY() + glyphMetrics[i].height()) / (float) fontMetrics.height();

            Glyphs[i] = new Glyph(i, 0, 0, glyphMetrics[i].width(), glyphMetrics[i].height(), U1, V1, U2, V2);
        }

        return Glyphs;
    }

    /**
    * ...
    *
    * @param awtFont ...
    * @param fontMetrics ...
    *
    * @return ...
    */

    private static int[] BakeFontBitmap(java.awt.Font awtFont, FontMetrics fontMetrics, GlyphMetrics[] glyphMetrics) {
        BufferedImage Image = new BufferedImage(fontMetrics.width(), fontMetrics.height(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D Graphics = Image.createGraphics();

        Graphics.setFont(awtFont);
        Graphics.setColor(Color.WHITE);
        Graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        for(int i = 32; i < 256; i++) {
            if(i == 127)
                continue;

            Graphics.drawString(Character.toString(i), glyphMetrics[i].offsetX(), fontMetrics.ascent());
        }

        return Image.getRGB(0, 0, Image.getWidth(), Image.getHeight(), null, 0, Image.getWidth());
    }
}