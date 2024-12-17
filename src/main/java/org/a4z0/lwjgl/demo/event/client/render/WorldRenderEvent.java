package org.a4z0.lwjgl.demo.event.client.render;

public abstract class WorldRenderEvent extends RenderEvent {

    WorldRenderEvent() {}

    /**
    * ...
    */

    public static final class Post extends WorldRenderEvent {}

    /**
    * ...
    */

    public static final class After extends WorldRenderEvent {}
}