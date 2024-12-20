package org.a4z0.lwjgl.demo.layer;

import org.a4z0.lwjgl.demo.chunk.Chunk;
import org.a4z0.lwjgl.demo.mesh.Mesh;
import org.a4z0.lwjgl.demo.registry.Registries;
import org.a4z0.lwjgl.demo.resource.Key;
import org.a4z0.lwjgl.demo.util.ByteBuf;
import org.joml.Matrix4f;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.*;

public class ChunkLayer {

    private static final int ELEMENTS_SIZE = 3 + 1 + 1;
    private static final int ELEMENTS_STRIDE = ELEMENTS_SIZE * Float.BYTES;
    private static final Matrix4f MATRIX_4_F = new Matrix4f();

    private static final byte PRE_COMPUTE = 0x01;
    private static final byte COMPUTED = PRE_COMPUTE | 0x02;
    private static final byte BAKED = 0x04;
    private static final byte UNREADY = 0x00;
    private static final byte READY_FOR_RENDERING = COMPUTED | BAKED;
    private static final byte CLOSED = -1;

    protected int a;
    protected int o;

    protected Chunk c;
    protected final ChunkLayerPosition position;

    protected byte w;
    protected ByteBuf b;

    protected Future<Void> f;

    /**
    * Construct a {@link ChunkLayer}.
    *
    * @param c ...
    * @param position ...
    */

    public ChunkLayer(Chunk c, ChunkLayerPosition position) {
        this.c = c;
        this.b = new ByteBuf();
        this.w = 0;
        this.position = position;
    }

    /**
    * Computes this {@link ChunkLayer}.
    */

    protected void compute() {
        this.f = CompletableFuture
            .runAsync(() -> Mesh.build(this.b, this.c, this.position))
            .thenRun(() -> this.w |= COMPUTED);

        this.w |= PRE_COMPUTE;
    }

    /**
    * Bakes this {@link ChunkLayer}.
    */

    protected void bake() {
        this.a = glGenVertexArrays();
        this.o = glGenBuffers();

        glBindVertexArray(this.a);
        glBindBuffer(GL_ARRAY_BUFFER, this.o);
        glBufferData(GL_ARRAY_BUFFER, this.b.asByteBuffer(), GL_STATIC_DRAW);

        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 3, GL_FLOAT, true, ELEMENTS_STRIDE, 0);

        glEnableVertexAttribArray(1);
        glVertexAttribPointer(1, 1, GL_FLOAT, true, ELEMENTS_STRIDE, (3) * Float.BYTES);

        glEnableVertexAttribArray(2);
        glVertexAttribPointer(2, 1, GL_FLOAT, true, ELEMENTS_STRIDE, (3 + 1) * Float.BYTES);

        glBindVertexArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        this.w |= BAKED;
    }

    /**
    * Draws this {@link ChunkLayer}.
    */

    protected void draw() {
        if(this.b.size() == 0)
            return;

        Registries.SHADER_PROGRAM.getOrThrow(Key.of("world")).setUniform4fv("transform", new Matrix4f().translate(0, 0 ,0));

        glBindVertexArray(this.a);

        glDrawArrays(GL_TRIANGLES, 0, (this.b.size() / ELEMENTS_SIZE));

        glBindVertexArray(0);
    }

    /**
    * Renders this {@link ChunkLayer}.
    */

    public void render() {
        switch (this.w) {
            case UNREADY ->
                this.compute();
            case COMPUTED ->
                this.bake();
            case READY_FOR_RENDERING ->
                this.draw();
        }
    }

    /**
    * Deletes this {@link ChunkLayer}.
    */

    public void delete() {
        this.delete(false);
    }

    /**
    * Deletes this {@link ChunkLayer}.
    *
    * @param b ...
    */

    public void delete(boolean b) {
        glDeleteVertexArrays(this.a);
        glDeleteBuffers(this.o);

        this.a = 0;
        this.o = 0;

        if(this.f != null)
            this.f.cancel(true);

        this.b.clear();

        if(!b) {
            this.f = null;
            this.b = null;
            this.c = null;

            this.w = CLOSED;

            return;
        }

        this.w = UNREADY;
    }
}