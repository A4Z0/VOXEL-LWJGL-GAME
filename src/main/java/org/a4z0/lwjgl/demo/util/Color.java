package org.a4z0.lwjgl.demo.util;

public final class Color {

    // White (255, 255, 255)
    public static final Color WHITE = new Color(255, 255, 255);

    // Blue (0, 0, 255)
    public static final Color BLUE = new Color(0, 0, 255);

    // Black (0, 0, 0)
    public static final Color BLACK = new Color(0, 0, 0);

    // Red (255, 0, 0)
    public static final Color RED = new Color(255, 0, 0);

    // Green (0, 255, 0)
    public static final Color GREEN = new Color(0, 255, 0);

    // Yellow (255, 255, 0)
    public static final Color YELLOW = new Color(255, 255, 0);

    // Cyan (0, 255, 255)
    public static final Color CYAN = new Color(0, 255, 255);

    // Magenta (255, 0, 255)
    public static final Color MAGENTA = new Color(255, 0, 255);

    // Gray (128, 128, 128)
    public static final Color GRAY = new Color(128, 128, 128);

    // Orange (255, 165, 0)
    public static final Color ORANGE = new Color(255, 165, 0);

    // Pink (255, 192, 203)
    public static final Color PINK = new Color(255, 192, 203);

    // Purple (128, 0, 128)
    public static final Color PURPLE = new Color(128, 0, 128);

    // Brown (165, 42, 42)
    public static final Color BROWN = new Color(165, 42, 42);

    // Light Blue (173, 216, 230)
    public static final Color LIGHT_BLUE = new Color(173, 216, 230);

    // Dark Green (0, 100, 0)
    public static final Color DARK_GREEN = new Color(0, 100, 0);

    // Light Green (144, 238, 144)
    public static final Color LIGHT_GREEN = new Color(144, 238, 144);

    // Indigo (75, 0, 130)
    public static final Color INDIGO = new Color(75, 0, 130);

    // Teal (0, 128, 128)
    public static final Color TEAL = new Color(0, 128, 128);

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
}