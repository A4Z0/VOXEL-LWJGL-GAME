package org.a4z0.lwjgl.game.level.chunk;

import org.a4z0.lwjgl.api.color.Color;
import org.a4z0.lwjgl.api.event.level.chunk.ChunkLoadEvent;
import org.a4z0.lwjgl.api.event.level.chunk.ChunkUnloadEvent;
import org.a4z0.lwjgl.api.block.BlockState;
import org.a4z0.lwjgl.api.level.chunk.Chunk;
import org.a4z0.lwjgl.api.level.chunk.section.ChunkSection;
import org.a4z0.lwjgl.api.position.BlockPosition;
import org.a4z0.lwjgl.api.position.ChunkPosition;
import org.a4z0.lwjgl.api.position.ChunkSectionPosition;
import org.a4z0.lwjgl.game.Main;
import org.a4z0.lwjgl.game.level.ClientLevel;
import org.a4z0.lwjgl.game.block.BlockData;
import org.a4z0.lwjgl.game.level.chunk.section.ClientChunkSection;

import java.util.Random;

public class ClientChunk implements Chunk {

    protected final ClientLevel level;
    protected final ChunkPosition position;

    protected final int[] palette = new int[CHUNK_SIZE_X * CHUNK_SIZE_Y * CHUNK_SIZE_Z];
    protected final ClientChunkSection[] sections = new ClientChunkSection[ChunkSection.SECTION_SIZE_X * ChunkSection.SECTION_SIZE_Y * ChunkSection.SECTION_SIZE_Z];

    protected boolean isLoaded;

    /**
    * Construct a {@link ClientChunk}.
    *
    * @param level Level.
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    public ClientChunk(ClientLevel level, int x, int y, int z) {
        this(level, ChunkPosition.ofVoxel(x, y, z));
    }

    /**
    * Construct a {@link ClientChunk}.
    *
    * @param level Level.
    * @param position Position.
    */

    public ClientChunk(ClientLevel level, ChunkPosition position) {
        this.level = level;
        this.position = position;

        if(this.position == null)
            return;

        for(int x = 0; x < ChunkSection.SECTION_SIZE_X; x++) {
            for(int y = 0; y < ChunkSection.SECTION_SIZE_Y; y++) {
                for(int z = 0; z < ChunkSection.SECTION_SIZE_Z; z++) {
                    ChunkSectionPosition Position = new ChunkSectionPosition(
                        this.getPosition().getX() + (ChunkSection.SECTION_SIZE_X * x),
                        this.getPosition().getY() + (ChunkSection.SECTION_SIZE_Y * y),
                        this.getPosition().getZ() + (ChunkSection.SECTION_SIZE_Z * z)
                    );

                    this.sections[Position.getIndex()] = new ClientChunkSection(this, Position);
                }
            }
        }
    }

    @Override
    public ClientLevel getLevel() {
        return this.level;
    }

    @Override
    public ChunkPosition getPosition() {
        return this.position;
    }

    @Override
    public BlockState getBlockAt(BlockPosition blockPosition) {
        if(ChunkPosition.ofVoxel(blockPosition).equals(this.getPosition())) {
            return new BlockData(this, this.palette, blockPosition);
        } else {
            return this.getLevel().getBlockAt(blockPosition);
        }
    }

    @Override
    public ClientChunkSection getSectionAt(BlockPosition blockPosition) {
        return this.sections[ChunkSectionPosition.ofVoxel(blockPosition).getIndex()];
    }

    @Override
    public boolean isLoaded() {
        return this.isLoaded;
    }

    @Override
    public boolean load(boolean generate) {
        if(this.isLoaded())
            return false;

        if(generate) {
            Random r = new Random();
            for(int x = this.getPosition().getX(); x < (this.getPosition().getX() + 16); x++)
                for(int y = this.getPosition().getY(); y < (this.getPosition().getY() + 16); y++)
                    for(int z = this.getPosition().getZ(); z < (this.getPosition().getZ() + 16); z++)
                        this.getBlockAt(x, y, z).setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
        }

        Main.EVENT_BUS.submit(new ChunkLoadEvent(this));

        this.isLoaded = true;

        return true;
    }

    @Override
    public boolean unload(boolean save) {
        if(!this.isLoaded())
            return false;

        Main.EVENT_BUS.submit(new ChunkUnloadEvent(this));

        this.isLoaded = false;

        return true;
    }

    /**
    * Ticks this {@link ClientChunk}.
    */

    public void tick() {
        if(this.isLoaded())
            for(ClientChunkSection section : this.sections)
                section.tick();
    }

    @Override
    public String toString() {
        return "ClientChunk{" + "\"Position\": " + this.getPosition() + "}";
    }

    public static ClientChunk EMPTY = new ClientChunk(null, null) {

        @Override
        public BlockState getBlockAt(BlockPosition blockPosition) {
            return BlockState.EMPTY;
        }

        @Override
        public ChunkSection getSectionAt(int x, int y, int z) {
            return null;
        }

        @Override
        public boolean isLoaded() {
            return false;
        }

        @Override
        public boolean load(boolean generate) {
            return false;
        }

        @Override
        public boolean unload(boolean save) {
            return false;
        }

        @Override
        public void tick() {

        }
    };
}