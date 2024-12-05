package org.a4z0.lwjgl.demo.ui;

import org.a4z0.lwjgl.demo.DevelopmentConstants;
import org.a4z0.lwjgl.demo.Main;
import org.a4z0.lwjgl.demo.resource.font.Fonts;

public final class Debug extends UI {

    public Debug() {

    }

    @Override
    public void onRezise(int width, int height) {

    }

    @Override
    public void onRender() {
        Main.FONT_RENDERER.drawString(Fonts.MINECRAFT_TRUE_TYPE_FONT, getChrono() + "\n" + getCoordinates() + "\n" + getFacing(), 3f, 3f);
    }

    /**
    * Retrieves a {@link String} written "F: 0 U: 0 T: 0".
    */

    private static String getChrono() {
        return String.format("F: %.0f U: %.0f, T: %.0f", Main.TIMER.getFPS(), Main.TIMER.getUPS(), 0f);
    }

    /**
    * Retrieves a {@link String} written "XYZ: 0.000 / 0.000 / 0.000".
    */

    private static String getCoordinates() {
        return String.format("XYZ: %.3f / %.3f / %.3f", DevelopmentConstants.DEVELOPMENT_PLAYER.getLocation().getX(), DevelopmentConstants.DEVELOPMENT_PLAYER.getLocation().getY(), DevelopmentConstants.DEVELOPMENT_PLAYER.getLocation().getZ());
    }

    /**
    * Retrieves a {@link String} written "Facing: (0.000 / 0.000)".
    */
    
    private static String getFacing() {
        return String.format("Facing: (%.3f / %.3f)", DevelopmentConstants.DEVELOPMENT_PLAYER.getLocation().getPitch(), DevelopmentConstants.DEVELOPMENT_PLAYER.getLocation().getYaw());
    }
}