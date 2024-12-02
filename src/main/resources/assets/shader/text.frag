#version 330 core

in vec2 out_vertex_texture_coordinates;
in vec4 out_color;

uniform sampler2D texture_sampler;

out vec4 fragment_color;

void main(void) {
    vec4 vertex_texture_color = texture(texture_sampler, out_vertex_texture_coordinates);

    fragment_color = vertex_texture_color * out_color;
}
