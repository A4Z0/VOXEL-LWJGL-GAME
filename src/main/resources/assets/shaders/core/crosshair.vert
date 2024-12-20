#version 400 core

#import <color.glsl>

in vec2 vertex_position;

void main() {
    gl_Position = vec4(vertex_position, 0.0, 1.0);
}