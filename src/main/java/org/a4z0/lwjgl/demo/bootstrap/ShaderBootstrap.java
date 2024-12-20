package org.a4z0.lwjgl.demo.bootstrap;

import org.a4z0.lwjgl.demo.meta.shader.ShaderProgramMeta;
import org.a4z0.lwjgl.demo.meta.shader.ShaderProgramReader;
import org.a4z0.lwjgl.demo.registry.Registries;
import org.a4z0.lwjgl.demo.resource.Key;
import org.a4z0.lwjgl.demo.resource.shader.ShaderException;
import org.a4z0.lwjgl.demo.resource.shader.ShaderLoader;
import org.a4z0.lwjgl.demo.resource.shader.ShaderType;
import org.a4z0.lwjgl.demo.util.Pair;

import java.io.File;

public final class ShaderBootstrap extends Bootstrap {

    public static final String DEFAULT_SHADER_FOLDER_PATH = DEFAULT_ASSETS_FOLDER_PATH + "/shaders/program";

    public ShaderBootstrap() {}

    @Override
    public void run() {
        this.run(DEFAULT_SHADER_FOLDER_PATH);
    }

    @Override
    public void run(String Path) {
        assert false : "Couldn't initialize Shaders!";
        for (File File : new File(Path).listFiles()) {
            if(File.getName().endsWith(".json")) {
                ShaderProgramMeta Meta = ShaderProgramReader.read(File.getPath());

                for (Pair<Key, ShaderType> Shader : Meta.getShaders()) {
                    try {
                        Registries.SHADER.register(Shader.getFirst(), ShaderLoader.load(Path + "/../core/" + Shader.getFirst().getPath().replace("vert", "").replace("frag", "") + "." + Shader.getSecond().getExtension(), Shader.getSecond()));
                    } catch (ShaderException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}