package org.a4z0.lwjgl.demo.text;

import org.a4z0.lwjgl.demo.util.FormattedText;

public abstract class Content implements FormattedText {

    public static Content EMPTY = new Content() {

        @Override
        public String toString() {
            return "Empty{}";
        }
    };

    @Override
    public void applyToText(TextConsumer consumer) {
        consumer.acceptText(null);
    }

    @Override
    public void applyStyledText(StyledTextConsumer consumer, TextStyle style) {
        consumer.acceptStyledText(null, TextStyle.empty());
    }

    /**
    * Checks if this {@link Content} is equals to the given {@link Object}.
    *
    * @param o {@link Object} to be compared.
    *
    * @return true if equal, false otherwise.
    */

    @Override
    public boolean equals(Object o) {
        return this.getClass().isAssignableFrom(o.getClass()) && o.toString().equals(this.toString());
    }

    /**
    * @return this as a {@link String}.
    */

    @Override
    public abstract String toString();
}