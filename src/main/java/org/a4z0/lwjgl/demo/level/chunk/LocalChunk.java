package org.a4z0.lwjgl.demo.level.chunk;

import org.a4z0.lwjgl.demo.Main;
import org.a4z0.lwjgl.demo.event.level.chunk.ChunkLoadEvent;
import org.a4z0.lwjgl.demo.event.level.chunk.ChunkUnloadEvent;
import org.a4z0.lwjgl.demo.level.Level;
import org.a4z0.lwjgl.demo.level.chunk.layer.ChunkLayers;
import org.a4z0.lwjgl.demo.voxel.IVoxel;

import java.util.Random;

public class LocalChunk implements Chunk {

    public static final int CHUNK_SIZE_X = 256;
    public static final int CHUNK_SIZE_Y = 256;
    public static final int CHUNK_SIZE_Z = 256;

    private final Level level;
    private final ChunkPosition position;

    private final int[] palette = new int[CHUNK_SIZE_X * CHUNK_SIZE_Y * CHUNK_SIZE_Z];
    private final ChunkLayers layers;

    private boolean isLoaded;

    /**
    * Construct a {@link LocalChunk}.
    *
    * @param level Level.
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    public LocalChunk(Level level, int x, int y, int z) {
        this(level, ChunkPosition.ofVoxel(x, y, z));
    }

    /**
    * Construct a {@link LocalChunk}.
    *
    * @param level Level.
    * @param position Position.
    */

    public LocalChunk(Level level, ChunkPosition position) {
        this.level = level;
        this.position = position;
        this.layers = new ChunkLayers(this);
    }

    @Override
    public Level getLevel() {
        return this.level;
    }

    @Override
    public ChunkPosition getPosition() {
        return this.position;
    }

    @Override
    public IVoxel getVoxelAt(int x, int y, int z) {
        if(ChunkPosition.ofVoxel(x, y, z).equals(this.getPosition())) {
            return IVoxel.of(this, this.palette, x, y, z);
        } else {
            return this.getLevel().getVoxelAt(x, y, z);
        }
    }

    @Override
    public boolean isLoaded() {
        return this.isLoaded;
    }

    @Override
    public boolean load() {
        return this.load(false);
    }

    @Override
    public boolean load(boolean g) {
        if(this.isLoaded())
            return false;

        if(g) {
            Random r = new Random();
            for(int x = 0; x < 256; x++)
                for(int y = 0; y < 256; y++)
                    for(int z = 0; z < 256; z++)
                        this.getVoxelAt(x + (this.getPosition().getX() * 16), y + (this.getPosition().getY() * 16), z + (this.getPosition().getZ() * 16)).setColor(r.nextInt(255), r.nextInt(255), r.nextInt(255));
        }

        Main.EVENT_BUS.submit(new ChunkLoadEvent(this));

        this.isLoaded = true;

        return true;
    }

    @Override
    public boolean unload() {
        return this.unload(true);
    }

    @Override
    public boolean unload(boolean l) {
        if(!this.isLoaded())
            return false;

        Main.EVENT_BUS.submit(new ChunkUnloadEvent(this));

        this.isLoaded = false;

        return true;
    }

    @Override
    public void tick() {
        if(this.isLoaded())
            this.layers.render();
    }

    @Override
    public String toString() {
        return "Chunk{"
            + "\"Level\": " + this.getLevel()
            + ", "
            + "\"Position\": " + this.getPosition()
        + "}";
    }
}