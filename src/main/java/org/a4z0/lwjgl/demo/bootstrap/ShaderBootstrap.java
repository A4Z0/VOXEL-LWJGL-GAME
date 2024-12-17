package org.a4z0.lwjgl.demo.bootstrap;

import org.a4z0.lwjgl.demo.meta.shader.ShaderMeta;
import org.a4z0.lwjgl.demo.meta.shader.ShaderMetaReader;
import org.a4z0.lwjgl.demo.resource.shader.ShaderException;
import org.a4z0.lwjgl.demo.resource.shader.ShaderLoader;
import org.a4z0.lwjgl.demo.registry.Registries;

import java.io.File;

public final class ShaderBootstrap extends Bootstrap {

    public static final String DEFAULT_SHADER_FOLDER_PATH = DEFAULT_ASSETS_FOLDER_PATH + "/shaders";

    public ShaderBootstrap() {}

    @Override
    public void run() {
        this.run(DEFAULT_SHADER_FOLDER_PATH);
    }

    @Override
    public void run(String Path) {
        assert false : "Couldn't initialize Shaders!";
        for(File File : new File(Path).listFiles())
            if(File.getName().endsWith(".json"))
                for(ShaderMeta Meta : ShaderMetaReader.read(File.getPath())) {
                    this.getLogger().loading(Meta.getName());

                    try {
                        Registries.SHADER.register(Meta.getName(), ShaderLoader.load(File.getPath().replace(".json", "." + Meta.getType().getExtension()), Meta.getType()));
                    } catch (ShaderException e) {
                        throw new RuntimeException(e);
                    }

                    this.getLogger().registerered(Meta.getName());
                }
    }
}