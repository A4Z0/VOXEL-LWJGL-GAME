package org.a4z0.lwjgl.demo.event.input.keyboard;

import org.a4z0.lwjgl.demo.event.input.InputEvent;
import org.a4z0.lwjgl.demo.input.InputAction;

public abstract class KeyEvent extends InputEvent {

    /**
    * Construct a {@link KeyEvent}.
    *
    * @param keyCode Key Code.
    */

    public KeyEvent(final int keyCode, final InputAction action) {
        super(keyCode, action);
    }

    /**
    * Called when a Key is down.
    */

    public static final class Down extends KeyEvent {

        /**
        * Construct a {@link Down}.
        *
        * @param keyCode Key Code.
        */

        public Down(final int keyCode) {
            super(keyCode, InputAction.DOWN);
        }
    }

    /**
    * Called when a Key is hold.
    */

    public static final class Hold extends KeyEvent {

        /**
        * Construct a {@link Hold}.
        *
        * @param keyCode Key Code.
        */

        public Hold(final int keyCode) {
            super(keyCode, InputAction.HOLD);
        }
    }

    /**
    * Called when a Key is released.
    */

    public static final class Release extends KeyEvent {

        /**
        * Construct a {@link Release}.
        *
        * @param keyCode Key Code.
        */

        public Release(final int keyCode) {
            super(keyCode, InputAction.RELEASE);
        }
    }
}