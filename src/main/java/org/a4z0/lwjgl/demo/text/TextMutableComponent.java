package org.a4z0.lwjgl.demo.text;

import java.util.List;
import java.util.Objects;

public class TextMutableComponent implements TextComponent {

    protected Content content;
    protected TextStyle style;

    protected final List<TextComponent> components;

    /**
    * Construct a {@link TextMutableComponent}.
    *
    * @param content Content.
    * @param style Style.
    * @param components Components.
    */

    protected TextMutableComponent(Content content, TextStyle style, List<TextComponent> components) {
        this.content = content;
        this.style = style;
        this.components = components;
    }

    @Override
    public Content getContent() {
        return this.content;
    }

    /**
    * Sets the Content.
    *
    * @param content Content to be set.
    *
    * @return this {@link TextMutableComponent}.
    */

    public TextMutableComponent setContent(Content content) {
        this.content = content;

        return this;
    }

    @Override
    public TextStyle getStyle() {
        return this.style;
    }

    /**
    * Sets the Style.
    *
    * @param style Style to be set.
    *
    * @return this {@link TextMutableComponent}.
    */

    public TextMutableComponent setStyle(TextStyle style) {
        this.style = style;

        return this;
    }

    @Override
    public List<TextComponent> getComponents() {
        return this.components;
    }

    /**
    * Appends a Component with the given Text.
    *
    * @param s Text.
    *
    * @return this {@link TextMutableComponent}.
    */

    public TextMutableComponent append(String s) {
        return this.append(TextComponent.text(s));
    }

    /**
    * Appends a Component.
    *
    * @param component Component to be appended.
    *
    * @return this {@link TextMutableComponent}.
    */

    public TextMutableComponent append(TextComponent component) {
        this.components.add(component);

        return this;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof TextMutableComponent)
            && ((TextMutableComponent) o).content.equals(this.content)
            && ((TextMutableComponent) o).style.equals(this.style)
            && ((TextMutableComponent) o).components.equals(this.components);
    }

    @Override
    public String toString() {
        return "TextMutableComponent{"
            + "\"Content\": "
            + this.getContent()
            + ", "
            + "\"Style\": "
            + this.getStyle()
            + ", "
            + "\"Components\": "
            + this.getComponents()
        + "}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.content, this.style, this.components);
    }
}