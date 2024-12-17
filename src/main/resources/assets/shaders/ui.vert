#version 330 core

in vec2 vertex_position;
in int vertex_color;

out vec4 out_color;

void main(void) {
    gl_Position = vec4(vertex_position, 0.0, 1.0);

    out_color = vec4(
        (vertex_color) & 0xFF,       // Red
        (vertex_color >> 8) & 0xFF,  // Green
        (vertex_color >> 16) & 0xFF, // Blue
        (vertex_color >> 24) & 0xFF  // Alpha
    ) / 255.f;
}