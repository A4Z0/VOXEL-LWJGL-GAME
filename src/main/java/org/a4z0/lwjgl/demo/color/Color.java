package org.a4z0.lwjgl.demo.color;

public final class Color {

    public static final Color WHITE = Color.hex("#FFFFFF");
    public static final Color BLACK = Color.hex("#000000");

    private final byte r;
    private final byte g;
    private final byte b;
    private final byte a;

    /**
    * Construct a {@link Color}.
    *
    * @param r Red.
    * @param g Green.
    * @param b Blue.
    */

    public Color(int r, int g, int b) {
        this(r, g, b, 255);
    }

    /**
    * Construct a {@link Color}.
    *
    * @param r Red.
    * @param g Green.
    * @param b Blue.
    * @param a Alpha.
    */

    public Color(int r, int g, int b, int a) {
        this.r = (byte) (r & 0xFF);
        this.g = (byte) (g & 0xFF);
        this.b = (byte) (b & 0xFF);
        this.a = (byte) (a & 0xFF);
    }

    /**
    * Retrieves the Red between 0-255.
    *
    * @return the Red.
    */

    public int getRed() {
        return this.r & 0xFF;
    }

    /**
    * Retrieves a new {@link Color} with the new Red.
    *
    * @param r Red.
    *
    * @return a new {@link Color}.
    */

    public Color setRed(int r) {
        return new Color(r, this.getGreen(), this.getBlue(), this.getAlpha());
    }

    /**
    * Retrieves the Green between 0-255.
    *
    * @return the Green.
    */

    public int getGreen() {
        return this.g & 0xFF;
    }

    /**
    * Retrieves a new {@link Color} with the new Green.
    *
    * @param g Green.
    *
    * @return a new {@link Color}.
    */

    public Color setGreen(int g) {
        return new Color(this.getRed(), g, this.getBlue(), this.getAlpha());
    }

    /**
    * Retrieves the Blue between 0-255.
    *
    * @return the Blue.
    */

    public int getBlue() {
        return this.b & 0xFF;
    }

    /**
    * Retrieves a new {@link Color} with the new Blue.
    *
    * @param b Blue.
    *
    * @return a new {@link Color}.
    */

    public Color setBlue(int b) {
        return new Color(this.getRed(), this.getGreen(), b, this.getAlpha());
    }

    /**
    * Retrieves the Alpha between 0-255.
    *
    * @return the Alpha.
    */

    public int getAlpha() {
        return this.a & 0xFF;
    }

    /**
    * Retrieves a new {@link Color} with the new Alpha.
    *
    * @param a Alpha.
    *
    * @return a new {@link Color}.
    */

    public Color setAlpha(int a) {
        return new Color(this.getRed(), this.getGreen(), this.getBlue(), a);
    }

    /**
    * @return this as an RGB.
    */

    public int asRGB() {
        return this.getRed() << 16 | this.getGreen() << 8 | this.getBlue();
    }

    /**
    * @return this as an ARGB.
    */

    public int asARGB() {
        return this.getAlpha() << 24 | this.asRGB();
    }

    /**
    * Checks if this {@link Color} is equals to the given {@link Object}.
    *
    * @param o {@link Object} to be compared.
    *
    * @return true if equal, false otherwise.
    */

    @Override
    public boolean equals(Object o) {
        return (o instanceof Color)
            && ((Color) o).getRed() == this.getRed()
            && ((Color) o).getGreen() == this.getGreen()
            && ((Color) o).getBlue() == this.getBlue()
            && ((Color) o).getAlpha() == this.getAlpha();
    }

    /**
    * @return the Hash Code.
    */

    @Override
    public int hashCode() {
        return 31 * this.asARGB();
    }

    /**
    * @return this as a {@link String}.
    */

    @Override
    public String toString() {
        return "Color{"
            + "\"Red\": " + this.getRed()
            + ", "
            + "\"Green\": " + this.getGreen()
            + ", "
            + "\"Blue\": " + this.getBlue()
            + ", "
            + "\"Alpha\": " + this.getAlpha()
        + "}";
    }

    /**
    * Construct a {@link Color} with an RGB.
    *
    * @param rgb RGB.
    *
    * @return a new {@link Color}.
    */

    public static Color rgb(int rgb) {
        return new Color((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF);
    }

    /**
    * Construct a {@link Color} with an ARGB.
    *
    * @param argb ARGB.
    *
    * @return a new {@link Color}.
    */

    public static Color argb(int argb) {
        return new Color((argb >> 16) & 0xFF, (argb >> 8) & 0xFF, argb & 0xFF, (argb >> 24) & 0xFF);
    }

    /**
    * Construct a {@link Color} with a Hexcode.
    *
    * @param hexcode Hexcode.
    *
    * @return a new {@link Color}.
    */

    public static Color hex(String hexcode) {
        String newHex = hexcode.replaceFirst("#", "");

        return switch (newHex.length()) {
            case 6 ->
                Color.rgb(Integer.parseInt(newHex, 16));
            case 8 ->
                Color.argb(Integer.parseInt(newHex.substring(0, 6), 16) | Integer.parseInt(newHex.substring(6), 16) << 24);
            default ->
                throw new IllegalArgumentException("Unable to format Hexcode.");
        };
    }
}