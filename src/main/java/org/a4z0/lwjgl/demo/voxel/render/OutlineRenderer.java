package org.a4z0.lwjgl.demo.voxel.render;

import jdk.jfr.Experimental;
import org.a4z0.lwjgl.demo.voxel.collision.AABB;
import org.a4z0.lwjgl.demo.voxel.shader.pre.VGShaders;
import org.a4z0.lwjgl.demo.voxel.vertex.stream.VertexBuffer;
import org.a4z0.lwjgl.demo.voxel.vertex.stream.VertexStream;
import org.a4z0.lwjgl.demo.voxel.world.chunk.Chunk;
import org.a4z0.lwjgl.demo.voxel.world.chunk.layer.Layer;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

/**
* ...
*/

@Experimental
public class OutlineRenderer {

    /** Represents the bounding edges of a {@link Layer}. */
    public static final AABB VOXEL_BOUNDARIES = new AABB(0, 0, 0, -1, -1, -1);

    /** Represents the bounding edges of a {@link Chunk}. */
    public static final AABB CHUNK_BOUNDARIES = new AABB(Chunk.CHUNK_SIZE_X -1, Chunk.CHUNK_SIZE_Y -1, Chunk.CHUNK_SIZE_Z -1, -1, -1, -1);

    /** Represents the bounding edges of a {@link Layer} */
    public static final AABB BLOCK_BOUNDARIES = new AABB(Layer.LAYER_SIZE_X -1, Layer.LAYER_SIZE_Y -1, Layer.LAYER_SIZE_Z -1, -1, -1, -1);

    /**
    * Construct a {@link OutlineRenderer}.
    */

    public OutlineRenderer() {

    }

    // TODO: Describe what each parameter does.
    public void render(Chunk chunk, float r, float g, float b, float a, float w) {
        this.render(CHUNK_BOUNDARIES, chunk.getX(), 0, chunk.getZ(), r, g, b, a, w);

        for(Layer layer : chunk.getLayers()) {
            if(layer == null)
                continue;

            this.render(BLOCK_BOUNDARIES, layer.getX(), layer.getY(), layer.getZ(), r, g, b, a, w);
        }
    }

    // TODO: Describe what each parameter does.
    public void render(AABB aabb, float x, float y, float z, float r, float g, float b, float a, float w) {
        this.render(aabb.getLowerX(), aabb.getLowerY(), aabb.getLowerZ(), aabb.getUpperX(), aabb.getUpperY(), aabb.getUpperZ(), x, y, z, r, g, b, a, w);
    }

    // TODO: Describe what each parameter does.
    public void render(float x1, float y1, float z1, float x2, float y2, float z2, float x, float y, float z, float r, float g, float b, float a, float width) {
        this.render(new VertexBuffer(), x1, y1, z1, x2, y2, z2, x, y, z, r, g, b, a, width);
    }

    // TODO: Describe what each parameter does.
    public void render(VertexBuffer Buffer, float x1, float y1, float z1, float x2, float y2, float z2, float x, float y, float z, float r, float g, float b, float a, float width) {
        new VertexStream()
            .put(x1 + 0.5f).put(y1 + 0.5f).put(z1 + 0.5f)
            .put(x2 + 0.5f).put(y1 + 0.5f).put(z1 + 0.5f)
            .put(x1 + 0.5f).put(y1 + 0.5f).put(z2 + 0.5f)
            .put(x2 + 0.5f).put(y1 + 0.5f).put(z2 + 0.5f)
            .put(x1 + 0.5f).put(y2 + 0.5f).put(z1 + 0.5f)
            .put(x2 + 0.5f).put(y2 + 0.5f).put(z1 + 0.5f)
            .put(x1 + 0.5f).put(y2 + 0.5f).put(z2 + 0.5f)
            .put(x2 + 0.5f).put(y2 + 0.5f).put(z2 + 0.5f)
            .put(x1 + 0.5f).put(y1 + 0.5f).put(z1 + 0.5f)
            .put(x1 + 0.5f).put(y2 + 0.5f).put(z1 + 0.5f)
            .put(x2 + 0.5f).put(y1 + 0.5f).put(z1 + 0.5f)
            .put(x2 + 0.5f).put(y2 + 0.5f).put(z1 + 0.5f)
            .put(x1 + 0.5f).put(y1 + 0.5f).put(z2 + 0.5f)
            .put(x1 + 0.5f).put(y2 + 0.5f).put(z2 + 0.5f)
            .put(x2 + 0.5f).put(y1 + 0.5f).put(z2 + 0.5f)
            .put(x2 + 0.5f).put(y2 + 0.5f).put(z2 + 0.5f)
            .put(x1 + 0.5f).put(y1 + 0.5f).put(z1 + 0.5f)
            .put(x1 + 0.5f).put(y1 + 0.5f).put(z2 + 0.5f)
            .put(x2 + 0.5f).put(y1 + 0.5f).put(z1 + 0.5f)
            .put(x2 + 0.5f).put(y1 + 0.5f).put(z2 + 0.5f)
            .put(x1 + 0.5f).put(y2 + 0.5f).put(z1 + 0.5f)
            .put(x1 + 0.5f).put(y2 + 0.5f).put(z2 + 0.5f)
            .put(x2 + 0.5f).put(y2 + 0.5f).put(z1 + 0.5f)
            .put(x2 + 0.5f).put(y2 + 0.5f).put(z2 + 0.5f)
        .consume(Buffer);

        VGShaders.OUTLINE_SHADER_PROGRAM.bind();

        glBindVertexArray(Buffer.getVAO().getID());
        glEnableVertexAttribArray(0);

        VGShaders.OUTLINE_SHADER_PROGRAM.setUniform4f("outline_color", r, g, b, a);
        VGShaders.OUTLINE_SHADER_PROGRAM.setUniform4fv("transformation", new Matrix4f().translate(x, y, z));

        glLineWidth(width);
        glDrawArrays(GL_LINES, 0, 24);
        glDisableVertexAttribArray(0);

        glLineWidth(1f);
        Buffer.getVAO().unbind();
        VGShaders.OUTLINE_SHADER_PROGRAM.unbind();

        Buffer.delete();
    }
}