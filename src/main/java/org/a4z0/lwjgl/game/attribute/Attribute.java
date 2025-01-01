package org.a4z0.lwjgl.game.attribute;

public class Attribute {

    protected final double value;

    /**
    * Construct a {@link Attribute}.
    *
    * @param value Value.
    */

    public Attribute(double value) {
        this.value = value;
    }

    /**
    * @return the Value.
    */

    public double getValue() {
        return this.value;
    }
}