package org.a4z0.lwjgl.demo.render;

import org.a4z0.api.Direction;
import org.a4z0.api.color.Color;
import org.a4z0.api.level.block.BlockState;
import org.a4z0.api.level.chunk.section.ChunkSection;
import org.a4z0.math.vector.Vector3f;
import org.a4z0.lwjgl.demo.buffer.ByteBuf;

public class Mesh {
    
    @Deprecated
    Mesh() {}
    
    public static void create(ByteBuf Buffer, ChunkSection Section) {
        for(int x = Section.getPosition().getX(); x < (Section.getPosition().getX() + ChunkSection.SECTION_SIZE_X); x++) {
            for(int y = Section.getPosition().getY(); y < (Section.getPosition().getY() + ChunkSection.SECTION_SIZE_Y); y++) {
                for(int z = Section.getPosition().getZ(); z < (Section.getPosition().getZ() + ChunkSection.SECTION_SIZE_Z); z++) {
                    BlockState Block = Section.getBlockAt(x, y, z);

                    if(Block.getColor().isEmpty())
                        continue;

                    if(Block.getNorth().getColor().isEmpty())
                        generate(Buffer, Direction.NORTH, Block.getPosition().getRelativeX(), Block.getPosition().getRelativeY(), Block.getPosition().getRelativeZ(), Block.getColor());
                    if(Block.getSouth().getColor().isEmpty())
                        generate(Buffer, Direction.SOUTH, Block.getPosition().getRelativeX(), Block.getPosition().getRelativeY(), Block.getPosition().getRelativeZ(), Block.getColor());
                    if(Block.getEast().getColor().isEmpty())
                        generate(Buffer, Direction.EAST, Block.getPosition().getRelativeX(), Block.getPosition().getRelativeY(), Block.getPosition().getRelativeZ(), Block.getColor());
                    if(Block.getWest().getColor().isEmpty())
                        generate(Buffer, Direction.WEST, Block.getPosition().getRelativeX(), Block.getPosition().getRelativeY(), Block.getPosition().getRelativeZ(), Block.getColor());
                    if(Block.getTop().getColor().isEmpty())
                        generate(Buffer, Direction.TOP, Block.getPosition().getRelativeX(), Block.getPosition().getRelativeY(), Block.getPosition().getRelativeZ(), Block.getColor());
                    if(Block.getBottom().getColor().isEmpty())
                        generate(Buffer, Direction.BOTTOM, Block.getPosition().getRelativeX(), Block.getPosition().getRelativeY(), Block.getPosition().getRelativeZ(), Block.getColor());
                }
            }
        }
    }

    private static void generate(ByteBuf b, Direction direction, int x, int y, int z, Color color) {
        for(Vector3f vertex : RenderableChunkSection.FACES[direction.ordinal()]) {
            b.put(vertex.getX() + x).put(vertex.getY() + y).put(vertex.getZ() + z).put(color.asARGB()).put(0);
        }
    }
}