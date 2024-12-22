package org.a4z0.lwjgl.demo.text;

import org.a4z0.lwjgl.demo.resource.language.Language;

public final class TranslatableContent extends Content {

    private final String text;
    private final String fallback;
    private final Object[] objects;

    /**
    * Construct a {@link TranslatableContent}.
    *
    * @param text Text.
    * @param args Arguments.
    */

    public TranslatableContent(String text, Object... args) {
        this(text, null, args);
    }

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
        this.objects = args;
    }

    @Override
    public void applyToText(TextConsumer consumer) {
        String translated = this.fallback != null
                ? Language.getInstance().getOrDefault(this.text, this.fallback)
                : Language.getInstance().getOrFallback(this.text);

        consumer.acceptText(String.format(translated, this.objects));
    }

    @Override
    public void applyStyledText(StyledTextConsumer consumer, TextStyle style) {
        String translated = this.fallback != null
                ? Language.getInstance().getOrDefault(this.text, this.fallback)
                : Language.getInstance().getOrFallback(this.text);

        consumer.acceptStyledText(String.format(translated, this.objects), style);
    }

    @Override
    public String toString() {
        return "Translatable{\"Text\": \"" + this.text + "\", \"Fallback\": \"" + this.fallback + "\"}";
    }
}