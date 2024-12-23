package org.a4z0.lwjgl.demo.text;

public interface Content extends FormattedText {

    /**
    * Represents an empty {@link Content}.
    */

    Content EMPTY = new Content() {

        @Override
        public String toString() {
            return "Empty{}";
        }
    };

    @Override
    default void applyToText(TextConsumer consumer) {}

    @Override
    default void applyStyledText(StyledTextConsumer consumer, TextStyle style) {}

    /**
    * Checks if this is equals to the given {@link Object}.
    *
    * @param o {@link Object} to be compared.
    *
    * @return true if equal, false otherwise.
    */

    @Override
    boolean equals(Object o);

    /**
    * @return this as a {@link String}.
    */

    @Override
    String toString();

    /**
    * @return the Hashcode.
    */

    @Override
    int hashCode();
}