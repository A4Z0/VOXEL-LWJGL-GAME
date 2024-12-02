package org.a4z0.lwjgl.demo.font;

public final class Glyph {

    private final int unicode;
    private final int width, height;
    private final int x, y;
    private final int offsetX, offsetY;
    private final int advance;

    /**
    * Construct a {@link Glyph}.
    *
    * @param unicode Unicode.
    * @param width Width.
    * @param height Height.
    * @param x X.
    * @param y Y.
    * @param offsetX Offset X.
    * @param offsetY Offset Y.
    * @param advance Advance.
    */

    public Glyph(
        int unicode,
        int x,
        int y,
        int width,
        int height,
        int offsetX,
        int offsetY,
        int advance
    ) {
        this.unicode = unicode;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.advance = advance;
    }

    /**
    * @return the Unicode.
    */

    public int getUnicode() {
        return this.unicode;
    }

    /**
    * @return the X.
    */

    public int getX() {
        return this.x;
    }

    /**
    * @return the Y.
    */

    public int getY() {
        return this.y;
    }

    /**
    * @return the Width.
    */

    public int getWidth() {
        return this.width;
    }

    /**
    * @return the Height.
    */

    public int getHeight() {
        return this.height;
    }

    /**
    * @return the Offset X.
    */

    public int getOffsetX() {
        return this.offsetX;
    }

    /**
    * @return the Offset Y.
    */

    public int getOffsetY() {
        return this.offsetY;
    }

    /**
    * @return the Advance.
    */

    public int getAdvance() {
        return this.advance;
    }
}