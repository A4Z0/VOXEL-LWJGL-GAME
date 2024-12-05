package org.a4z0.lwjgl.demo.ui;

import org.a4z0.lwjgl.demo.Main;
import org.a4z0.lwjgl.demo.util.ByteBuf;

import static org.a4z0.lwjgl.demo.resource.shader.ShaderPrograms.CROSS_SHADER_PROGRAM;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public final class Crosshair extends UI {

    public int VERTEX_ARRAY_OBJECT;
    public int VERTEX_BUFFER_OBJECT;

    public final ByteBuf BYTE_BUF = new ByteBuf();
    public int ELEMENTS_COUNT;

    public Crosshair() {
        this.VERTEX_ARRAY_OBJECT = glGenVertexArrays();
        this.VERTEX_BUFFER_OBJECT = glGenBuffers();

        glBindVertexArray(this.VERTEX_ARRAY_OBJECT);
        glBindBuffer(GL_ARRAY_BUFFER, this.VERTEX_BUFFER_OBJECT);

        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 2, GL_FLOAT, false, 2 * Float.BYTES, 0);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    @Override
    public void onRezise(int width, int height) {
        BYTE_BUF.clear();
        BYTE_BUF.put(-((4f / width) * 4f)).put(0.0f);
        BYTE_BUF.put((4f / width) * 4f).put(0.0f);
        BYTE_BUF.put(0.f).put((4f / height) * 4f);
        BYTE_BUF.put(0.f).put(-((4f / height) * 4f));

        ELEMENTS_COUNT = BYTE_BUF.size() / 2;

        glBindBuffer(GL_ARRAY_BUFFER, VERTEX_BUFFER_OBJECT);
        glBufferData(GL_ARRAY_BUFFER, BYTE_BUF.asFloatBuffer(), GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    @Override
    public void onRender() {
        glDisable(GL_DEPTH_TEST);
        glBindVertexArray(this.VERTEX_ARRAY_OBJECT);

        CROSS_SHADER_PROGRAM.bind();
        glDrawArrays(GL_LINES, 0, ELEMENTS_COUNT);
        CROSS_SHADER_PROGRAM.unbind();

        glBindVertexArray(0);
        glEnable(GL_DEPTH_TEST);
    }
}