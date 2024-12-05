package org.a4z0.lwjgl.demo.resource.font;

import org.a4z0.lwjgl.demo.registry.Key;
import org.a4z0.lwjgl.demo.registry.Registries;
import org.a4z0.lwjgl.demo.resource.Resource;
import org.a4z0.lwjgl.demo.resource.texture.Texture;
import org.a4z0.lwjgl.demo.util.ByteBuf;

import static org.lwjgl.opengl.GL11.*;

public final class Font implements Resource {

    private int a;
    private Key k;
    private int w;
    private int h;
    private Glyph[] g;

    /**
    * Construct a {@link Texture}.
    *
    * @param k Key.
    * @param w Width.
    * @param h Height.
    * @param g Glyphs.
    * @param b Buffer Data.
    */

    Font(Key k, int w, int h, Glyph[] g, ByteBuf b) {
        this.g = g;
        glBindTexture(GL_TEXTURE_2D, this.a = glGenTextures());

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, this.w = w, this.h = h, 0, GL_RGBA, GL_UNSIGNED_BYTE, b.asByteBuffer());
        glBindTexture(GL_TEXTURE_2D, 0);

        Registries.FONT_REGISTRY.register(this.k = k, this);
    }

    /**
    * @return the Key.
    */

    public Key getKey() {
        return this.k;
    }

    /**
    * @return the ID.
    */

    public int getID() {
        return this.a;
    }

    /**
    * @return the Width.
    */

    public int getWidth() {
        return this.w;
    }

    /**
    * @return the Height.
    */

    public int getHeight() {
        return this.h;
    }

    /**
    * Retrieves a {@link Glyph}.
    *
    * @param u Unicode.
    *
    * @return a {@link Glyph} or null.
    */

    public Glyph getGlyph(int u) {
        for(Glyph glyph : this.g)
            if(glyph != null && glyph.getUnicode() == u)
                return glyph;

        return null;
    }

    /**
    * Deletes this {@link Font}.
    */

    public void delete() {
        // Remove this Font from our registry.
        Registries.FONT_REGISTRY.unregister(this.k);

        // We delete the Texture.
        glDeleteTextures(this.a);

        // Then clear the variables.
        this.a = 0;
        this.k = null;
        this.w = 0;
        this.h = 0;
        this.g = null;
    }
}