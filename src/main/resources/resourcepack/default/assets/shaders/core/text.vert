#version 330 core

#import <color.glsl>

in vec2 vertex_position;
in vec2 vertex_texture_coordinates;
in int vertex_color;

out vec2 out_vertex_texture_coordinates;
out vec4 out_color;

void main(void) {
    gl_Position = vec4(vertex_position, 0.0, 1.0);

    out_color = color_from_argb(vertex_color);
    out_vertex_texture_coordinates = vertex_texture_coordinates;
}