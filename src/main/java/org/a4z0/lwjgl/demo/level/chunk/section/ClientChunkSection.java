package org.a4z0.lwjgl.demo.level.chunk.section;

import org.a4z0.api.level.block.BlockState;
import org.a4z0.api.level.chunk.Chunk;
import org.a4z0.api.level.chunk.section.ChunkSection;
import org.a4z0.api.math.position.BlockPosition;
import org.a4z0.api.math.position.ChunkSectionPosition;
import org.a4z0.lwjgl.demo.render.RenderableChunkSection;

public class ClientChunkSection implements ChunkSection {

    protected final Chunk chunk;
    protected final ChunkSectionPosition position;
    protected final RenderableChunkSection playerChunkSection;

    /**
    * Construct a {@link ClientChunkSection}.
    *
    * @param chunk Chunk.
    * @param position Position.
    */

    public ClientChunkSection(Chunk chunk, ChunkSectionPosition position) {
        this.chunk = chunk;
        this.position = position;
        this.playerChunkSection = new RenderableChunkSection(this);
    }

    @Override
    public Chunk getChunk() {
        return this.chunk;
    }

    @Override
    public ChunkSectionPosition getPosition() {
        return this.position;
    }

    @Override
    public BlockState getBlockAt(BlockPosition blockPosition) {
        return this.getChunk().getBlockAt(blockPosition);
    }

    @Override
    public void update() {

    }

    @Override
    public void tick() {
        this.playerChunkSection.render();
    }

    @Override
    public String toString() {
        return "ClientChunkSection{" + "\"Position\": " + this.getPosition() + "}";
    }
}