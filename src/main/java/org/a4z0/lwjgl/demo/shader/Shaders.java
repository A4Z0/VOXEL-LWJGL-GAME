package org.a4z0.lwjgl.demo.shader;

import java.io.IOException;
import java.io.InputStream;

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;

/**
* Represents a {@link Shaders}.
*/

public class Shaders {

    public static ShaderProgram VOXEL_SHADER_PROGRAM;
    public static ShaderProgram OUTLINE_SHADER_PROGRAM;
    public static ShaderProgram TEXT_SHADER_PROGRAM;

    static {
        {
            VOXEL_SHADER_PROGRAM = new ShaderProgram();
            VOXEL_SHADER_PROGRAM.attribute(0, "vertex_position");
            VOXEL_SHADER_PROGRAM.attribute(1, "vertex_color");

            // Vertex
            Shader VOXEL_VERTEX_SHADER = new Shader(GL_VERTEX_SHADER);
            VOXEL_VERTEX_SHADER.source(getSource("assets/shader/glsl/world.vert"));
            VOXEL_VERTEX_SHADER.compile();

            // Fragment
            Shader VOXEL_FRAGMENT_SHADER = new Shader(GL_FRAGMENT_SHADER);
            VOXEL_FRAGMENT_SHADER.source(getSource("assets/shader/glsl/world.frag"));
            VOXEL_FRAGMENT_SHADER.compile();

            VOXEL_SHADER_PROGRAM.addShader(VOXEL_VERTEX_SHADER);
            VOXEL_SHADER_PROGRAM.addShader(VOXEL_FRAGMENT_SHADER);

            VOXEL_SHADER_PROGRAM.link();
        }
        {
            OUTLINE_SHADER_PROGRAM = new ShaderProgram();
            OUTLINE_SHADER_PROGRAM.attribute(0, "vertex_position");
            OUTLINE_SHADER_PROGRAM.attribute(1, "vertex_color");

            // Vertex
            Shader OUTLINE_VERTEX_SHADER = new Shader(GL_VERTEX_SHADER);
            OUTLINE_VERTEX_SHADER.source(getSource("assets/shader/glsl/outline.vert"));
            OUTLINE_VERTEX_SHADER.compile();

            // Fragment
            Shader OUTLINE_FRAGMENT_SHADER = new Shader(GL_FRAGMENT_SHADER);
            OUTLINE_FRAGMENT_SHADER.source(getSource("assets/shader/glsl/outline.frag"));
            OUTLINE_FRAGMENT_SHADER.compile();

            OUTLINE_SHADER_PROGRAM.addShader(OUTLINE_VERTEX_SHADER);
            OUTLINE_SHADER_PROGRAM.addShader(OUTLINE_FRAGMENT_SHADER);

            OUTLINE_SHADER_PROGRAM.link();
        }
        {
            TEXT_SHADER_PROGRAM = new ShaderProgram();
            TEXT_SHADER_PROGRAM.attribute(0, "vertex_position");
            TEXT_SHADER_PROGRAM.attribute(1, "vertex_texture_coordinates");

            // Vertex
            Shader TEXT_VERTEX_SHADER = new Shader(GL_VERTEX_SHADER);
            TEXT_VERTEX_SHADER.source(getSource("assets/shader/glsl/text.vert"));
            TEXT_VERTEX_SHADER.compile();

            // Fragment
            Shader TEXT_FRAGMENT_SHADER = new Shader(GL_FRAGMENT_SHADER);
            TEXT_FRAGMENT_SHADER.source(getSource("assets/shader/glsl/text.frag"));
            TEXT_FRAGMENT_SHADER.compile();

            TEXT_SHADER_PROGRAM.addShader(TEXT_VERTEX_SHADER);
            TEXT_SHADER_PROGRAM.addShader(TEXT_FRAGMENT_SHADER);

            TEXT_SHADER_PROGRAM.link();
        }
    }

    /**
    * Initializes this {@link Shaders}.
    */

    public static void init() {

    }

    /**
    * ...
    *
    * @param uri ...
    *
    * @return ...
    */

    private static String getSource(String uri) {
        return getSource(Shaders.class.getClassLoader().getResourceAsStream(uri));
    }

    /**
    * ...
    *
    * @param stream ...
    *
    * @return a {@link String}.
    */

    private static String getSource(InputStream stream) {
        try(InputStream i = stream) {
            return new String(i.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}