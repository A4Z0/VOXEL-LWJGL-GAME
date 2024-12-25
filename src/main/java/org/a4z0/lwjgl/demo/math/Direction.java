package org.a4z0.lwjgl.demo.math;

/**
* Represents directions in a three-dimensional space.
*/

public enum Direction {
    NORTH,
    SOUTH,
    EAST,
    WEST,
    TOP,
    BOTTOM;

    /**
    * @return the opposite {@link Direction} to this.
    */

    public Direction opposite() {
        return switch (this) {
            case NORTH ->
                SOUTH;
            case SOUTH ->
                NORTH;
            case EAST ->
                WEST;
            case WEST ->
                EAST;
            case TOP ->
                BOTTOM;
            case BOTTOM ->
                TOP;
        };
    }
}