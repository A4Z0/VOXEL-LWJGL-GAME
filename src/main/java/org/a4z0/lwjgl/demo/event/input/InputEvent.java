package org.a4z0.lwjgl.demo.event.input;

import org.a4z0.api.event.Event;
import org.a4z0.lwjgl.demo.input.InputAction;
import org.a4z0.lwjgl.demo.input.InputHandler;

public abstract class InputEvent extends Event {

    protected final int code;
    protected final InputAction action;

    /**
    * Construct a {@link InputEvent}.
    */

    public InputEvent(final int code, final InputAction action) {
        this.code = code;
        this.action = action;
    }

    /**
    * @return the Code.
    */

    public int getCode() {
        return this.code;
    }

    /**
    * @return the Action.
    */

    public InputAction getAction() {
        return this.action;
    }

    /**
    * @return the Action of a Key.
    */

    public final InputAction getKey(int GLFW_KEY) {
        return InputHandler.getKey(GLFW_KEY);
    }

    /**
    * @return the Action of a Button.
    */

    public final InputAction getButton(int GLFW_BUTTON) {
        return InputHandler.getButton(GLFW_BUTTON);
    }
}