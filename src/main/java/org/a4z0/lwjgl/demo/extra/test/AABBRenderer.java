package org.a4z0.lwjgl.demo.extra.test;

import org.a4z0.lwjgl.demo.math.aabb.AABBfc;
import org.a4z0.lwjgl.demo.math.aabb.AABBic;
import org.a4z0.lwjgl.demo.buffer.ByteBuf;
import org.a4z0.lwjgl.demo.color.Color;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public final class AABBRenderer {

    private static final int ELEMENTS_SIZE = 3 + 1;
    private static final int ELEMENTS_STRIDE = ELEMENTS_SIZE * Float.BYTES;

    private static final int VERTEX_ARRAY_OBJECT;
    private static final int VERTEX_BUFFER_OBJECT;

    private static final ByteBuf BYTE_BUF = new ByteBuf();

    static {
        VERTEX_ARRAY_OBJECT = glGenVertexArrays();
        VERTEX_BUFFER_OBJECT = glGenBuffers();

        glBindVertexArray(VERTEX_ARRAY_OBJECT);
        glBindBuffer(GL_ARRAY_BUFFER, VERTEX_BUFFER_OBJECT);

        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 3, GL_FLOAT, true, ELEMENTS_STRIDE, 0);

        glEnableVertexAttribArray(1);
        glVertexAttribPointer(1, 1, GL_FLOAT, true, ELEMENTS_STRIDE, (3) * Float.BYTES);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    public static void draw(AABBic aabb, int x, int y, int z, int r, int g, int b) {
        draw(aabb.getLowerX(), aabb.getLowerY(), aabb.getLowerZ(), aabb.getUpperX(), aabb.getUpperY(), aabb.getUpperZ(), x, y, z, r, g, b);
    }

    public static void draw(AABBfc aabb, float x, float y, float z, int r, int g, int b) {
        draw(aabb.getLowerX(), aabb.getLowerY(), aabb.getLowerZ(), aabb.getUpperX(), aabb.getUpperY(), aabb.getUpperZ(), x, y, z, r, g, b);
    }

    public static void draw(float x1, float y1, float z1, float x2, float y2, float z2, float x, float y, float z, int r, int g, int b) {
        draw(x1, y1, z1, x2, y2, z2, x, y, z, new Color(r, g, b).asARGB());
    }

    public static void draw(float x1, float y1, float z1, float x2, float y2, float z2, float x, float y, float z, int i) {
        BYTE_BUF.put(x1).put(y1).put(z1).put(i);
        BYTE_BUF.put(x2).put(y1).put(z1).put(i);
        BYTE_BUF.put(x1).put(y1).put(z2).put(i);
        BYTE_BUF.put(x2).put(y1).put(z2).put(i);
        BYTE_BUF.put(x1).put(y2).put(z1).put(i);
        BYTE_BUF.put(x2).put(y2).put(z1).put(i);
        BYTE_BUF.put(x1).put(y2).put(z2).put(i);
        BYTE_BUF.put(x2).put(y2).put(z2).put(i);
        BYTE_BUF.put(x1).put(y1).put(z1).put(i);
        BYTE_BUF.put(x1).put(y2).put(z1).put(i);
        BYTE_BUF.put(x2).put(y1).put(z1).put(i);
        BYTE_BUF.put(x2).put(y2).put(z1).put(i);
        BYTE_BUF.put(x1).put(y1).put(z2).put(i);
        BYTE_BUF.put(x1).put(y2).put(z2).put(i);
        BYTE_BUF.put(x2).put(y1).put(z2).put(i);
        BYTE_BUF.put(x2).put(y2).put(z2).put(i);
        BYTE_BUF.put(x1).put(y1).put(z1).put(i);
        BYTE_BUF.put(x1).put(y1).put(z2).put(i);
        BYTE_BUF.put(x2).put(y1).put(z1).put(i);
        BYTE_BUF.put(x2).put(y1).put(z2).put(i);
        BYTE_BUF.put(x1).put(y2).put(z1).put(i);
        BYTE_BUF.put(x1).put(y2).put(z2).put(i);
        BYTE_BUF.put(x2).put(y2).put(z1).put(i);
        BYTE_BUF.put(x2).put(y2).put(z2).put(i);
    }

    public static void flush() {
        glEnable(GL_POLYGON_OFFSET_FILL);
        glPolygonOffset(-1.0f, -1.0f); // "Empurra" as linhas do outline para frente

        glBindBuffer(GL_ARRAY_BUFFER, VERTEX_BUFFER_OBJECT);
        glBufferData(GL_ARRAY_BUFFER, BYTE_BUF.asFloatBuffer(), GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(VERTEX_ARRAY_OBJECT);

        glDrawArrays(GL_LINES, 0, 96);

        glBindVertexArray(0);

        glDisable(GL_POLYGON_OFFSET_FILL);

        BYTE_BUF.clear();
    }
}