package org.a4z0.lwjgl.game.block;

import org.a4z0.lwjgl.api.color.Color;
import org.a4z0.lwjgl.api.block.BlockState;
import org.a4z0.lwjgl.api.level.chunk.Chunk;
import org.a4z0.lwjgl.api.position.BlockPosition;

public class BlockData implements BlockState {

    protected final Chunk chunk;
    protected final BlockPosition position;
    protected final int[] palette;

    /**
    * Construct a {@link BlockData}.
    *
    * @param chunk Chunk.
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    public BlockData(Chunk chunk, int[] palette, int x, int y, int z) {
        this(chunk, palette, new BlockPosition(x, y, z));
    }

    /**
    * Construct a {@link BlockData}.
    *
    * @param chunk Chunk.
    * @param position Position.
    */

    public BlockData(Chunk chunk, int[] palette, BlockPosition position) {
        this.chunk = chunk;
        this.palette = palette;
        this.position = position;
    }

    @Override
    public Chunk getChunk() {
        return this.chunk;
    }

    @Override
    public BlockPosition getPosition() {
        return this.position;
    }

    @Override
    public Color getColor() {
        return Color.argb(this.palette[this.getPosition().getIndex()]);
    }

    @Override
    public BlockData setColor(Color color) {
        this.palette[this.getPosition().getIndex()] = color.asARGB();

        return this;
    }

    @Override
    public void update() {
        this.getChunk().getSectionAt(this.getPosition()).update();
    }

    @Override
    public String toString() {
        return "BlockData{" + "\"Position\":" + this.getPosition() + ", " + "\"Color\":" + this.getColor() + "}";
    }
}