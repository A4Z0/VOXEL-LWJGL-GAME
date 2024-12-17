package org.a4z0.lwjgl.demo.resource.shader;

import static org.lwjgl.opengl.GL20.*;

public final class Shader {

    private int glShader;

    /**
    * Construct a {@link Shader}.
    *
    * @param shaderType Type.
    * @param shaderSource Source.
    */

    Shader(ShaderType shaderType, String shaderSource) throws ShaderException {
        this.glShader = glCreateShader(shaderType.getShader());

        glShaderSource(this.getID(), shaderSource);
        glCompileShader(this.getID());

        if(glGetShaderi(this.getID(), GL_COMPILE_STATUS) == 0)
            throw new ShaderException("Unable to compile Shader." + "\n" + glGetShaderInfoLog(this.getID()));
    }

    /**
    * @return this {@link Shader} ID.
    */

    public int getID() {
        return this.glShader;
    }

    /**
    * Deletes this {@link Shader}.
    */

    public void delete() {
        glDeleteShader(this.glShader);

        this.glShader = 0;
    }
}