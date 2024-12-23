package org.a4z0.lwjgl.demo.text;

public interface FormattedText {

    /**
    * @return the Text.
    */

    default String getText() {
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

    @FunctionalInterface
    interface TextConsumer {
        void acceptText(String text);
    }

    @FunctionalInterface
    interface StyledTextConsumer {
        void acceptStyledText(String text, TextStyle style);
    }
}