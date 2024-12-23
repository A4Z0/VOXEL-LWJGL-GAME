package org.a4z0.lwjgl.demo.text;

import org.a4z0.lwjgl.demo.resource.language.Language;

import java.util.Arrays;
import java.util.Objects;

public class TranslatableContent implements Content {

    protected final String text;
    protected final String fallback;
    protected final Object[] args;

    /**
    * Construct a {@link TranslatableContent}.
    *
    * @param text Text.
    * @param fallback Fallback.
    * @param args Arguments.
    */

    public TranslatableContent(String text, String fallback, Object... args) {
        this.text = text;
        this.fallback = fallback;
        this.args = args;
    }

    @Override
    public void applyToText(TextConsumer consumer) {
        consumer.acceptText(String.format(this.getTranslatedString(), this.args));
    }

    @Override
    public void applyStyledText(StyledTextConsumer consumer, TextStyle style) {
        consumer.acceptStyledText(String.format(this.getTranslatedString(), this.args), style);
    }

    private String getTranslatedString() {
        return this.fallback != null ? Language.getInstance().getOrDefault(this.text, this.fallback) : Language.getInstance().getOrFallback(this.text);
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof TranslatableContent)
            && ((TranslatableContent) o).text.equals(this.text)
            && ((TranslatableContent) o).fallback.equals(this.fallback)
            && Arrays.equals(((TranslatableContent) o).args, this.args);
    }

    @Override
    public String toString() {
        return "Translatable{"
            + "\"Text\": \"" + this.text + "\""
            + ", "
            + "\"Fallback\": \"" + this.fallback + "\""
        + "}";
    }

    @Override
    public int hashCode() {
        return (Objects.hash(this.text) * 31 + Objects.hash(this.fallback)) * 31 + Arrays.hashCode(this.args);
    }
}