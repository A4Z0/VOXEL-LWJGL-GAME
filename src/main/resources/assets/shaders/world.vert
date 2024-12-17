#version 400 core

in vec3 vertex_position;
in int vertex_color;

uniform mat4 camera_projection_view;

out vec4 out_color;

void main() {
    gl_Position = camera_projection_view * vec4(vertex_position, 1.0);

    out_color = vec4(
        (vertex_color) & 0xFF,       // Red
        (vertex_color >> 8) & 0xFF,  // Green
        (vertex_color >> 16) & 0xFF, // Blue
        (vertex_color >> 24) & 0xFF  // Alpha
    ) / 255.f;
}