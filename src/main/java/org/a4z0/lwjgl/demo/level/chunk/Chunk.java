package org.a4z0.lwjgl.demo.level.chunk;

import org.a4z0.lwjgl.demo.level.chunk.generation.planet.DevPlanetChunkGenerator;
import org.a4z0.lwjgl.demo.level.Level;
import org.a4z0.lwjgl.demo.math.position.ChunkPosition;
import org.a4z0.lwjgl.demo.level.chunk.voxel.Voxel;
import org.a4z0.lwjgl.demo.math.position.VoxelPosition;

public final class Chunk {

    public static final int CHUNK_SIZE_X = 256;
    public static final int CHUNK_SIZE_Y = 256;
    public static final int CHUNK_SIZE_Z = 256;

    private final Level LEVEL;
    private final ChunkPosition POSITION;
    private final int[] PALETTE = new int[CHUNK_SIZE_X * CHUNK_SIZE_Y * CHUNK_SIZE_Z];
    private final ChunkLayers CHUNK_LAYERS = new ChunkLayers(this);

    private boolean IS_LOADED;

    /**
    * Construct a {@link Chunk}.
    *
    * @param level {@link Level} this {@link Chunk} is at.
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    public Chunk(Level level, int x, int y, int z) {
        this(level, new ChunkPosition(x, y, z));
    }

    /**
    * Construct a {@link Chunk}.
    *
    * @param level {@link Level} this {@link Chunk} is at.
    * @param position {@link ChunkPosition Position}.
    */

    public Chunk(Level level, ChunkPosition position) {
        this.LEVEL = level;
        this.POSITION = position;
    }

    /**
    * @return the {@link Level}.
    */

    public Level getLevel() {
        return this.LEVEL;
    }

    /**
    * @return the {@link ChunkPosition Position}.
    */

    public ChunkPosition getPosition() {
        return this.POSITION;
    }

    @Deprecated
    public ChunkLayers getLayers() {
        return this.CHUNK_LAYERS;
    }

    /**
    * Retrieves a {@link Voxel} at the coordinates.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return a {@link Voxel}.
    */

    public Voxel getVoxelAt(int x, int y, int z) {
        return new Voxel() {

            @Override
            public VoxelPosition getPosition() {
                return new VoxelPosition(x + ((Chunk.this.getPosition().getX() >> 4) * 256), y + ((Chunk.this.getPosition().getY() >> 4) * 256), z + ((Chunk.this.getPosition().getZ() >> 4) * 256));
            }

            @Override
            public int getColor() {
                if((x < 0 || x >= CHUNK_SIZE_X) || (y < 0 || y >= CHUNK_SIZE_Y) || (z < 0 || z >= CHUNK_SIZE_Z))
                    return 0;

                return PALETTE[this.getPosition().getIndex()];
            }

            @Override
            public void setColor(byte r, byte g, byte b, byte a) {
                if((x < 0 || x >= CHUNK_SIZE_X) || (y < 0 || y >= CHUNK_SIZE_Y) || (z < 0 || z >= CHUNK_SIZE_Z))
                    return;

                PALETTE[this.getPosition().getIndex()] = (r & 0xFF) | ((g & 0xFF) << 8) | ((b & 0xFF) << 16) | ((a & 0xFF) << 24);
            }

            @Override
            public Voxel getNorth() {
                return getVoxelAt(x, y, z - 1);
            }

            @Override
            public Voxel getSouth() {
                return getVoxelAt(x, y, z + 1);
            }

            @Override
            public Voxel getEast() {
                return getVoxelAt(x + 1, y, z);
            }

            @Override
            public Voxel getWest() {
                return getVoxelAt(x - 1, y, z);
            }

            @Override
            public Voxel getTop() {
                return getVoxelAt(x, y + 1, z);
            }

            @Override
            public Voxel getBottom() {
                return getVoxelAt(x, y - 1, z);
            }
        };
    }

    /**
    * @return true if is loaded, false otherwise.
    */

    public boolean isLoaded() {
        return this.IS_LOADED;
    }

    /**
    * Loads this {@link Chunk}.
    *
    * @return true if it loads correctly, false otherwise.
    */

    public boolean load() {
        return this.load(false);
    }

    /**
    * Loads this {@link Chunk}.
    *
    * @param g Generates this Chunk when true.
    *
    * @return true if it loads correctly, false otherwise.
    */

    public boolean load(boolean g) {
        if(this.isLoaded())
            return false;

        /*if(g) {
            for(int x = 0; x < 256; x++) {
                for(int y = 0; y < 1; y++) {
                    for(int z = 0; z < 256; z++) {
                        int blockX = x / 16;
                        int blockZ = z / 16;

                        if ((blockX + blockZ) % 2 == 0) {
                            this.getVoxelAt(x, y, z).setColor(255, 255, 255);
                        } else {
                            this.getVoxelAt(x, y, z).setColor(0, 0, 0);
                        }
                    }
                }
            }
        }

        System.out.println("[Chunk]: Loaded!");*/

        int[] map = DevPlanetChunkGenerator.surface(this);

        if(g)
            for (int x = 0; x < Chunk.CHUNK_SIZE_X; x++)
                for (int y = 0; y < Chunk.CHUNK_SIZE_Y; y++)
                    for (int z = 0; z < Chunk.CHUNK_SIZE_Z; z++)
                        DevPlanetChunkGenerator.generate(this, x, y, z, map);

        this.IS_LOADED = true;

        return true;
    }

    /**
    * Unloads this {@link Chunk}.
    *
    * @return true if it unloads correctly, false otherwise.
    */

    public boolean unload() {
        return this.unload(true);
    }

    /**
    * Unloads this {@link Chunk}.
    *
    * @param l Unloads slowly when true.
    *
    * @return true if it unloads correctly, false otherwise.
    */

    public boolean unload(boolean l) {
        if(!this.isLoaded())
            return false;

        System.out.println("[Chunk]: Unloaded!");

        return true;
    }

    /**
    * Ticks.
    */

    public void tick() {

    }
}