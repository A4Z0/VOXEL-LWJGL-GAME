package org.a4z0.lwjgl.game.colision;

import org.a4z0.lwjgl.api.level.block.BlockState;
import org.a4z0.lwjgl.api.location.Location3fc;
import org.a4z0.lwjgl.Key;
import org.a4z0.lwjgl.math.aabb.AABBf;
import org.a4z0.lwjgl.math.aabb.AABBfc;
import org.a4z0.lwjgl.game.Game;
import org.a4z0.lwjgl.game.extra.test.AABBRenderer;
import org.a4z0.lwjgl.game.registry.Registries;

public class Collision {

    public static final AABBf DEFAULT_BLOCK_BODY = new AABBf(-0.5f, -0.5f, -0.5f, 0.5f, 0.5f, 0.5f);

    public static boolean North(AABBfc AABBf) {
        return Collide(AABBf.clone().subtract(0, 0, 0.1f));
    }

    public static boolean South(AABBfc AABBf) {
        return false;
    }

    public static boolean East(AABBfc AABBf) {
        return false;
    }

    public static boolean West(AABBfc AABBf) {
        return false;
    }

    public static boolean Top(AABBfc AABBf) {
        return false;
    }

    public static boolean Bottom(AABBfc AABBf) {
        return Collide(AABBf.clone());
    }

    public static boolean Collide(AABBfc AABBf) {
        int LX = (int) Math.floor(AABBf.getLowerX());
        int LY = (int) Math.floor(AABBf.getLowerY());
        int LZ = (int) Math.floor(AABBf.getLowerZ());
        int UX = (int) Math.ceil(AABBf.getUpperX());
        int UY = (int) Math.ceil(AABBf.getUpperY());
        int UZ = (int) Math.ceil(AABBf.getUpperZ());

        for(int x = LX; x <= UX; x++) {
            for(int y = LY; y <= UY; y++) {
                for(int z = LZ; z <= UZ; z++) {
                    BlockState Block = Game.LEVEL.getBlockAt(x, y, z);

                    if(Block.getColor().isEmpty())
                        continue;

                    if(LEGACY_BLOCK_COLLIDER(Block).intersects(AABBf)) {
                        {
                            Registries.SHADER_PROGRAM.getOrThrow(Key.of("outline")).bind();
                            Registries.SHADER_PROGRAM.getOrThrow(Key.of("outline")).setUniform4fv("camera_projection_view", Game.PLAYER.getCamera().getProjectionView());

                            AABBRenderer.draw(LEGACY_BLOCK_COLLIDER(Block), 0, 0, 0, 255, 0, 0);

                            AABBRenderer.flush();
                            Registries.SHADER_PROGRAM.getOrThrow(Key.of("outline")).unbind();
                        }

                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static AABBfc LEGACY_BLOCK_COLLIDER(BlockState Block) {
        return DEFAULT_BLOCK_BODY.clone().add(Block.getPosition().getX(), Block.getPosition().getY(), Block.getPosition().getZ());
    }

    public static BlockState LEGACY_LOC_BLOCK(Location3fc loc) {
        return Game.LEVEL.getBlockAt((int) Math.floor(loc.getX()), (int) Math.floor(loc.getY()), (int) Math.floor(loc.getZ()));
    }
}