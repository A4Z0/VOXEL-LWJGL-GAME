package org.a4z0.lwjgl.game.resource.shader;

public final class ShaderException extends Exception {

    /**
    * Construct a {@link ShaderException}.
    *
    * @param message Message.
    */

    public ShaderException(String message) {
        super(message);
    }

    /**
    * Construct a {@link ShaderException}.
    *
    * @param message Message.
    * @param cause Cause.
    */

    public ShaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
