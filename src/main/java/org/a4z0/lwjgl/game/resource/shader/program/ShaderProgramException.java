package org.a4z0.lwjgl.game.resource.shader.program;

public final class ShaderProgramException extends Exception {

    /**
    * Construct a {@link ShaderProgramException}.
    *
    * @param message Message.
    */

    public ShaderProgramException(String message) {
        super(message);
    }

    /**
    * Construct a {@link ShaderProgramException}.
    *
    * @param message Message.
    * @param cause Cause.
    */

    public ShaderProgramException(String message, Throwable cause) {
        super(message, cause);
    }
}
