package org.a4z0.lwjgl.demo.event.level.chunk;

import org.a4z0.lwjgl.demo.event.Event;
import org.a4z0.lwjgl.demo.level.chunk.Chunk;

public abstract class ChunkEvent extends Event {

    protected final Chunk chunk;

    /**
    * Construct a {@link ChunkEvent}.
    *
    * @param chunk ...
    */

    public ChunkEvent(Chunk chunk) {
        this.chunk = chunk;
    }

    /**
    * @return ...
    */

    public Chunk getChunk() {
        return this.chunk;
    }
}