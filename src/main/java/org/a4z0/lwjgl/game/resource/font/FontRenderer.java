package org.a4z0.lwjgl.game.resource.font;

import org.a4z0.lwjgl.api.color.Color;
import org.a4z0.lwjgl.Key;
import org.a4z0.lwjgl.api.text.TextComponent;
import org.a4z0.lwjgl.game.Game;
import org.a4z0.lwjgl.game.registry.Registries;
import org.a4z0.lwjgl.game.buffer.ByteBuf;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public final class FontRenderer {

    private static final int ELEMENTS = 2 + 2 + 1;
    private static final int ELEMENTS_STRIDE = ELEMENTS * Float.BYTES;

    private static final int VBO;
    private static final int VAO;

    private static final ByteBuf BYTE_BUF = new ByteBuf();

    private static float SKIP = 0;
    private static float OFFSET_X = 0;
    private static float OFFSET_Y = 0;

    private static final Key DEFAULT_FONT = Key.of("minecraft");

    static {
        glBindBuffer(GL_ARRAY_BUFFER, VBO = glGenBuffers());
        glBindVertexArray(VAO = glGenVertexArrays());

        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 2, GL_FLOAT, true, ELEMENTS_STRIDE, 0);

        glEnableVertexAttribArray(1);
        glVertexAttribPointer(1, 2, GL_FLOAT, true, ELEMENTS_STRIDE, (2) * Float.BYTES);

        glEnableVertexAttribArray(2);
        glVertexAttribPointer(2, 1, GL_FLOAT, true, ELEMENTS_STRIDE, (2 + 2) * Float.BYTES);

        glBindVertexArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public static void drawString(TextComponent component, float x, float y) {
        drawString(component, x, y, 1f);
    }

    public static void drawString(TextComponent textComponent, float x, float y, float scale) {
        for(TextComponent component : textComponent.toFlatList())
            drawString(component.getStyle().getFont() != null ? component.getStyle().getFont().getPath() : DEFAULT_FONT.getPath(), component.getText(), x, y, scale, component.getStyle().getColor() != null ? component.getStyle().getColor() : Color.WHITE);
    }

    public static void drawString(String family, String text, float x, float y) {
        drawString(family, text, x, y, 1f);
    }

    public static void drawString(String family, String text, float x, float y, float scale) {
        drawString(family, text, x, y, scale, Color.WHITE);
    }

    public static void drawString(String family, String text, float x, float y, float scale, int r, int g, int b, int a) {
        drawString(family, text, x, y, scale, new Color(r, g, b, a));
    }

    public static void drawString(String family, String text, float x, float y, float scale, Color color) {
        drawString(Registries.FONT.getOrThrow(Key.of(family)), text, x, y, scale, color.asARGB());
    }

    public static void drawString(Font font, String text, float x, float y, float scale, int i) {
        for(int Index = 0; Index < text.length(); Index++) {
            if(text.codePointAt(Index) == '\n') {
                SKIP++;
                OFFSET_X = 0;

                continue;
            }

            Glyph Glyph = font.getGlyph(text.codePointAt(Index));

            if(Glyph == null)
                continue;

            OFFSET_Y = SKIP * font.getHeight();

            float NX1;
            float NY1;
            float NX2;
            float NY2;

            //
            NY1 = Game.getHeight();
            NY2 = Game.getHeight();

            //
            NX1 =  (OFFSET_X + x + Glyph.getX()) * scale;
            NY1 -=  (OFFSET_Y + y + Glyph.getY()) * scale;
            NX2 =  (OFFSET_X + x + Glyph.getWidth()) * scale;
            NY2 -=  (OFFSET_Y + y + Glyph.getHeight()) * scale;

            //
            NX1 -= Game.getWidth();
            NX2 -= Game.getWidth();

            //
            NX1 /= Game.getWidth();
            NY1 /= Game.getHeight();
            NX2 /= Game.getWidth();
            NY2 /= Game.getHeight();

            BYTE_BUF.put(NX1).put(NY1).put(Glyph.getU1()).put(Glyph.getV1()).put(i);
            BYTE_BUF.put(NX1).put(NY2).put(Glyph.getU1()).put(Glyph.getV2()).put(i);
            BYTE_BUF.put(NX2).put(NY2).put(Glyph.getU2()).put(Glyph.getV2()).put(i);
            BYTE_BUF.put(NX1).put(NY1).put(Glyph.getU1()).put(Glyph.getV1()).put(i);
            BYTE_BUF.put(NX2).put(NY2).put(Glyph.getU2()).put(Glyph.getV2()).put(i);
            BYTE_BUF.put(NX2).put(NY1).put(Glyph.getU2()).put(Glyph.getV1()).put(i);

            OFFSET_X += Glyph.getWidth();
        }

        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, font.getID());
    }

    public static void flush() {
        glBindBuffer(GL_ARRAY_BUFFER, VBO);
        glBufferData(GL_ARRAY_BUFFER, BYTE_BUF.asByteBuffer(), GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);

        glDisable(GL_DEPTH_TEST);
        glBindVertexArray(VAO);

        glDrawArrays(GL_TRIANGLES, 0, BYTE_BUF.size() / ELEMENTS);

        glBindTexture(GL_TEXTURE_2D, 0);
        glBindVertexArray(0);
        glEnable(GL_DEPTH_TEST);

        BYTE_BUF.clear();

        SKIP = 0;
        OFFSET_X = 0;
        OFFSET_Y = 0;
    }
}