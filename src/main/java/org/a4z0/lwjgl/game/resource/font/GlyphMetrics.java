package org.a4z0.lwjgl.game.resource.font;

/**
* Construct a {@link GlyphMetrics}.
*
* @param x X-Axis.
* @param y Y-Axis.
* @param width Width.
* @param height Height.
* @param offsetX Axis-X Offset.
* @param offsetY Axis-Y Offset.
* @param advance Advance.
* @param bearing Bearing.
*/

public record GlyphMetrics(int x, int y, int width, int height, int offsetX, int offsetY, float advance, float bearing) {}