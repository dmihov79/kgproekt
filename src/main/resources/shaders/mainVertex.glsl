#version 330 core

layout(location = 0) in vec3 position;

out vec3 outcolor;

void main() {
    gl_Position = vec4(position, 1.0);
    outcolor = vec3(position.x, 0.0, position.y);
}