#version 400 core

const float offset = 1.00002f;

in vec3 vertex_position;
in int vertex_color;

uniform mat4 camera_projection_view;
uniform mat4 transformation;

out vec4 out_color;

void main() {
    gl_Position = camera_projection_view * transformation * vec4(vertex_position * offset, 1.0);
    out_color = vec4(
        (vertex_color) & 0xFF,       // Red
        (vertex_color >> 8) & 0xFF,  // Green
        (vertex_color >> 16) & 0xFF, // Blue
        (vertex_color >> 24) & 0xFF  // Alpha
    ) / 255.f;
}