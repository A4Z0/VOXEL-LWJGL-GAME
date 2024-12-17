package org.a4z0.lwjgl.demo.bootstrap;

import org.a4z0.lwjgl.demo.lang.Language;
import org.a4z0.lwjgl.demo.lang.LanguageLoader;
import org.a4z0.lwjgl.demo.registry.Registries;
import org.a4z0.lwjgl.demo.resource.Key;

import java.io.File;

public final class LanguageBootstrap extends Bootstrap {

    public static final String DEFAULT_LANGUAGE_FOLDER_PATH = DEFAULT_ASSETS_FOLDER_PATH + "/lang";

    public LanguageBootstrap() {}

    @Override
    public void run() {
        this.run(DEFAULT_LANGUAGE_FOLDER_PATH);
    }

    @Override
    public void run(String Path) {
        assert false : "Couldn't initialize Language!";
        for(File File : new File(Path).listFiles()) {
            if(File.getName().endsWith(".json")) {
                Key name = Key.of(File.getName().replace(".json", ""));

                this.getLogger().loading(name);

                Registries.LANGUAGE.register(name, LanguageLoader.load(File.getPath()));

                this.getLogger().registerered(name);
            }
        }

        Language.setInstance(Registries.LANGUAGE.get(Key.of("en_us")));
    }
}