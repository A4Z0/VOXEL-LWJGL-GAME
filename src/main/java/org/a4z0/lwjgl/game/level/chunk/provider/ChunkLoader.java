package org.a4z0.lwjgl.game.level.chunk.provider;

import org.a4z0.lwjgl.api.level.chunk.Chunk;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ChunkLoader {

    private final ExecutorService Executor;

    /**
    * Construct a {@link ChunkLoader}.
    *
    * @param maxParallelLoads ...
    */

    public ChunkLoader(int maxParallelLoads) {
        this.Executor = Executors.newFixedThreadPool(maxParallelLoads);
    }

    /**
    * ...
    *
    * @param chunk    ...
    * @param generate ...
    */

    public void submit(Chunk chunk, boolean generate) {
        Executor.submit(() -> chunk.load(generate));
    }

    /**
    * ...
    *
    * @param chunk ...
    * @param lazy ...
    */

    public void remove(Chunk chunk, boolean lazy) {
        Executor.submit(() -> chunk.unload(lazy));
    }
}