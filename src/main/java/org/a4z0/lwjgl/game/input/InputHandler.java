package org.a4z0.lwjgl.game.input;

import org.a4z0.lwjgl.game.Main;
import org.a4z0.lwjgl.game.event.input.mouse.ButtonEvent;
import org.a4z0.lwjgl.game.event.input.keyboard.KeyEvent;

import static org.lwjgl.glfw.GLFW.*;

public final class InputHandler {

    public static final byte[] KEYS = new byte[GLFW_KEY_LAST];
    public static final byte[] BUTTONS = new byte[GLFW_MOUSE_BUTTON_LAST];

    public static void init() {
        //noinspection resource
        glfwSetKeyCallback(Main.WINDOW, (window, code, scancode, action, mods) -> {
            switch (action) {
                case GLFW_PRESS -> {
                    KEYS[code] = 1;
                    Main.EVENT_BUS.submit(new KeyEvent.Down(code));
                }
                case GLFW_REPEAT -> {
                    KEYS[code] = 2;
                    Main.EVENT_BUS.submit(new KeyEvent.Hold(code));
                }
                case GLFW_RELEASE -> {
                    KEYS[code] = 0;
                    Main.EVENT_BUS.submit(new KeyEvent.Release(code));
                }
            }
        });

        //noinspection resource
        glfwSetMouseButtonCallback(Main.WINDOW, (window, code, action, mods) -> {
            switch (action) {
                case GLFW_PRESS -> {
                    BUTTONS[code] = 1;
                    Main.EVENT_BUS.submit(new ButtonEvent.Down(code));

                }
                case GLFW_REPEAT -> {
                    BUTTONS[code] = 2;
                    Main.EVENT_BUS.submit(new ButtonEvent.Hold(code));

                }
                case GLFW_RELEASE -> {
                    BUTTONS[code] = 0;
                    Main.EVENT_BUS.submit(new ButtonEvent.Release(code));
                }
            }
        });
    }

    /**
    * Retrieves the Action of a Key.
    *
    * @param GLFW_KEY_CODE Key Code.
    *
    * @return a {@link InputAction}.
    */

    public static InputAction getKey(int GLFW_KEY_CODE) {
        return switch (KEYS[GLFW_KEY_CODE]) {
            case 1 ->
                InputAction.DOWN;
            case 2 ->
                InputAction.HOLD;
            default ->
                InputAction.RELEASE;
        };
    }

    /**
    * Retrieves the Action of a Button.
    *
    * @param GLFW_BUTTON_CODE Button Code.
    *
    * @return a {@link InputAction}.
    */

    public static InputAction getButton(int GLFW_BUTTON_CODE) {
        return switch (BUTTONS[GLFW_BUTTON_CODE]) {
            case 1 ->
                InputAction.DOWN;
            case 2 ->
                InputAction.HOLD;
            default ->
                InputAction.RELEASE;
        };
    }
}