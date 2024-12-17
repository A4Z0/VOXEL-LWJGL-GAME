package org.a4z0.lwjgl.demo.screen;

import org.a4z0.lwjgl.demo.Main;
import org.a4z0.lwjgl.demo.event.EventHandler;
import org.a4z0.lwjgl.demo.event.client.input.keyboard.KeyEvent;
import org.a4z0.lwjgl.demo.event.client.render.WorldRenderEvent;
import org.a4z0.lwjgl.demo.registry.Registries;
import org.a4z0.lwjgl.demo.resource.Key;
import org.a4z0.lwjgl.demo.resource.font.FontRenderer;
import org.a4z0.lwjgl.demo.text.*;
import org.a4z0.lwjgl.demo.text.TextComponent;
import org.a4z0.lwjgl.demo.util.Color;
import org.a4z0.lwjgl.demo.util.Gradient;

import java.awt.*;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_F3;

public final class Debugger {

    public static boolean ENABLED;

    @EventHandler
    public void onKeyDown(final KeyEvent.Down e) {
        if(e.getCode() != GLFW_KEY_F3)
            return;

        ENABLED = !ENABLED;
    }

    @EventHandler
    public void onRender(final WorldRenderEvent.After e) {
        if(!ENABLED)
            return;

        Registries.SHADER_PROGRAM.get(Key.of("text")).bind();

        FontRenderer.drawString(TextComponent.empty()
            .append(TextComponent.translatable("debug.name"))
            .append(TextComponent.translatable("debug.framerate", Main.CHRONO.getFPS(), 0f))
            .append(TextComponent.translatable("debug.location", 0f, 0f, 0f))
            .append(TextComponent.translatable("debug.facing", 0f, 0f))
            .append(Gradient.test("This is a Simple Text!", Color.RED, Color.ORANGE)),
        3f, 3f, 1.5f);
    }
}