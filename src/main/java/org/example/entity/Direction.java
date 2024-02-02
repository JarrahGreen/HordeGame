package org.example.entity;

public enum Direction {
    UP_LEFT,
    UP,
    UP_RIGHT,
    RIGHT,
    DOWN_RIGHT,
    DOWN,
    DOWN_LEFT,
    LEFT;

    public static Direction fromBooleans(boolean up, boolean right, boolean down, boolean left) {
        if (up) {
            if (right) {
                return UP_RIGHT;
            } else if (left) {
                return UP_LEFT;
            } else {
                return UP;
            }
        } else if (down) {
            if (right) {
                return DOWN_RIGHT;
            } else if (left) {
                return DOWN_LEFT;
            } else {
                return DOWN;
            }
        } else {
            if (right) {
                return RIGHT;
            } else if (left) {
                return LEFT;
            } else {
                return RIGHT;
            }
        }
    }

    public int getDeltaX() {
        return switch (this) {
            case UP_LEFT, LEFT, DOWN_LEFT -> -1;
            case UP_RIGHT, RIGHT, DOWN_RIGHT -> 1;
            default -> 0;
        };
    }

    public int getDeltaY() {
        return switch (this) {
            case UP_LEFT, UP, UP_RIGHT -> -1;
            case DOWN_LEFT, DOWN, DOWN_RIGHT -> 1;
            default -> 0;
        };
    }
}
