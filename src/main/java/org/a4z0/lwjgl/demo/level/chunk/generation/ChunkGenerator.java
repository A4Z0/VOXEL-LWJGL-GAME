package org.a4z0.lwjgl.demo.level.chunk.generation;

import org.a4z0.lwjgl.demo.level.chunk.Chunk;
import org.a4z0.lwjgl.demo.level.chunk.generation.noise.OpenSimplex2;

public abstract class ChunkGenerator {

    /**
    * @return ...
    */

    private float[] surface(Chunk chunk) {
        float[] Values = new float[Chunk.CHUNK_SIZE_X * Chunk.CHUNK_SIZE_Z];

        for(int x = 0; x < Chunk.CHUNK_SIZE_X; x++) {
            for(int z = 0; z < Chunk.CHUNK_SIZE_Z; z++) {
                Values[getIndex(x, z)] = OpenSimplex2.noise2(chunk.getLevel().getSeed(), chunk.getPosition().getX() * Chunk.CHUNK_SIZE_X, chunk.getPosition().getZ() * Chunk.CHUNK_SIZE_X);
            }
        }

        return Values;
    }

    /**
    * @return the Relative X-Axis.
    */

    protected static int getRelativeX(int x) {
        return x & 0xFF;
    }

    /**
    * @return the Relative Z-Axis.
    */

    protected static int getRelativeZ(int z) {
        return z & 0xFF;
    }

    /**
    * ...
    *
    * @param x ...
    * @param z ...
    *
    * @return ...
    */

    protected static int getIndex(int x, int z) {
        return getRelativeX(x) << 8 | getRelativeZ(z);
    }
}