package org.a4z0.lwjgl.game.level;

import org.a4z0.lwjgl.api.level.ChunkProvider;
import org.a4z0.lwjgl.api.position.ChunkPosition;
import org.a4z0.lwjgl.game.level.chunk.ClientChunk;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.*;

public class ClientChunkProvider implements ChunkProvider {

    public static final int MAX_THREADED_CHUNKS = 4;

    protected final ExecutorService chunkLoadService = Executors.newFixedThreadPool(MAX_THREADED_CHUNKS);
    protected final ExecutorService chunkUnloadService = Executors.newFixedThreadPool(MAX_THREADED_CHUNKS);

    protected final Queue<ChunkPosition> beingLoad = new ConcurrentLinkedQueue<>();
    protected final Queue<ChunkPosition> beingUnload = new ConcurrentLinkedQueue<>();

    protected final ClientLevel level;
    protected final Map<ChunkPosition, ClientChunk> queryWithPosition = new ConcurrentHashMap<>();

    /**
    * Construct a {@link ClientChunkProvider}.
    *
    * @param level Level.
    */

    public ClientChunkProvider(ClientLevel level) {
        this.level = level;
    }

    @Override
    public ClientLevel getLevel() {
        return this.level;
    }

    @Override
    public ClientChunk getChunkAt(int x, int y, int z) {
        return this.getChunkAt(ChunkPosition.ofVoxel(x, y, z));
    }

    /**
    * Retrieves a Chunk.
    *
    * @param position Position.
    *
    * @return a {@link ClientChunk}.
    */

    public ClientChunk getChunkAt(ChunkPosition position) {
        return this.queryWithPosition.getOrDefault(position, ClientChunk.EMPTY);
    }

    @Override
    public void load(int x, int y, int z) {
        this.load(ChunkPosition.ofVoxel(x, y, z));
    }

    /**
    * Loads a Chunk.
    *
    * @param position Position.
    */

    public void load(ChunkPosition position) {
        // Check if the Chunk is not in this provider.
        if(!this.queryWithPosition.containsKey(position))

            // Create a new Chunk if not.
            this.load(new ClientChunk(this.getLevel(), position));

        // If the chunk exists, then we can just load.
        this.load(this.queryWithPosition.get(position));
    }

    /**
    * Loads a Chunk.
    *
    * @param chunk Chunk to be loaded.
    */

    public void load(ClientChunk chunk) {
        // Check if the Chunk is already is this provider.
        if(this.queryWithPosition.containsKey(chunk.getPosition()))
            return;

        // We attach the chunk with the position.
        this.queryWithPosition.put(chunk.getPosition(), chunk);

        // Then we add to the load queue.
        this.beingLoad.add(chunk.getPosition());
    }

    @Override
    public void unload(int x, int y, int z) {
        this.unload(ChunkPosition.ofVoxel(x, y, z));
    }

    /**
    * Unloads a Chunk.
    *
    * @param position Position.
    */

    public void unload(ChunkPosition position) {
        // Check if the Chunk is not in this provider.
        if(!this.queryWithPosition.containsKey(position))
            return;

        // We dettach the chunk.
        this.queryWithPosition.remove(position);

        // Then we add to the unload queue.
        this.beingUnload.add(position);
    }

    /**
    * Unloads a Chunk.
    *
    * @param chunk Chunk to be unloaded.
    */

    public void unload(ClientChunk chunk) {
        this.unload(chunk.getPosition());
    }

    /**
    * Processes tasks between queues asynchronously.
    */

    public void pollTask() {
        while(!this.beingLoad.isEmpty()) {
            this.chunkLoadService.submit(() -> {
                this.queryWithPosition.get(this.beingLoad.poll()).load(true);
            });
        }

        while(!this.beingUnload.isEmpty()) {
            this.chunkUnloadService.submit(() -> {
                this.queryWithPosition.get(this.beingUnload.poll()).unload(true);
            });
        }
    }

    /**
    * Ticks this {@link ClientChunkProvider}.
    */

    public void tick() {
        this.pollTask();

        for(ClientChunk chunk : this.queryWithPosition.values())
            chunk.tick();
    }

    /**
    * Closes this {@link ClientChunkProvider}.
    */

    public void close() {
        try {
            if(!chunkLoadService.awaitTermination(60, TimeUnit.SECONDS))
                chunkLoadService.shutdownNow();

            for(ClientChunk chunk : this.queryWithPosition.values())
                this.unload(chunk);

            if(!chunkUnloadService.awaitTermination(60, TimeUnit.SECONDS))
                chunkUnloadService.shutdownNow();

        } catch (InterruptedException e) {
            throw new RuntimeException("...", e);
        }
    }
}