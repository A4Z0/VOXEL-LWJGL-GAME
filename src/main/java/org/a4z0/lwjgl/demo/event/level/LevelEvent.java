package org.a4z0.lwjgl.demo.event.level;

import org.a4z0.lwjgl.demo.event.Event;
import org.a4z0.lwjgl.demo.level.Level;

public abstract class LevelEvent extends Event {

    protected final Level level;

    /**
    * Construct a {@link LevelEvent}.
    *
    * @param level ...
    */

    public LevelEvent(Level level) {
        this.level = level;
    }

    /**
    * @return ...
    */

    public Level getLevel() {
        return this.level;
    }
}