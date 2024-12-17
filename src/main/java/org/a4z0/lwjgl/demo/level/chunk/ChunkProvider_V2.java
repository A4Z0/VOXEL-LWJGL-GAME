package org.a4z0.lwjgl.demo.level.chunk;

import org.a4z0.lwjgl.demo.level.Level;
import org.a4z0.lwjgl.demo.math.position.ChunkPosition;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ChunkProvider_V2 {

    private static final int maxCache = 100;
    private static final int maxParallelLoads = 2;

    private final Map<Long, Chunk> CHUNK_MAP = new HashMap<>();
    private final LinkedList<Long> CHUNK_ACCESS_ORDER = new LinkedList<>();
    private final ExecutorService chunkLoader = Executors.newFixedThreadPool(maxParallelLoads);

    private final Level Level;

    /**
    * Construct a {@link ChunkProvider_V2}.
    *
    * @param Level ...
    */

    public ChunkProvider_V2(Level Level) {
        this.Level = Level;
    }

    /**
    * @return ...
    */

    public Level getLevel() {
        return this.Level;
    }

    public Chunk getChunk(int x, int y, int z) {
        return this.getChunk(x, y, z, false);
    }
    /**
    * ...
    *
    * @return ....
    */

    public Chunk getChunk(int x, int y, int z, boolean l) {
        ChunkPosition position = new ChunkPosition(x, y, z);
        long index = position.getIndex();

        if (CHUNK_MAP.containsKey(index)) {
            CHUNK_ACCESS_ORDER.remove(index);
            CHUNK_ACCESS_ORDER.addLast(index);

            return CHUNK_MAP.get(index);
        }

        Chunk newChunk = new Chunk(this.getLevel(), position);

        if(l) {
            addChunk(index, newChunk);
            chunkLoader.submit(() -> newChunk.load(true));
        }

        return newChunk;
    }

    private synchronized void addChunk(long id, Chunk chunk) {
        if(CHUNK_MAP.size() >= maxCache)
            unloadLeastRecentlyUsedChunk();

        CHUNK_MAP.put(id, chunk);
        CHUNK_ACCESS_ORDER.addLast(id);
    }

    public synchronized void removeChunk(long id) {
        Chunk chunk = CHUNK_MAP.remove(id);
        CHUNK_ACCESS_ORDER.remove(id);

        if(chunk != null)
            chunk.unload();
    }

    private void unloadLeastRecentlyUsedChunk() {
        if(!CHUNK_ACCESS_ORDER.isEmpty()) {
            Chunk Chunk = CHUNK_MAP.remove(CHUNK_ACCESS_ORDER.removeFirst());

            if(Chunk != null)
                Chunk.unload();
        }
    }

    public void tick() {
        for (Chunk value : CHUNK_MAP.values()) {
            if(value.isLoaded())
                value.getLayers().render();
        }
    }
}