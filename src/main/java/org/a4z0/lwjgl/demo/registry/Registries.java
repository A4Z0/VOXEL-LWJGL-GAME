package org.a4z0.lwjgl.demo.registry;

import org.a4z0.lwjgl.demo.resource.font.Font;
import org.a4z0.lwjgl.demo.resource.texture.Texture;
import org.a4z0.lwjgl.demo.resource.shader.Shader;
import org.a4z0.lwjgl.demo.resource.shader.ShaderProgram;
import org.a4z0.lwjgl.demo.ui.UI;

public final class Registries {

    public static Registry<Shader>          SHADER_REGISTRY;
    public static Registry<ShaderProgram>   SHADER_PROGRAM_REGISTRY;
    public static Registry<Texture>         TEXTURE_REGISTRY;
    public static Registry<Font>            FONT_REGISTRY;
    public static Registry<UI>          WIDGET_REGISTRY;

    Registries() {}

    public static void init() {
        System.out.println("[Registries]: Initializing Shader.");
        SHADER_REGISTRY = new Registry<>();
        System.out.println("[Registries]: Initializing Shader Program.");
        SHADER_PROGRAM_REGISTRY = new Registry<>();
        System.out.println("[Registries]: Initializing Texture.");
        TEXTURE_REGISTRY = new Registry<>();
        System.out.println("[Registries]: Initializing Font.");
        FONT_REGISTRY = new Registry<>();
        System.out.println("[Registries]: Initializing Widget.");
        WIDGET_REGISTRY = new Registry<>();
    }
}