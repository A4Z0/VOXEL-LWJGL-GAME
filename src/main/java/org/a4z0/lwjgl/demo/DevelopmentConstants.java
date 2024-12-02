package org.a4z0.lwjgl.demo;

import org.a4z0.lwjgl.demo.camera.Camera3fa;
import org.a4z0.lwjgl.demo.entity.EntityPlayer;
import org.a4z0.lwjgl.demo.level.Level;
import org.a4z0.lwjgl.demo.server.Server;
import org.a4z0.lwjgl.demo.server.ServerWaterResistant;

import java.util.UUID;

public final class DevelopmentConstants {

    public static Server DEVELOPMENT_SERVER;
    public static Level DEVELOPMENT_LEVEL;
    public static Camera3fa DEVELOPMENT_CAMERA;
    public static EntityPlayer DEVELOPMENT_PLAYER;

    public static void init() {
        DEVELOPMENT_SERVER = new ServerWaterResistant();
        DEVELOPMENT_LEVEL = new Level(DEVELOPMENT_SERVER, UUID.randomUUID(), "Development Level", 0);
        DEVELOPMENT_CAMERA = new Camera3fa();
        DEVELOPMENT_PLAYER = new EntityPlayer("A4Z0", DEVELOPMENT_LEVEL, 0, 16, 0);
        DEVELOPMENT_SERVER.getPlayers().add(DEVELOPMENT_PLAYER);

        DEVELOPMENT_LEVEL.getChunkAt(0, 0, 0).load(true);
    }
}