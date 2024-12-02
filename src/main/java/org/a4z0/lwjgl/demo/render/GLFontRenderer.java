package org.a4z0.lwjgl.demo.render;

import org.a4z0.lwjgl.demo.Main;
import org.a4z0.lwjgl.demo.font.Fonts;
import org.a4z0.lwjgl.demo.font.GLFont;
import org.a4z0.lwjgl.demo.font.Glyph;
import org.a4z0.lwjgl.demo.shader.ShaderPrograms;
import org.a4z0.lwjgl.demo.util.ByteBuf;
import org.a4z0.lwjgl.demo.util.Color;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public final class GLFontRenderer {

    public static final int ELEMENTS = 2 + 2 + 1;
    public static final int ELEMENTS_STRIDE = ELEMENTS * Float.BYTES;

    public final int VERTEX_BUFFER_OBJECT;
    public final int VERTEX_ARRAY_OBJECT;

    public final ByteBuf BYTE_BUF = new ByteBuf();

    /**
    * Construct a {@link GLFontRenderer}.
    */

    public GLFontRenderer() {
        this.VERTEX_BUFFER_OBJECT = glGenBuffers();
        this.VERTEX_ARRAY_OBJECT = glGenVertexArrays();
    }

    /**
    * ...
    *
    * @param t ...
    * @param x ...
    * @param y ...
    */

    public void drawString(String t, float x, float y) {
        this.drawString(t, x, y, 1f);
    }

    /**
    * ...
    *
    * @param t ...
    * @param x ...
    * @param y ...
    * @param scale ...
    */

    public void drawString(String t, float x, float y, float scale) {
        this.drawString(t, x, y, scale, new Color(255, 255, 255).getColor());
    }

    /**
    * ...
    *
    * @param Text ...
    * @param x ...
    * @param y ...
    * @param scale ...
    * @param i ...
    */

    public void drawString(String Text, float x, float y, float scale, int i) {
        float Skip = 0;
        float OffsetX = 0;

        for(int Index = 0; Index < Text.length(); Index++) {
            if(Text.codePointAt(Index) == '\n') {
                Skip++;
                OffsetX = 0;

                continue;
            }

            GLFont Font = Fonts.MINECRAFT;
            Glyph Glyph = Font.getGlyph(Text.codePointAt(Index));

            float OffsetY = Skip * Glyph.getHeight();

            float X1 =  ((OffsetX + x) * scale) - (Main.GL_WINDOW_WIDTH / 2f);
            float Y1 =  (Main.GL_WINDOW_HEIGHT / 2f) - ((y + OffsetY) * scale);
            float X2 =  ((x + Glyph.getWidth() + OffsetX) * scale) - (Main.GL_WINDOW_WIDTH / 2f);
            float Y2 =  (Main.GL_WINDOW_HEIGHT / 2f) - ((y + Glyph.getHeight() + OffsetY) * scale);

            float NX1 = (X1 / (Main.GL_WINDOW_WIDTH  / 2f));
            float NY1 = (Y1 / (Main.GL_WINDOW_HEIGHT / 2f));
            float NX2 = (X2 / (Main.GL_WINDOW_WIDTH  / 2f));
            float NY2 = (Y2 / (Main.GL_WINDOW_HEIGHT / 2f));

            float U1 = (Glyph.getX() / (float) Font.getWidth());
            float V1 = (Glyph.getY() / (float) Font.getHeight());
            float U2 = ((Glyph.getX() + Glyph.getWidth()) / (float) Font.getWidth());
            float V2 = ((Glyph.getY() + Glyph.getHeight()) / (float) Font.getHeight());

            this.BYTE_BUF.put(NX1).put(NY1).put(U1).put(V1).put(i);
            this.BYTE_BUF.put(NX1).put(NY2).put(U1).put(V2).put(i);
            this.BYTE_BUF.put(NX2).put(NY2).put(U2).put(V2).put(i);
            this.BYTE_BUF.put(NX1).put(NY1).put(U1).put(V1).put(i);
            this.BYTE_BUF.put(NX2).put(NY2).put(U2).put(V2).put(i);
            this.BYTE_BUF.put(NX2).put(NY1).put(U2).put(V1).put(i);

            OffsetX+= Glyph.getWidth();
        }

        glDisable(GL_DEPTH_TEST);
        glBindBuffer(GL_ARRAY_BUFFER, this.VERTEX_BUFFER_OBJECT);
        glBindVertexArray(this.VERTEX_ARRAY_OBJECT);
        glBufferData(GL_ARRAY_BUFFER, this.BYTE_BUF.asByteBuffer(), GL_STATIC_DRAW);

        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);

        glVertexAttribPointer(0, 2, GL_FLOAT, true, ELEMENTS_STRIDE, 0);
        glVertexAttribPointer(1, 2, GL_FLOAT, true, ELEMENTS_STRIDE, (2) * Float.BYTES);
        glVertexAttribPointer(2, 1, GL_FLOAT, true, ELEMENTS_STRIDE, (2 + 2) * Float.BYTES);

        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, Fonts.MINECRAFT.getID());

        ShaderPrograms.TEXT_SHADER_PROGRAM.bind();
        glDrawArrays(GL_TRIANGLES, 0, this.BYTE_BUF.size() / ELEMENTS);
        ShaderPrograms.TEXT_SHADER_PROGRAM.unbind();

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);

        glEnable(GL_DEPTH_TEST);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);

        this.BYTE_BUF.clear();
    }

    /**
    * ...
    */

    public void delete() {
        glDeleteBuffers(this.VERTEX_BUFFER_OBJECT);
        glDeleteVertexArrays(this.VERTEX_BUFFER_OBJECT);
    }
}