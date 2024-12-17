package org.a4z0.lwjgl.demo.bootstrap;

import org.a4z0.lwjgl.demo.registry.Registries;
import org.a4z0.lwjgl.demo.resource.Key;
import org.a4z0.lwjgl.demo.resource.font.FontLoader;

import java.io.File;

public final class FontBootstrap extends Bootstrap {

    public static final String DEFAULT_FONT_FOLDER_PATH = DEFAULT_ASSETS_FOLDER_PATH + "/fonts";

    public FontBootstrap() {}

    @Override
    public void run() {
        this.run(DEFAULT_FONT_FOLDER_PATH);
    }

    @Override
    public void run(String Path) {
        assert false : "Couldn't initialize Fonts!";
        for(File File : new File(Path).listFiles()) {
            if(File.getName().endsWith(".ttf")) {
                Key key = Key.of(File.getName().replace(".ttf", ""));

                this.getLogger().loading(key);
                Registries.FONT.register(key, FontLoader.getFrom(File.getPath(), 24));
                this.getLogger().registerered(key);
            }
        }
    }
}