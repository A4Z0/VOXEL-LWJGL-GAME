package org.a4z0.lwjgl.demo.resource.font;

import org.a4z0.lwjgl.demo.registry.Key;

public final class Fonts {

    public static final Key MINECRAFT_TRUE_TYPE_FONT_KEY = Key.of("font", "minecraft");
    public static final Key FIRA_CODE_TRUE_TYPE_FONT_KEY = Key.of("font", "fira_code");

    public static Font MINECRAFT_TRUE_TYPE_FONT;
    public static Font FIRA_CODE_TRUE_TYPE_FONT;

    Fonts() {}

    public static void init() {
        System.out.println("[FontLoader]: -> \"font/minecraft.ttf\".");
        MINECRAFT_TRUE_TYPE_FONT = GLFontLoader.load("E:\\Antigo\\Wrapped\\IdeaProjects\\voxel-lwjgl\\src\\main\\resources\\assets\\font\\minecraft.ttf");
        System.out.println("[FontLoader]: -> \"font/fira_code.ttf\".");
        FIRA_CODE_TRUE_TYPE_FONT = GLFontLoader.load("E:\\Antigo\\Wrapped\\IdeaProjects\\voxel-lwjgl\\src\\main\\resources\\assets\\font\\fira_code.ttf");
    }
}