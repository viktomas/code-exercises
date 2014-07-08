package com.lookahead.robot;

/**
 * Atomic immutable class for holding coordinates;
 */
public final class Coordinate {
    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    Coordinate addCoordinate(Coordinate addition){
        int newX = x + addition.getX();
        int newY = y + addition.getY();
        return new Coordinate(newX,newY);
    }
}
