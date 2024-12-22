package org.a4z0.lwjgl.demo.text;

import org.a4z0.lwjgl.demo.resource.Key;
import org.a4z0.lwjgl.demo.color.Color;

public class TextStyle {

    public static final TextStyle EMPTY = new TextStyle(null, null, null, null, null, null);

    private final Key font;
    private final Color color;
    private final Boolean bold;
    private final Boolean italic;
    private final Boolean underlined;
    private final Boolean strikethrough;

    /**
    * Construct a {@link TextStyle}.
    *
    * @param font Font.
    * @param color Color.
    * @param bold Bold.
    * @param italic Italic.
    * @param underlined Underlined.
    * @param strikethrough Strikethough.
    */

    public TextStyle(Key font, Color color, Boolean bold, Boolean italic, Boolean underlined, Boolean strikethrough) {
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

    public Key getFont() {
        return this.font;
    }

    /**
    * @return the Color.
    */

    public Color getColor() {
        return this.color;
    }

    /**
    * @return true if it is Bold, false otherwise.
    */

    public boolean isBold() {
        return this.bold == Boolean.TRUE;
    }

    /**
    * @return true if it is Italic, false otherwise.
    */

    public boolean isItalic() {
        return this.italic == Boolean.TRUE;
    }

    /**
    * @return true if it is Underlined, false otherwise.
    */

    public boolean isUnderlined() {
        return this.underlined == Boolean.TRUE;
    }

    /**
    * @return true if it is Strikethrough, false otherwise.
    */

    public boolean isStrikethrough() {
        return this.strikethrough == Boolean.TRUE;
    }

    /**
    * @return true if it is empty, false otherwise.
    */

    public boolean isEmpty() {
        return this.equals(TextStyle.EMPTY);
    }

    /**
    * Checks if this is equals to the given {@link Object}.
    *
    * @param o {@link Object} to be compared.
    *
    * @return true if equal, false otherwise.
    */

    @Override
    public boolean equals(Object o) {
        return (o instanceof TextStyle)
            && ((TextStyle) o).getFont() == this.getFont()
            && ((TextStyle) o).getColor() == this.getColor()
            && ((TextStyle) o).isBold() == this.isBold()
            && ((TextStyle) o).isItalic() == this.isItalic()
            && ((TextStyle) o).isUnderlined() == this.isUnderlined()
            && ((TextStyle) o).isStrikethrough() == this.isStrikethrough();
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