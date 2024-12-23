package org.a4z0.lwjgl.demo.text;

import java.util.Objects;

public class LiteralContent implements Content {

    protected final String s;

    /**
    * Construct a {@link LiteralContent}.
    *
    * @param s Text.
    */

    protected LiteralContent(String s) {
        this.s = s;
    }

    @Override
    public void applyToText(TextConsumer consumer) {
        consumer.acceptText(this.s);
    }

    @Override
    public void applyStyledText(StyledTextConsumer consumer, TextStyle style) {
        consumer.acceptStyledText(this.s, style);
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof LiteralContent) && ((LiteralContent) o).s.equals(this.s);
    }

    @Override
    public String toString() {
        return "Literal{"
            + "\"Text\": \"" + this.s + "\""
        + " }";
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.s);
    }
}