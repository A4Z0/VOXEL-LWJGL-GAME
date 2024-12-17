package org.a4z0.lwjgl.demo.event.client.input.mouse;

import org.a4z0.lwjgl.demo.event.client.input.InputEvent;
import org.a4z0.lwjgl.demo.input.InputAction;

public abstract class ButtonEvent extends InputEvent {

    /**
    * Construct a {@link ButtonEvent}.
    *
    * @param buttonCode Button Code.
    */

    public ButtonEvent(final int buttonCode, final InputAction action) {
        super(buttonCode, action);
    }

    /**
    * Called when a Button is down.
    */

    public static final class Down extends ButtonEvent {

        /**
        * Construct a {@link Down}.
        *
        * @param buttonCode Button Code.
        */

        public Down(final int buttonCode) {
            super(buttonCode, InputAction.DOWN);
        }
    }

    /**
    * Called when a Button is hold.
    */

    public static final class Hold extends ButtonEvent {

        /**
        * Construct a {@link Hold}.
        *
        * @param buttonCode Button Code.
        */

        public Hold(final int buttonCode) {
            super(buttonCode, InputAction.HOLD);
        }
    }

    /**
    * Called when a Button is released.
    */

    public static final class Release extends ButtonEvent {

        /**
        * Construct a {@link Release}.
        *
        * @param buttonCode Button Code.
        */

        public Release(final int buttonCode) {
            super(buttonCode, InputAction.RELEASE);
        }
    }
}