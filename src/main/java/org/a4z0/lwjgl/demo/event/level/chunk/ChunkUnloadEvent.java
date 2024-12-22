package org.a4z0.lwjgl.demo.event.level.chunk;

import org.a4z0.lwjgl.demo.level.chunk.Chunk;

public final class ChunkUnloadEvent extends ChunkEvent {

    /**
    * Construct a {@link ChunkEvent}.
    *
    * @param chunk ...
    */

    public ChunkUnloadEvent(Chunk chunk) {
        super(chunk);
    }
}