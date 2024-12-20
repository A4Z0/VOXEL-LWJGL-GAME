package org.a4z0.lwjgl.demo.resource.shader;

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL32.GL_GEOMETRY_SHADER;
import static org.lwjgl.opengl.GL40.GL_TESS_CONTROL_SHADER;
import static org.lwjgl.opengl.GL40.GL_TESS_EVALUATION_SHADER;
import static org.lwjgl.opengl.GL43.GL_COMPUTE_SHADER;

public enum ShaderType {
    VERTEX(GL_VERTEX_SHADER, "vert"),
    FRAGMENT(GL_FRAGMENT_SHADER, "frag"),
    GEOMETRY(GL_GEOMETRY_SHADER, "geom"),
    TESSELLATION_CONTROL(GL_TESS_CONTROL_SHADER, "tesc"),
    TESSELLATION_EVALUATION(GL_TESS_EVALUATION_SHADER, "tese"),
    COMPUTE(GL_COMPUTE_SHADER, "comp"),
    MODULE(GL_VERTEX_SHADER, "glsl");

    private final int shaderCode;
    private final String extension;

    /**
    * @param shaderCode GL Shader Code.
    */

    ShaderType(int shaderCode, String extension) {
        this.shaderCode = shaderCode;
        this.extension = extension;
    }

    /**
    * @return a GL Shader Code.
    */

    public int getShader() {
        return this.shaderCode;
    }

    /**
    * @return the File Extension.
    */

    public String getExtension() {
        return this.extension;
    }

    /**
    * ...
    *
    * @param name ...
    *
    * @return ...
    */

    public static ShaderType of(String name) {
        return switch (name) {
            case "vertex" ->
                VERTEX;
            case "fragment" ->
                FRAGMENT;
            case "geometry" ->
                GEOMETRY;
            case "tessellation_control" ->
                TESSELLATION_CONTROL;
            case "tessellation_evaluation" ->
                TESSELLATION_EVALUATION;
            case "compute" ->
                COMPUTE;
            case "module" ->
                MODULE;
            default ->
                throw new IllegalArgumentException("...");
        };
    }
}