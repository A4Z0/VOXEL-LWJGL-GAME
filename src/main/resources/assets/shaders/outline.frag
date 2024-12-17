#version 400 core

in vec4 out_color;

out vec4 fragment_color;

void main() {
    fragment_color = out_color;
}