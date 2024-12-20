package org.a4z0.lwjgl.demo.text;

import org.a4z0.lwjgl.demo.resource.Key;
import org.a4z0.lwjgl.demo.color.Color;

public interface TextStyle {

    /**
    * @return the Font.
    */

    Key getFont();

    /**
    * @return the Color.
    */

    Color getColor();

    /**
    * @return true if it is Bold, false otherwise.
    */

    boolean isBold();

    /**
    * @return true if it is Italic, false otherwise.
    */

    boolean isItalic();

    /**
    * @return true if it is Underlined, false otherwise.
    */

    boolean isUnderlined();

    /**
    * @return true if it is Strikethrough, false otherwise.
    */

    boolean isStrikethrough();

    /**
    * @return true if it is empty, false otherwise.
    */

    boolean isEmpty();

    /**
    * Checks if this {@link TextStyle} is equals to the given {@link Object}.
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
    * @return an empty {@link TextMutableStyle}.
    */

    static TextMutableStyle empty() {
        return new TextMutableStyle(null, null, null, null, null, null);
    }

    /**
    * Construct a {@link TextMutableStyle}.
    *
    * @param font ...
    *
    * @return a new {@link TextMutableStyle}.
    */

    static TextMutableStyle font(Key font) {
        return new TextMutableStyle(font, null, null, null, null, null);
    }

    /**
    * Construct a {@link TextMutableStyle}.
    *
    * @param color ...
    *
    * @return a new {@link TextMutableStyle}.
    */

    static TextMutableStyle color(Color color) {
        return new TextMutableStyle(null, color, null, null, null, null);
    }

    /**
    * Construct a {@link TextMutableStyle}.
    *
    * @param bold ...
    *
    * @return a new {@link TextMutableStyle}.
    */

    static TextMutableStyle bold(boolean bold) {
        return new TextMutableStyle(null, null, bold, null, null, null);
    }

    /**
    * Construct a {@link TextMutableStyle}.
    *
    * @param italic ...
    *
    * @return a new {@link TextMutableStyle}.
    */

    static TextMutableStyle italic(boolean italic) {
        return new TextMutableStyle(null, null, null, italic, null, null);
    }

    /**
    * Construct a {@link TextMutableStyle}.
    *
    * @param underlined ...
    *
    * @return a new {@link TextMutableStyle}.
    */

    static TextMutableStyle underlined(boolean underlined) {
        return new TextMutableStyle(null, null, null, null, underlined, null);
    }

    /**
    * Construct a {@link TextMutableStyle}.
    *
    * @param strikethrough ...
    *
    * @return a new {@link TextMutableStyle}.
    */

    static TextMutableStyle strikethrough(boolean strikethrough) {
        return new TextMutableStyle(null, null, null, null, null, strikethrough);
    }
}