package org.a4z0.lwjgl.demo.resource.font;

/**
* Construct a {@link FontMetrics}.
*
* @param width Width.
* @param height Height.
* @param ascent Ascent.
* @param descent Descent.
* @param lineGap Line Gap.
*/

public record FontMetrics(int width, int height, float ascent, float descent, float lineGap) {}