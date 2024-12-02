package org.a4z0.lwjgl.demo.font;

public final class Fonts {

    public static GLFont MINECRAFT;

    public static void init() {
        System.out.println("[FontLoader]: -> \"font/minecraft.ttf\".");
        MINECRAFT = GLFont.create("./assets/font/minecraft.ttf", 16);
    }
}