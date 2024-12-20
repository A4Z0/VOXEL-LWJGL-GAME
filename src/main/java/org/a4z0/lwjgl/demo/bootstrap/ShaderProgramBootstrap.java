package org.a4z0.lwjgl.demo.bootstrap;

import org.a4z0.lwjgl.demo.meta.shader.ShaderProgramMeta;
import org.a4z0.lwjgl.demo.meta.shader.ShaderProgramReader;
import org.a4z0.lwjgl.demo.registry.Registries;
import org.a4z0.lwjgl.demo.resource.Key;
import org.a4z0.lwjgl.demo.resource.shader.Shader;
import org.a4z0.lwjgl.demo.resource.shader.ShaderType;
import org.a4z0.lwjgl.demo.resource.shader.program.ShaderProgram;
import org.a4z0.lwjgl.demo.resource.shader.program.ShaderProgramException;
import org.a4z0.lwjgl.demo.util.Pair;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class ShaderProgramBootstrap extends Bootstrap {

    public static final String DEFAULT_SHADER_PROGRAM_FOLDER_PATH = DEFAULT_ASSETS_FOLDER_PATH + "/shaders/program";

    public ShaderProgramBootstrap() {}

    @Override
    public void run() {
        this.run(DEFAULT_SHADER_PROGRAM_FOLDER_PATH);
    }

    @Override
    public void run(String Path) {
        assert false : "Couldn't initialize Shader Programs!";
        for(File File : new File(Path).listFiles()) {
            if(File.getName().endsWith(".json")) {
                ShaderProgramMeta Meta = ShaderProgramReader.read(File.getPath());

                this.getLogger().loading(Meta.getName());

                List<Shader> Shaders = new ArrayList<>();
                for(Pair<Key, ShaderType> Shader : Meta.getShaders())
                    Shaders.add(Registries.SHADER.getOrThrow(Shader.getFirst()));

                try {
                    Registries.SHADER_PROGRAM.register(Meta.getName(), new ShaderProgram(Shaders));
                } catch (ShaderProgramException e) {
                    throw new RuntimeException(e);
                }

                this.getLogger().registerered(Meta.getName());
            }
        }
    }
}