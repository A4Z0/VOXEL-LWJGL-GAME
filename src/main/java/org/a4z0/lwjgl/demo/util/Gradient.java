package org.a4z0.lwjgl.demo.util;

import org.a4z0.lwjgl.demo.color.Color;
import org.a4z0.lwjgl.demo.text.TextComponent;
import org.a4z0.lwjgl.demo.text.TextStyle;

public final class Gradient {

    Gradient() {}

    /**
    * ...
    *
    * @param steps ...
    * @param colors ...
    *
    * @return ...
    */

    public static Color[] create(int steps, Color... colors) {
        Color[] gradient = new Color[steps];

        int numColors = colors.length;
        int segmentSize = steps / (numColors - 1);

        for (int i = 0; i < steps; i++) {
            int segment = i / segmentSize;
            segment = Math.min(segment, numColors - 2);

            float ratio = (float) (i - segment * segmentSize) / (float) segmentSize;

            int r = (int) (colors[segment].getRed() + ratio * (colors[segment + 1].getRed() - colors[segment].getRed()));
            int g = (int) (colors[segment].getGreen() + ratio * (colors[segment + 1].getGreen() - colors[segment].getGreen()));
            int b = (int) (colors[segment].getBlue() + ratio * (colors[segment + 1].getBlue() - colors[segment].getBlue()));
            int a = (int) (colors[segment].getAlpha() + ratio * (colors[segment + 1].getAlpha() - colors[segment].getAlpha()));

            gradient[i] = new Color(r, g, b, a);
        }

        return gradient;
    }

    public static TextComponent[] test(String s, Color... colors) {
        Color[] gradient = create(s.length(), colors);
        TextComponent[] components = new TextComponent[s.length()];

        for(int i = 0; i < s.length(); i++)
            components[i] = TextComponent.text(s.substring(i, i + 1)).setStyle(TextStyle.color(gradient[i]));

        return components;
    }
}