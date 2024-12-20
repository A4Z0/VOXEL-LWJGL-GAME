package org.a4z0.lwjgl.demo.chunk;

import org.a4z0.lwjgl.demo.level.Level;
import org.a4z0.lwjgl.demo.voxel.IVoxel;

public interface Chunk {

    int CHUNK_SIZE_X = 256;
    int CHUNK_SIZE_Y = 256;
    int CHUNK_SIZE_Z = 256;

    /**
    * Represents an Empty Chunk.
    */

    Chunk EMPTY = new Chunk() {

        @Override
        public Level getLevel() {
            return null;
        }

        @Override
        public ChunkPosition getPosition() {
            return null;
        }

        @Override
        public IVoxel getVoxelAt(int x, int y, int z) {
            return IVoxel.EMPTY;
        }

        @Override
        public boolean isLoaded() {
            return false;
        }

        @Override
        public boolean load() {
            return false;
        }

        @Override
        public boolean load(boolean g) {
            return false;
        }

        @Override
        public boolean unload() {
            return false;
        }

        @Override
        public boolean unload(boolean l) {
            return false;
        }

        @Override
        public void tick() {

        }

        @Override
        public String toString() {
            return "EmptyChunk{}";
        }
    };

    /**
    * @return the Level.
    */

    Level getLevel();

    /**
    * @return the Position.
    */

    ChunkPosition getPosition();

    /**
    * Retrieves a {@link IVoxel} at the coordinates.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return a {@link IVoxel}.
    */

    IVoxel getVoxelAt(int x, int y, int z);

    /**
    * @return true if is loaded, false otherwise.
    */

    boolean isLoaded();

    /**
    * Loads this {@link Chunk}.
    *
    * @return true if it loads correctly, false otherwise.
    */

    boolean load();

    /**
    * Loads this {@link Chunk}.
    *
    * @param g Generates this Chunk when true.
    *
    * @return true if it loads correctly, false otherwise.
    */

    boolean load(boolean g);

    /**
    * Unloads this {@link Chunk}.
    *
    * @return true if it unloads correctly, false otherwise.
    */

    boolean unload();

    /**
    * Unloads this {@link Chunk}.
    *
    * @param l Unloads slowly when true.
    *
    * @return true if it unloads correctly, false otherwise.
    */

    boolean unload(boolean l);

    /**
    * Ticks this Chunk.
    */

    void tick();

    /**
    * @return this as a {@link Chunk}.
    */

    @Override
    String toString();
}