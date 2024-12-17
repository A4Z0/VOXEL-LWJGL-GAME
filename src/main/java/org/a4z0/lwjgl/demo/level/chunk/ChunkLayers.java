package org.a4z0.lwjgl.demo.level.chunk;

import org.a4z0.lwjgl.demo.math.position.ChunkLayerPosition;

public final class ChunkLayers {

    public static final int AMOUNT_X = 16;
    public static final int AMOUNT_Y = 16;
    public static final int AMOUNT_Z = 16;

    public static final int SIZE_X = Chunk.CHUNK_SIZE_X / AMOUNT_X;
    public static final int SIZE_Y = Chunk.CHUNK_SIZE_Y / AMOUNT_Y;
    public static final int SIZE_Z = Chunk.CHUNK_SIZE_Z / AMOUNT_Z;

    private final Chunk chunk;
    private final ChunkLayer_V2[] LAYER_ARRAY = new ChunkLayer_V2[SIZE_X * SIZE_Y * SIZE_Z];

    /**
    * Construct a {@link ChunkLayers}.
    */

    public ChunkLayers(Chunk chunk) {
        this.chunk = chunk;

        for(int x = 0; x < SIZE_X; x++) {
            for(int y = 0; y < SIZE_Y; y++) {
                for(int z = 0; z < SIZE_Z; z++) {
                    ChunkLayerPosition Position = new ChunkLayerPosition(x, y, z);

                    LAYER_ARRAY[Position.getIndex()] = new ChunkLayer_V2(chunk, Position);

                    //LAYER_ARRAY[ChunkLayerPosition.getIndex(x, y, z)] = new ChunkLayer(chunk, (x * AMOUNT_X), (y * AMOUNT_Y), (z * AMOUNT_Z), (x * AMOUNT_X) + AMOUNT_X, (y * AMOUNT_Y) + AMOUNT_Y, (z * AMOUNT_Z) + AMOUNT_Z);
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

    public ChunkLayer_V2 getLayerAt(int x, int y, int z) {
        return LAYER_ARRAY[ChunkLayerPosition.getIndex(x, y, z)];
    }

    /**
    * ...
    */

    public void render() {
        /*for(int x = 0; x < SIZE_X; x++) {
            for(int y = 0; y < SIZE_Y; y++) {
                for(int z = 0; z < SIZE_Z; z++) {
                    if(Cameras.PLAYER_CAMERA.getFrustum().testPoint(this.chunk.getPosition().getX() + x, this.chunk.getPosition().getY() + y, this.chunk.getPosition().getZ() + z, 12)) {
                        this.getLayerAt(x, y, z).render();
                    }
                }
            }
        }*/

        for (ChunkLayer_V2 chunkLayerV2 : this.LAYER_ARRAY) {
            chunkLayerV2.render();
        }
    }

    /**
    * Deletes all Layers.
    */

    public void delete() {
        this.delete(true);
    }

    /**
    * Deletes all Layers.
    */

    public void delete(boolean b) {
        /*for(ChunkLayer Layer : this.LAYER_ARRAY)
            Layer.delete(b);*/
    }
}