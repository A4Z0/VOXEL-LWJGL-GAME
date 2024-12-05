package org.a4z0.lwjgl.demo.resource.texture;

import org.a4z0.lwjgl.demo.resource.Resource;
import org.a4z0.lwjgl.demo.util.ByteBuf;

import static org.lwjgl.opengl.GL11.*;

public final class Texture implements Resource {

    private int a;
    private int w;
    private int h;

    /**
    * Construct a {@link Texture}.
    *
    * @param w Width.
    * @param h Height.
    * @param b Buffer Data.
    */

    Texture(int w, int h, ByteBuf b) {
        glBindTexture(GL_TEXTURE_2D, this.a = glGenTextures());

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, this.w = w, this.h = h, 0, GL_RGBA, GL_UNSIGNED_BYTE, b.asByteBuffer());
        glBindTexture(GL_TEXTURE_2D, 0);
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
    * Deletes this {@link Texture}.
    */

    public void delete() {
        // We delete the Texture.
        glDeleteTextures(this.a);

        // Then clear the variables.
        this.a = 0;
        this.w = 0;
        this.h = 0;
    }
}