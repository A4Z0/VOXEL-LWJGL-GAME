package org.a4z0.lwjgl.game.resource.font;

public final class Glyph {

    private final int unicode;

    private final int x, y;
    private final int width, height;

    private final float u1, v1;
    private final float u2, v2;

    /**
    * Construct a {@link Glyph}.
    *
    * @param unicode Unicode.
    * @param x X.
    * @param y Y.
    * @param width Width.
    * @param height Height.
    * @param u1 ...
    * @param v1 ...
    * @param u2 ...
    * @param v2 ...
    */

    public Glyph(int unicode, int x, int y, int width, int height, float u1, float v1, float u2, float v2) {
        this.unicode = unicode;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.u1 = u1;
        this.v1 = v1;
        this.u2 = u2;
        this.v2 = v2;
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
    * @return ...
    */

    public float getU1() {
        return this.u1;
    }

    /**
    * @return ...
    */

    public float getV1() {
        return this.v1;
    }

    /**
    * @return ...
    */

    public float getU2() {
        return this.u2;
    }

    /**
    * @return ...
    */

    public float getV2() {
        return this.v2;
    }
}