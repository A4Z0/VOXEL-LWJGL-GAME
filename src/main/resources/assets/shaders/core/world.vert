#version 400 core

#import <color.glsl>

in vec3 vertex_position;
in int vertex_color;

uniform mat4 camera_projection_view;
uniform mat4 transform;

out vec4 out_color;

void main() {
    gl_Position = camera_projection_view * transform * vec4(vertex_position, 1.0);

    out_color = color_from_argb(vertex_color);
}