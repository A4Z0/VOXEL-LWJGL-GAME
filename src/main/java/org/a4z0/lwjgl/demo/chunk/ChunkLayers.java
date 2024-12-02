package org.a4z0.lwjgl.demo.chunk;

public class ChunkLayers {

    public static final int AMOUNT_X = 16;
    public static final int AMOUNT_Y = 16;
    public static final int AMOUNT_Z = 16;

    public static final int SIZE_X = Chunk.CHUNK_SIZE_X / AMOUNT_X;
    public static final int SIZE_Y = Chunk.CHUNK_SIZE_Y / AMOUNT_Y;
    public static final int SIZE_Z = Chunk.CHUNK_SIZE_Z / AMOUNT_Z;

    protected final ChunkLayer[] LAYER_ARRAY = new ChunkLayer[SIZE_X * SIZE_Y * SIZE_Z];

    /**
    * Construct a {@link ChunkLayers}.
    */

    public ChunkLayers(Chunk chunk) {
        for(int x = 0; x < SIZE_X; x++) {
            for(int y = 0; y < SIZE_Y; y++) {
                for(int z = 0; z < SIZE_Z; z++) {
                    LAYER_ARRAY[getIndex(x, y, z)] = new ChunkLayer(chunk, (x * AMOUNT_X), (y * AMOUNT_Y), (z * AMOUNT_Z), (x * AMOUNT_X) + AMOUNT_X, (y * AMOUNT_Y) + AMOUNT_Y, (z * AMOUNT_Z) + AMOUNT_Z);
                }
            }
        }
    }

    private int getRelative(int i) {
        return i & 15;
    }

    private int getIndex(int x, int y, int z) {
        return (getRelative(y) << 4 | getRelative(z)) << 4 | getRelative(x);
    }

    /**
    * ...
    */

    public void render() {
        for(ChunkLayer Layer : this.LAYER_ARRAY)
            Layer.render();
    }

    /**
    * ...
    */

    public void delete() {
        for(ChunkLayer Layer : this.LAYER_ARRAY)
            Layer.delete(true);
    }
}