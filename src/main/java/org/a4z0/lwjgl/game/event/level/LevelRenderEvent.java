package org.a4z0.lwjgl.game.event.level;

import org.a4z0.lwjgl.api.event.Event;

public abstract class LevelRenderEvent extends Event {

    protected LevelRenderEvent() {}

    public static final class Post extends LevelRenderEvent {}

    public static final class After extends LevelRenderEvent {}
}