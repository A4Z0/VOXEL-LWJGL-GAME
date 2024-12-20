package org.a4z0.lwjgl.demo.face;

import org.a4z0.lwjgl.demo.math.vector.Vector3f;
import org.a4z0.lwjgl.demo.util.ByteBuf;

public class FaceGenerator {

    public static final Vector3f[] NORTH = new Vector3f[]{
        new Vector3f(1f, 1f, 0f),
        new Vector3f(1f, 0f, 0f),
        new Vector3f(0f, 0f, 0f),
        new Vector3f(0f, 0f, 0f),
        new Vector3f(0f, 1f, 0f),
        new Vector3f(1f, 1f, 0f)
    };

    public static final Vector3f[] SOUTH = new Vector3f[]{
        new Vector3f(0f, 1f, 1f),
        new Vector3f(0f, 0f, 1f),
        new Vector3f(1f, 0f, 1f),
        new Vector3f(1f, 0f, 1f),
        new Vector3f(1f, 1f, 1f),
        new Vector3f(0f, 1f, 1f)
    };

    public static final Vector3f[] EAST = new Vector3f[]{
        new Vector3f(1f, 1f, 1f),
        new Vector3f(1f, 0f, 1f),
        new Vector3f(1f, 0f, 0f),
        new Vector3f(1f, 0f, 0f),
        new Vector3f(1f, 1f, 0f),
        new Vector3f(1f, 1f, 1f)
    };

    public static final Vector3f[] WEST = new Vector3f[]{
        new Vector3f(0f, 1f, 0f),
        new Vector3f(0f, 0f, 0f),
        new Vector3f(0f, 0f, 1f),
        new Vector3f(0f, 0f, 1f),
        new Vector3f(0f, 1f, 1f),
        new Vector3f(0f, 1f, 0f)
    };

    public static final Vector3f[] TOP = new Vector3f[]{
        new Vector3f(0f, 1f, 0f),
        new Vector3f(0f, 1f, 1f),
        new Vector3f(1f, 1f, 1f),
        new Vector3f(1f, 1f, 1f),
        new Vector3f(1f, 1f, 0f),
        new Vector3f(0f, 1f, 0f)
    };

    public static final Vector3f[] BOTTOM = new Vector3f[]{
        new Vector3f(0f, 0f, 1f),
        new Vector3f(0f, 0f, 0f),
        new Vector3f(1f, 0f, 0f),
        new Vector3f(1f, 0f, 0f),
        new Vector3f(1f, 0f, 1f),
        new Vector3f(0f, 0f, 1f)
    };

    public static final Vector3f[][] FACES = new Vector3f[][]{NORTH, SOUTH, EAST, WEST, TOP, BOTTOM};

    static {
        for(Vector3f[] Face : FACES) {
            for(Vector3f Vertex : Face) {
                Vertex.multiply(0.125f);
            }
        }
    }

    /**
    * ...
    *
    * @param buffer ...
    * @param i ...
    *
    * @return a new {@link FaceBuilder}.
    */

    public static FaceBuilder create(ByteBuf buffer, int i) {
        return (direction, x, y, z) -> {
            for(Vector3f vertex : FACES[direction.ordinal()]) {
                buffer.put(vertex.getX() + x).put(vertex.getY() + y).put(vertex.getZ() + z).put(i).put(0);
            }
        };
    }
}