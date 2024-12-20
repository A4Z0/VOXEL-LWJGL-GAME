vec4 color_from_argb(int argb) {
    return vec4(
        (argb >> 16) & 0xFF, // Red
        (argb >> 8 ) & 0xFF, // Green
        (argb)       & 0xFF, // Blue
        (argb >> 24) & 0xFF  // Alpha
    ) / 255.f;
}