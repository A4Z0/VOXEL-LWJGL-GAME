#version 120

vec3 normalize_voxel_position(vec3 position) {
    return position * 0.125;
}

mat4 normalize_voxel_matrix(mat4 matrix) {
    matrix[3].xyz *= 0.125;

    return matrix;
}