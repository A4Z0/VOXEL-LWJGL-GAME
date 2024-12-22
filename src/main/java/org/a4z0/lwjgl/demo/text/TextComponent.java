package org.a4z0.lwjgl.demo.text;

import com.google.common.collect.Lists;

import java.util.List;

public interface TextComponent extends FormattedText {

    @Override
    default void applyToText(TextConsumer consumer) {
        this.getContent().applyToText(consumer);

        for(TextComponent Component : this.getComponents())
            Component.applyToText(consumer);
    }

    @Override
    default void applyStyledText(StyledTextConsumer consumer, TextStyle style) {
        this.getContent().applyStyledText(consumer, this.getStyle());

        for(TextComponent Component : this.getComponents())
            Component.applyStyledText(consumer, this.getStyle());
    }

    /**
    * @return the Content.
    */

    Content getContent();

    /**
    * @return the {@link TextStyle Style}.
    */

    TextStyle getStyle();

    /**
    * @return the Components.
    */

    List<TextComponent> getComponents();

    /**
    * Retrieves this as a Flat List.
    *
    * @return this to a {@link List Flat List}.
    */

    default List<TextComponent> toFlatList() {
        return this.toFlatList(TextStyle.empty());
    }

    /**
    * Retrieves this as a Flat List.
    *
    * @param style Style.
    *
    * @return this to a {@link List Flat List}.
    */

    default List<TextComponent> toFlatList(TextStyle style) {
        List<TextComponent> List = Lists.newArrayList();

        this.applyStyledText((A, B) -> {
            if(A != null && !A.isEmpty())
                List.add(TextComponent.text(A).setStyle(B));
        }, style);

        return List;
    }

    /**
    * @return an empty {@link TextMutableComponent}.
    */

    static TextMutableComponent empty() {
        return new TextMutableComponent(Content.EMPTY, TextStyle.empty(), Lists.newArrayList());
    }

    /**
    * Construct a {@link TextMutableComponent}.
    *
    * @param text Text.
    *
    * @return a new {@link TextMutableComponent}.
    */

    static TextMutableComponent text(String text) {
        return new TextMutableComponent(new TextContent(text), TextStyle.empty(), Lists.newArrayList());
    }

    /**
    * Construct a {@link TextMutableComponent}.
    *
    * @param text Text.
    * @param args Arguments.
    *
    * @return a new {@link TextMutableComponent}.
    */

    static TextMutableComponent translatable(String text, Object... args) {
        return translatable(text, null, args);
    }

    /**
    * Construct a {@link TextMutableComponent}.
    *
    * @param text Text.
    * @param fallback Fallback.
    * @param args Arguments.
    *
    * @return a new {@link TextMutableComponent}.
    */

    static TextMutableComponent translatable(String text, String fallback, Object... args) {
        return new TextMutableComponent(new TranslatableContent(text, fallback, args), TextStyle.empty(), Lists.newArrayList());
    }
}