package org.a4z0.lwjgl.demo.text;

import org.a4z0.lwjgl.demo.resource.Key;
import org.a4z0.lwjgl.demo.color.Color;

import java.util.Objects;

public class TextStyle {

    public static final TextStyle EMPTY = new TextStyle(null, null, null, null, null, null);

    protected final Key font;
    protected final Color color;
    protected final Boolean bold;
    protected final Boolean italic;
    protected final Boolean underlined;
    protected final Boolean strikethrough;

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
    * Retrieves a new {@link TextStyle} with the given Font.
    *
    * @param font Font.
    *
    * @return a new {@link TextStyle}.
    */

    public TextStyle setFont(Key font) {
        return new TextStyle(font, this.color, this.bold, this.italic, this.underlined, this.strikethrough);
    }

    /**
    * @return the Color.
    */

    public Color getColor() {
        return this.color;
    }

    /**
    * Retrieves a new {@link TextStyle} with the given Color.
    *
    * @param color Color.
    *
    * @return a new {@link TextStyle}.
    */

    public TextStyle setColor(Color color) {
        return new TextStyle(this.font, color, this.bold, this.italic, this.underlined, this.strikethrough);
    }

    /**
    * @return true if it is Bold, false otherwise.
    */

    public boolean isBold() {
        return this.bold == Boolean.TRUE;
    }

    /**
    * Retrieves a new {@link TextStyle} with the given Bold.
    *
    * @param bold Bold.
    *
    * @return a new {@link TextStyle}.
    */

    public TextStyle setBold(Boolean bold) {
        return new TextStyle(this.font, this.color, bold, this.italic, this.underlined, this.strikethrough);
    }

    /**
    * @return true if it is Italic, false otherwise.
    */

    public boolean isItalic() {
        return this.italic == Boolean.TRUE;
    }

    /**
    * Retrieves a new {@link TextStyle} with the given Italic.
    *
    * @param italic Italic.
    *
    * @return a new {@link TextStyle}.
    */

    public TextStyle setItalic(Boolean italic) {
        return new TextStyle(this.font, this.color, this.bold, italic, this.underlined, this.strikethrough);
    }

    /**
    * @return true if it is Underlined, false otherwise.
    */

    public boolean isUnderlined() {
        return this.underlined == Boolean.TRUE;
    }

    /**
    * Retrieves a new {@link TextStyle} with the given Underlined.
    *
    * @param underlined Underlined.
    *
    * @return a new {@link TextStyle}.
    */

    public TextStyle setUnderlined(Boolean underlined) {
        return new TextStyle(this.font, this.color, this.bold, this.italic, underlined, this.strikethrough);
    }

    /**
    * @return true if it is Strikethrough, false otherwise.
    */

    public boolean isStrikethrough() {
        return this.strikethrough == Boolean.TRUE;
    }

    /**
    * Retrieves a new {@link TextStyle} with the given Strikethrough.
    *
    * @param strikethrough Strikethrough.
    *
    * @return a new {@link TextStyle}.
    */

    public TextStyle setStrikethrough(Boolean strikethrough) {
        return new TextStyle(this.font, this.color, this.bold, this.italic, this.underlined, strikethrough);
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

    /**
    * @return this as a {@link String}.
    */

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

    /**
    * @return the Hashcode.
    */

    @Override
    public int hashCode() {
        return Objects.hash(this.font, this.color, this.bold, this.italic, this.underlined, this.strikethrough);
    }
}