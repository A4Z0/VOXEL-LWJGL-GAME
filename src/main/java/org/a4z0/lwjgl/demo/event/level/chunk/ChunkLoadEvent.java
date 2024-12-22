package org.a4z0.lwjgl.demo.event.level.chunk;

import org.a4z0.lwjgl.demo.level.chunk.Chunk;

public final class ChunkLoadEvent extends ChunkEvent {

    /**
    * Construct a {@link ChunkEvent}.
    *
    * @param chunk ...
    */

    public ChunkLoadEvent(Chunk chunk) {
        super(chunk);
    }
}