package org.a4z0.lwjgl.demo.level.chunk.layer;

import org.a4z0.lwjgl.demo.Main;
import org.a4z0.lwjgl.demo.event.level.chunk.ChunkRenderEvent;
import org.a4z0.lwjgl.demo.level.chunk.Chunk;

public final class ChunkLayers {

    public static final int AMOUNT_X = 16;
    public static final int AMOUNT_Y = 16;
    public static final int AMOUNT_Z = 16;

    public static final int SIZE_X = Chunk.CHUNK_SIZE_X / AMOUNT_X;
    public static final int SIZE_Y = Chunk.CHUNK_SIZE_Y / AMOUNT_Y;
    public static final int SIZE_Z = Chunk.CHUNK_SIZE_Z / AMOUNT_Z;

    private final Chunk chunk;
    private final ChunkLayer[] LAYER_ARRAY = new ChunkLayer[SIZE_X * SIZE_Y * SIZE_Z];

    /**
    * Construct a {@link ChunkLayers}.
    */

    public ChunkLayers(Chunk chunk) {
        this.chunk = chunk;

        for(int x = 0; x < SIZE_X; x++) {
            for(int y = 0; y < SIZE_Y; y++) {
                for(int z = 0; z < SIZE_Z; z++) {
                    ChunkLayerPosition Position = new ChunkLayerPosition(
                            (this.chunk.getPosition().getX() * 16) + (16 * x),
                            (this.chunk.getPosition().getY() * 16) + (16 * y),
                            (this.chunk.getPosition().getZ() * 16) + (16 * z)
                    );

                    LAYER_ARRAY[Position.getIndex()] = new ChunkLayer(chunk, Position);
                }
            }
        }
    }

    /**
    * ...
    *
    * @param x ...
    * @param y ...
    * @param z ...
    *
    * @return ...
    */

    public ChunkLayer getLayerAt(int x, int y, int z) {
        return LAYER_ARRAY[ChunkLayerPosition.getIndex(x, y, z)];
    }

    /**
    * ...
    */

    public void render() {
        Main.EVENT_BUS.submit(new ChunkRenderEvent.Post());

        for(ChunkLayer Layer : this.LAYER_ARRAY)
            Layer.render();

        Main.EVENT_BUS.submit(new ChunkRenderEvent.After());
    }

    /**
    * Deletes all Layers.
    */

    public void delete() {
        this.delete(true);
    }

    /**
    * Deletes all Layers.
    *
    * @param b ...
    */

    public void delete(boolean b) {
        for(ChunkLayer Layer : this.LAYER_ARRAY)
            Layer.delete(b);
    }
}