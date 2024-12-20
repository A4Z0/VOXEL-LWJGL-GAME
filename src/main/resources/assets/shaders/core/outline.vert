#version 400 core

#import <color.glsl>

const float offset = 1.00002f;

in vec3 vertex_position;
in int vertex_color;

uniform mat4 camera_projection_view;

out vec4 out_color;

void main() {
    gl_Position = camera_projection_view * vec4(vertex_position * offset, 1.0);

    out_color = color_from_argb(vertex_color);
}