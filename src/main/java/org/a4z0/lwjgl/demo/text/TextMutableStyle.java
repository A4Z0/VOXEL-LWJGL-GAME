package org.a4z0.lwjgl.demo.text;

import org.a4z0.lwjgl.demo.resource.Key;
import org.a4z0.lwjgl.demo.color.Color;

public class TextMutableStyle implements TextStyle {

    private final Key font;
    private final Color color;
    private final Boolean bold;
    private final Boolean italic;
    private final Boolean underlined;
    private final Boolean strikethrough;

    /**
    * Construct a {@link TextMutableStyle}.
    *
    * @param font Font.
    * @param color Color.
    * @param bold Bold.
    * @param italic Italic.
    * @param underlined Underlined.
    * @param strikethrough Strikethough.
    */

    public TextMutableStyle(Key font, Color color, Boolean bold, Boolean italic, Boolean underlined, Boolean strikethrough) {
        this.font = font;
        this.color = color;
        this.bold = bold;
        this.italic = italic;
        this.underlined = underlined;
        this.strikethrough = strikethrough;
    }

    /**
    * @return the Font.
    */

    @Override
    public Key getFont() {
        return this.font;
    }

    /**
    * @return the Color.
    */

    @Override
    public Color getColor() {
        return this.color;
    }

    /**
    * @return true if it is Bold, false otherwise.
    */

    @Override
    public boolean isBold() {
        return this.bold == Boolean.TRUE;
    }

    /**
    * @return true if it is Italic, false otherwise.
    */

    @Override
    public boolean isItalic() {
        return this.italic == Boolean.TRUE;
    }

    /**
    * @return true if it is Underlined, false otherwise.
    */

    @Override
    public boolean isUnderlined() {
        return this.underlined == Boolean.TRUE;
    }

    /**
    * @return true if it is Strikethrough, false otherwise.
    */

    @Override
    public boolean isStrikethrough() {
        return this.strikethrough == Boolean.TRUE;
    }

    /**
    * @return true if it is empty, false otherwise.
    */

    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
    * Checks if this {@link TextMutableStyle} is equals to the given {@link Object}.
    *
    * @param o {@link Object} to be compared.
    *
    * @return true if equal, false otherwise.
    */

    @Override
    public boolean equals(Object o) {
        return (o instanceof TextMutableStyle)
            && ((TextMutableStyle) o).getFont() == this.getFont()
            && ((TextMutableStyle) o).getColor() == this.getColor()
            && ((TextMutableStyle) o).isBold() == this.isBold()
            && ((TextMutableStyle) o).isItalic() == this.isItalic()
            && ((TextMutableStyle) o).isUnderlined() == this.isUnderlined()
            && ((TextMutableStyle) o).isStrikethrough() == this.isStrikethrough();
    }

    @Override
    public String toString() {
        return "TextStyle{"
            + "\"Font\": " + this.getFont()
            + ", "
            + "\"Color\": " + this.getColor()
            + ", "
            + "\"Bold\": " + this.isBold()
            + ", "
            + "\"Italic\": " + this.isItalic()
            + ", "
            + "\"Underlined\": " + this.isUnderlined()
            + ", "
            + "\"Strikethrough\": " + this.isStrikethrough()
        + " }";
    }
}