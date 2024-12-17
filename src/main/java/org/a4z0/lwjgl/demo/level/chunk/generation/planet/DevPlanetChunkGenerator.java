package org.a4z0.lwjgl.demo.level.chunk.generation.planet;

import org.a4z0.lwjgl.demo.level.chunk.Chunk;
import org.a4z0.lwjgl.demo.level.chunk.generation.noise.OpenSimplex2;

import java.util.Random;

public final class DevPlanetChunkGenerator extends PlanetChunkGenerator {

    public static final int MAX_HEIGHT = 256;
    public static final int MIN_HEIGHT = (256 / 2);

    public static final float SMOOTHNESS = 128;

    public static int[] surface(Chunk chunk) {
        int[] map = new int[Chunk.CHUNK_SIZE_X * Chunk.CHUNK_SIZE_Z];

        for(int x = 0; x < Chunk.CHUNK_SIZE_X; x++) {
            for (int z = 0; z < Chunk.CHUNK_SIZE_Z; z++) {
                float value = OpenSimplex2.noise2(
                        chunk.getLevel().getSeed(),
                        ((x + 0.5) + chunk.getPosition().getX() * Chunk.CHUNK_SIZE_X) / SMOOTHNESS,
                        ((-z - 0.5) + chunk.getPosition().getZ() * Chunk.CHUNK_SIZE_Z) / SMOOTHNESS
                );

                value = (value + 1f) * 0.1f;
                map[getIndex(x, z)] = (int) (value * (MAX_HEIGHT - MIN_HEIGHT) + MIN_HEIGHT);
            }
        }

        return map;
    }

    public static void generate(Chunk chunk, int x, int y, int z, int[] map) {
        Random random = new Random(chunk.getLevel().getSeed());
        int surface = map[getIndex(x, z)];

        if(y > surface)
            return;

        int distance = (surface - y);

        if(distance < 256) {
            int diff = random.nextInt() * 25;
            chunk.getVoxelAt(x, y, z).setColor(random.nextInt(60), 255 - random.nextInt(128) - random.nextInt(128), random.nextInt(75));
        }
    }
}