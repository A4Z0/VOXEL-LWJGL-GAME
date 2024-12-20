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
    * @param consumer Text Consumer.
    */

    void applyToText(FormattedText.TextConsumer consumer);

    /**
    * Applies the Consumer.
    *
    * @param consumer Styled Text Consumer.
    * @param style Style.
    */

    void applyStyledText(FormattedText.StyledTextConsumer consumer, TextStyle style);

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
            public void applyToText(TextConsumer consumer) {
                consumer.acceptText(s);
            }

            @Override
            public void applyStyledText(StyledTextConsumer consumer, TextStyle style) {
                consumer.acceptStyledText(s, style);
            }
        };
    }

    @FunctionalInterface
    interface TextConsumer {
        void acceptText(String s);
    }

    @FunctionalInterface
    interface StyledTextConsumer {
        void acceptStyledText(String s, TextStyle style);
    }
}