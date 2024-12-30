package org.a4z0.lwjgl.game.event.level.chunk;

import org.a4z0.lwjgl.api.event.Event;

public abstract class ChunkRenderEvent extends Event {

    protected ChunkRenderEvent() {}

    public static final class Post extends ChunkRenderEvent {}

    public static final class After extends ChunkRenderEvent {}
}