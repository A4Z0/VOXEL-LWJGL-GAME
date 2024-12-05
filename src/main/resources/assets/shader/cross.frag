#version 400 core

out vec4 fragment_color;

void main() {
    fragment_color = vec4(1.0 - gl_FragCoord.x / 1920.0, 1.0 - gl_FragCoord.y / 1080.0, 1.0, 1.0);
}