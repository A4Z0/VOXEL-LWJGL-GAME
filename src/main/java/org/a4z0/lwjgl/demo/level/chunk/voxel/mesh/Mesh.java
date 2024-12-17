package org.a4z0.lwjgl.demo.level.chunk.voxel.mesh;

import org.a4z0.lwjgl.demo.level.chunk.Chunk;
import org.a4z0.lwjgl.demo.level.chunk.voxel.face.FaceGenerator;
import org.a4z0.lwjgl.demo.level.Direction;
import org.a4z0.lwjgl.demo.util.ByteBuf;
import org.a4z0.lwjgl.demo.level.chunk.voxel.Voxel;

public class Mesh {

    /**
    * ...
    *
    * @param buffer ...
    * @param chunk ...
    * @param x1 ...
    * @param y1 ...
    * @param z1 ...
    * @param x2 ...
    * @param y2 ...
    * @param z2 ...
    */

    public static void build(ByteBuf buffer, Chunk chunk, int x1, int y1, int z1, int x2, int y2, int z2) {
        for(int x = x1; x <= x2; x++) {
            for(int y = y1; y <= y2; y++) {
                for(int z = z1; z <= z2; z++) {
                    Voxel Voxel = chunk.getVoxelAt(x, y, z);

                    if(Voxel.getColor() == 0)
                        continue;

                    for(Direction dir : Direction.values()) {
                        switch (dir) {
                            case NORTH -> {
                                if(Voxel.getNorth().getColor() != 0)
                                    break;

                                FaceGenerator.create(buffer, Voxel.getColor()).build(Direction.NORTH, Voxel.getPosition().getX(), Voxel.getPosition().getY(), Voxel.getPosition().getZ());
                            }
                            case SOUTH -> {
                                if(Voxel.getSouth().getColor() != 0)
                                    break;

                                FaceGenerator.create(buffer, Voxel.getColor()).build(Direction.SOUTH, Voxel.getPosition().getX(), Voxel.getPosition().getY(), Voxel.getPosition().getZ());
                            }
                            case EAST -> {
                                if(Voxel.getEast().getColor() != 0)
                                    break;

                                FaceGenerator.create(buffer, Voxel.getColor()).build(Direction.EAST, Voxel.getPosition().getX(), Voxel.getPosition().getY(), Voxel.getPosition().getZ());
                            }
                            case WEST -> {
                                if(Voxel.getWest().getColor() != 0)
                                    break;

                                FaceGenerator.create(buffer, Voxel.getColor()).build(Direction.WEST, Voxel.getPosition().getX(), Voxel.getPosition().getY(), Voxel.getPosition().getZ());
                            }
                            case TOP -> {
                                if(Voxel.getTop().getColor() != 0)
                                    break;

                                FaceGenerator.create(buffer, Voxel.getColor()).build(Direction.TOP, Voxel.getPosition().getX(), Voxel.getPosition().getY(), Voxel.getPosition().getZ());
                            }
                            case BOTTOM -> {
                                if(Voxel.getBottom().getColor() != 0)
                                    break;

                                FaceGenerator.create(buffer, Voxel.getColor()).build(Direction.BOTTOM, Voxel.getPosition().getX(), Voxel.getPosition().getY(), Voxel.getPosition().getZ());
                            }
                        }
                    }
                }
            }
        }
    }
}