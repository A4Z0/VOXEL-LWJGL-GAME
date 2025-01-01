package org.a4z0.lwjgl.game.resource.shader.program;

import org.a4z0.lwjgl.game.registry.Registries;
import org.a4z0.lwjgl.core.resource.ResourceKey;
import org.a4z0.lwjgl.game.resource.shader.Shader;
import org.joml.Matrix4dc;
import org.joml.Matrix4fc;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;
import java.util.List;

import static org.lwjgl.opengl.GL20.*;

public final class ShaderProgram {

    private int glShaderProgram;

    /**
    * Construct a {@link ShaderProgram}.
    *
    * @param shaders Shaders.
    */

    public ShaderProgram(List<ResourceKey<Shader>> shaders) {
        this.glShaderProgram = glCreateProgram();

        for(ResourceKey<Shader> shader : shaders)
            glAttachShader(this.getID(), Registries.SHADER.getOrThrow(shader).getID());

        glLinkProgram(this.getID());

        try {
            if (glGetProgrami(this.getID(), GL_LINK_STATUS) == 0)
                throw new ShaderProgramException("Unable to link this Shader Program. \n" + glGetProgramInfoLog(this.getID()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
    * @return this {@link ShaderProgram} ID.
    */

    public int getID() {
        return this.glShaderProgram;
    }

    /**
    * Binds this {@link ShaderProgram} to the current context.
    */

    public void bind() {
        glUseProgram(this.glShaderProgram);
    }

    /**
    * ...
    *
    * @param attr ...
    * @param var ...
    */

    public ShaderProgram attribute(int attr, String var) {
        glBindAttribLocation(this.glShaderProgram, attr, var);

        return this;
    }

    /**
    * Gets the name of the given Uniform.
    *
    * @param glName Name of the Uniform to be located.
    *
    * @return the location of the Uniform as an {@link Integer}.
    */

    public int getUniformLocation(String glName) {
        return glGetUniformLocation(this.glShaderProgram, glName);
    }

    /**
    * ...
    *
    * @param glName ...
    * @param glMatrix ..
    */

    public void setUniform4fv(String glName, Matrix4fc glMatrix) {
        try(MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer x = stack.mallocFloat(16);
            glMatrix.get(x);

            glUniformMatrix4fv(this.getUniformLocation(glName), false, x);
        }
    }

    /**
    * ...
    *
    * @param glName ...
    * @param glMatrix ...
    */

    public void setUniform4fv(String glName, Matrix4dc glMatrix) {
        try(MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer x = stack.mallocFloat(16);
            glMatrix.get(x);

            glUniformMatrix4fv(this.getUniformLocation(glName), false, x);
        }
    }

    /**
    * ...
    *
    * @param glName ...
    * @param glFloatArray ...
    */

    public void setUniform3fv(String glName, float[] glFloatArray) {
        glUniform3fv(this.getUniformLocation(glName), glFloatArray);
    }

    /**
    * ...
    *
    * @param glName ...
    * @param glIntArray ...
    */

    public void setUniform3iv(String glName, int[] glIntArray) {
        glUniform3iv(this.getUniformLocation(glName), glIntArray);
    }

    /**
    * ...
    *
    * @param glName ...
    * @param glX ...
    * @param glY ...
    */

    public void setUniform2f(String glName, float glX, float glY) {
        glUniform2f(this.getUniformLocation(glName), glX, glY);
    }

    /**
    * ...
    *
    * @param glName ...
    * @param glX ...
    * @param glY ...
    * @param glZ ...
    */

    public void setUniform3f(String glName, float glX, float glY, float glZ) {
        glUniform3f(this.getUniformLocation(glName), glX, glY, glZ);
    }

    /**
    * ...
    *
    * @param glName ...
    * @param glX ...
    * @param glY ...
    * @param glZ ...
    * @param glW ...
    */

    public void setUniform4f(String glName, float glX, float glY, float glZ, float glW) {
        glUniform4f(this.getUniformLocation(glName), glX, glY, glZ, glW);
    }

    /**
    * ...
    *
    * @param glName ...
    * @param glFloat ...
    */

    public void setUniform1f(String glName, float glFloat) {
        glUniform1f(this.getUniformLocation(glName), glFloat);
    }

    /**
    * Unbinds this {@link ShaderProgram} from the current context.
    */

    public void unbind() {
        glUseProgram(0);
    }

    /**
    * Deletes this {@link ShaderProgram}.
    */

    public void delete() {
        glDeleteProgram(this.glShaderProgram);

        this.glShaderProgram = 0;
    }
}