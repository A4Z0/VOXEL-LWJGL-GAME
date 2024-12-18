package org.a4z0.lwjgl.demo.text;

import java.util.List;

public final class TextMutableComponent implements TextComponent {

    private Content content;
    private TextStyle style;

    private final List<TextComponent> components;

    /**
    * Construct a {@link TextMutableComponent}.
    *
    * @param content Content.
    * @param style Style.
    * @param components Components.
    */

    TextMutableComponent(Content content, TextStyle style, List<TextComponent> components) {
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
    * @param text Content to be set.
    *
    * @return this {@link TextMutableComponent}.
    */

    public TextMutableComponent setContent(Content text) {
        this.content = text;

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
    * Appends a String.
    *
    * @param s String to be appended.
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

    /**
    * ...
    *
    * @param components ...
    *
    * @return this {@link TextMutableComponent}.
    */

    public TextMutableComponent append(TextComponent... components) {
        for(TextComponent component : components)
            this.append(component);

        return this;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof TextMutableComponent)
            && ((TextMutableComponent) o).getContent().equals(this.getContent())
            && ((TextMutableComponent) o).getStyle().equals(this.getStyle())
            && ((TextMutableComponent) o).getComponents().equals(this.getComponents());
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
}