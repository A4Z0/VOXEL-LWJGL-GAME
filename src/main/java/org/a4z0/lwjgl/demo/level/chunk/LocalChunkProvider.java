package org.a4z0.lwjgl.demo.level.chunk;

import org.a4z0.lwjgl.demo.level.Level;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public final class LocalChunkProvider implements ChunkProvider {

    private static final int MAX_CACHED_CHUNKS = 16;
    private static final int MAX_PARALLEL_LOADS = 2;

    private final ChunkLoader CHUNK_LOADER = new ChunkLoader(MAX_PARALLEL_LOADS);

    private final Map<Long, Chunk> queryWithLong = new HashMap<>();
    private final LinkedList<Long> queryWithLongOrdened = new LinkedList<>();

    private final Level level;

    /**
    * Construct a {@link LocalChunkProvider}.
    *
    * @param level Level.
    */

    public LocalChunkProvider(Level level) {
        this.level = level;
    }

    @Override
    public Level getLevel() {
        return this.level;
    }

    @Override
    public Chunk getChunkAt(int x, int y, int z) {
        synchronized (this.queryWithLong) {
            return this.queryWithLong.get(ChunkPosition.ofVoxel(x, y, z).getIndex());
        }
    }

    @Override
    public void load(int x, int y, int z) {
        this.load(this.queryWithLong.getOrDefault(ChunkPosition.getIndex(x, y, z), new Chunk(this.getLevel(), x, y, z)));
    }

    @Override
    public void load(Chunk Chunk) {
        synchronized (this.queryWithLong) {
            if(this.queryWithLong.size() >= MAX_CACHED_CHUNKS) {
                this.unload(this.queryWithLong.get(this.queryWithLongOrdened.removeFirst()));

                return;
            }

            this.queryWithLong.put(Chunk.getPosition().getIndex(), Chunk);
            this.queryWithLongOrdened.addLast(Chunk.getPosition().getIndex());
            this.CHUNK_LOADER.submit(Chunk, true);
        }
    }

    @Override
    public void unload(Chunk Chunk) {
        synchronized (this.queryWithLong) {
            this.CHUNK_LOADER.remove(Chunk, true);
            this.queryWithLongOrdened.remove(this.queryWithLong.remove(Chunk.getPosition().getIndex()).getPosition().getIndex());
        }
    }

    @Override
    public void tick() {
        synchronized (this.queryWithLong) {
            for(Chunk Chunk : queryWithLong.values()) {
                if(Chunk.isLoaded())
                    Chunk.tick();
            }
        }
    }
}