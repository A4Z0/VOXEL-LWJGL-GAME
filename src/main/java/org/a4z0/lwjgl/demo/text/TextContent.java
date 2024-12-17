package org.a4z0.lwjgl.demo.text;

public final class TextContent extends Content {

    private final String text;

    /**
    * Construct a {@link TextContent}.
    *
    * @param text Text.
    */

    TextContent(String text) {
        this.text = text;
    }

    @Override
    public void applyToText(a consumer) {
        consumer.accept(this.text);
    }

    @Override
    public void applyStyledText(b consumer, TextStyle style) {
        consumer.accept(this.text, style);
    }

    @Override
    public String toString() {
        return "Text{"
            + "\"Text\": \"" + this.text + "\""
            + " }";
    }
}