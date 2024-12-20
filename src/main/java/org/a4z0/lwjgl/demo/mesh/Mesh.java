package org.a4z0.lwjgl.demo.mesh;

import org.a4z0.lwjgl.demo.chunk.Chunk;
import org.a4z0.lwjgl.demo.voxel.VoxelPosition;
import org.a4z0.lwjgl.demo.face.FaceGenerator;
import org.a4z0.lwjgl.demo.level.Direction;
import org.a4z0.lwjgl.demo.layer.ChunkLayerPosition;
import org.a4z0.lwjgl.demo.util.ByteBuf;
import org.a4z0.lwjgl.demo.voxel.IVoxel;

public class Mesh {

    /**
    * ...
    *
    * @param buffer ...
    * @param chunk ...
    * @param position ...
    */

    public static void build(ByteBuf buffer, Chunk chunk, ChunkLayerPosition position) {
        for(int x = position.getX(); x < (position.getX() + 16); x++) {
            for(int y = position.getY(); y < (position.getY() + 16); y++) {
                for(int z = position.getZ(); z < (position.getZ() + 16); z++) {
                    IVoxel Voxel = chunk.getLevel().getVoxelAt(x, y, z);

                    if(Voxel.getColor() == 0)
                        continue;

                    VoxelPosition Position = Voxel.getPosition();

                    for(Direction dir : Direction.values()) {
                        switch (dir) {
                            case NORTH -> {
                                if(Voxel.getNorth().getColor() != 0)
                                    break;

                                FaceGenerator.create(buffer, Voxel.getColor()).build(Direction.NORTH, Position.getX(), Position.getY(), Position.getZ());
                            }
                            case SOUTH -> {
                                if(Voxel.getSouth().getColor() != 0)
                                    break;

                                FaceGenerator.create(buffer, Voxel.getColor()).build(Direction.SOUTH, Position.getX(), Position.getY(), Position.getZ());
                            }
                            case EAST -> {
                                if(Voxel.getEast().getColor() != 0)
                                    break;

                                FaceGenerator.create(buffer, Voxel.getColor()).build(Direction.EAST, Position.getX(), Position.getY(), Position.getZ());
                            }
                            case WEST -> {
                                if(Voxel.getWest().getColor() != 0)
                                    break;

                                FaceGenerator.create(buffer, Voxel.getColor()).build(Direction.WEST, Position.getX(), Position.getY(), Position.getZ());
                            }
                            case TOP -> {
                                if(Voxel.getTop().getColor() != 0)
                                    break;

                                FaceGenerator.create(buffer, Voxel.getColor()).build(Direction.TOP, Position.getX(), Position.getY(), Position.getZ());
                            }
                            case BOTTOM -> {
                                if(Voxel.getBottom().getColor() != 0)
                                    break;

                                FaceGenerator.create(buffer, Voxel.getColor()).build(Direction.BOTTOM, Position.getX(), Position.getY(), Position.getZ());
                            }
                        }
                    }
                }
            }
        }
    }
}