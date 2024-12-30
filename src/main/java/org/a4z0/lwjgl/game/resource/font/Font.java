package org.a4z0.lwjgl.game.resource.font;

import static org.lwjgl.opengl.GL11.*;

public final class Font {

    private int texture;
    private int width, height;
    private float ascent, descent, lineGap;
    private Glyph[] glyphs;

    /**
    * Construct a {@link Font}.
    *
    * @param width Width.
    * @param height Height.
    * @param ascent Ascent.
    * @param descent Descent.
    * @param lineGap Line Gap.
    * @param glyphs Glyphs.
    * @param buffer Buffer Data.
    */

    Font(int width, int height, float ascent, float descent, float lineGap, Glyph[] glyphs, int[] buffer) {
        this.glyphs = glyphs;
        this.ascent = ascent;
        this.descent = descent;
        this.lineGap = lineGap;

        glBindTexture(GL_TEXTURE_2D, this.texture = glGenTextures());

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, this.width = width, this.height = height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    /**
    * @return the ID.
    */

    public int getID() {
        return this.texture;
    }

    /**
    * @return the Width.
    */

    public int getWidth() {
        return this.width;
    }

    /**
    * @return the Height.
    */

    public int getHeight() {
        return this.height;
    }

    /**
    * @return the Ascent.
    */

    public float getAscent() {
        return ascent;
    }

    /**
    * @return the Descent.
    */

    public float getDescent() {
        return this.descent;
    }

    /**
    * @return the Line Gap.
    */

    public float getLineGap() {
        return this.lineGap;
    }

    /**
    * Retrieves a {@link Glyph}.
    *
    * @param unicode Unicode.
    *
    * @return a {@link Glyph} or null.
    */

    public Glyph getGlyph(int unicode) {
        for(Glyph glyph : this.glyphs)
            if(glyph != null && glyph.getUnicode() == unicode)
                return glyph;

        return null;
    }

    /**
    * Deletes this {@link Font}.
    */

    public void delete() {
        // We delete the Texture.
        glDeleteTextures(this.texture);

        // Then clear the variables.
        this.texture = 0;
        this.width = height = 0;
        this.ascent = descent = lineGap = 0;
        this.glyphs = null;
    }
}