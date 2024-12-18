package org.a4z0.lwjgl.demo.level.chunk;

import org.a4z0.lwjgl.demo.Main;
import org.a4z0.lwjgl.demo.event.level.chunk.ChunkLoadEvent;
import org.a4z0.lwjgl.demo.event.level.chunk.ChunkUnloadEvent;
import org.a4z0.lwjgl.demo.level.Level;
import org.a4z0.lwjgl.demo.level.chunk.generation.planet.DevPlanetChunkGenerator;
import org.a4z0.lwjgl.demo.level.chunk.layer.ChunkLayers;
import org.a4z0.lwjgl.demo.level.chunk.layer.voxel.Voxel;

public class Chunk {

    public static final int CHUNK_SIZE_X = 256;
    public static final int CHUNK_SIZE_Y = 256;
    public static final int CHUNK_SIZE_Z = 256;

    private final Level level;
    private final ChunkPosition position;

    private final int[] palette = new int[CHUNK_SIZE_X * CHUNK_SIZE_Y * CHUNK_SIZE_Z];
    private final ChunkLayers layers;

    private boolean isLoaded;

    /**
    * Construct a {@link Chunk}.
    *
    * @param level Level.
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    public Chunk(Level level, int x, int y, int z) {
        this(level, ChunkPosition.ofVoxel(x, y, z));
    }

    /**
    * Construct a {@link Chunk}.
    *
    * @param level Level.
    * @param position Position.
    */

    public Chunk(Level level, ChunkPosition position) {
        this.level = level;
        this.position = position;
        this.layers = new ChunkLayers(this);
    }

    /**
    * @return ...
    */

    public Level getLevel() {
        return this.level;
    }

    /**
    * @return ...
    */

    public ChunkPosition getPosition() {
        return this.position;
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
        return Voxel.of(this, this.palette, x, y, z);
    }

    /**
    * @return true if is loaded, false otherwise.
    */

    public boolean isLoaded() {
        return this.isLoaded;
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

        int[] map = DevPlanetChunkGenerator.surface(this);

        if(g)
            for (int x = 0; x < Chunk.CHUNK_SIZE_X; x++)
                for (int y = 0; y < Chunk.CHUNK_SIZE_Y; y++)
                    for (int z = 0; z < Chunk.CHUNK_SIZE_Z; z++)
                        DevPlanetChunkGenerator.generate(this, x, y, z, map);

        Main.EVENT_BUS.submit(new ChunkLoadEvent(this));

        this.isLoaded = true;

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

        Main.EVENT_BUS.submit(new ChunkUnloadEvent(this));

        this.isLoaded = false;

        return true;
    }

    /**
    * Ticks this Chunk.
    */

    public void tick() {
        if(this.isLoaded())
            this.layers.render();
    }

    @Override
    public String toString() {
        return "Chunk{" + this.getPosition() + "}";
    }

}