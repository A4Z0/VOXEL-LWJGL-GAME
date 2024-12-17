package org.a4z0.lwjgl.demo.util;

import org.a4z0.lwjgl.demo.text.TextStyle;

public interface FormattedText {

    /**
    * @return the Raw Text.
    */

    default String toRawText() {
        StringBuilder s = new StringBuilder();
        this.applyToText(s::append);

        return s.toString();
    }

    /**
    * Applies the Consumer.
    *
    * @param consumer Consumer.
    */

    void applyToText(FormattedText.a consumer);

    /**
    * Applies the Consumer.
    *
    * @param consumer Consumer.
    * @param style Style.
    */

    void applyStyledText(FormattedText.b consumer, TextStyle style);

    /**
    * Construct a {@link FormattedText}.
    *
    * @param s String.
    *
    * @return a new {@link FormattedText}.
    */

    static FormattedText of(final String s) {
        return new FormattedText() {
            @Override
            public void applyToText(a consumer) {
                consumer.accept(s);
            }

            @Override
            public void applyStyledText(b consumer, TextStyle style) {
                consumer.accept(s, style);
            }
        };
    }

    @FunctionalInterface
    interface a {
        void accept(String s);
    }

    @FunctionalInterface
    interface b {
        void accept(String s, TextStyle style);
    }
}