package org.a4z0.lwjgl.demo.resource.shader;

import static org.lwjgl.opengl.GL20.*;

public final class Shader {

    private final int glShader;

    /**
    * Construct a {@link Shader}.
    *
    * @param glShaderType Shader Type that will be set.
    */

    public Shader(int glShaderType) {
        this.glShader = glCreateShader(glShaderType);
    }

    /**
    * @return this {@link Shader} ID.
    */

    public int getID() {
        return this.glShader;
    }

    /**
    * Sets the source code of this {@link Shader}.
    *
    * @param glSource Source code that will be set.
    */

    public Shader source(String glSource) {
        glShaderSource(this.glShader, glSource);

        return this;
    }

    /**
    * Compile this {@link Shader}.
    */

    public Shader compile() {
        glCompileShader(this.glShader);

        if(glGetShaderi(this.glShader, GL_COMPILE_STATUS) == 0)
            throw new RuntimeException("Unable to compile Shader." + "\n" + glGetShaderInfoLog(this.glShader));

        return this;
    }

    /**
    * Deletes this {@link Shader}.
    */

    public void delete() {
        glDeleteShader(this.glShader);
    }
}