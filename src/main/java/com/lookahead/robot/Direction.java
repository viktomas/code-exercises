package com.lookahead.robot;

/**
 * Cardinal direction.
 */
public enum Direction {
    NORTH(0,1),
    EAST(1,0),
    SOUTH(0,-1),
    WEST(-1,0);

    /**
     * Single coordinate for showing direction on grid.
     */
    private final Coordinate stepAddition;

    /**
     * Constructor takes integer step showing direction on grid and creates enum.
     * @param x addition of X coordinate for this direction
     * @param y addition of Y coordinate for this direction
     */
    Direction(int x, int y) {
        this.stepAddition = new Coordinate(x,y);
    }

    /**
     * Returns coordinate representing direction on the grid.
     * You can use this coordinate to move on XY grid.
     *
     * @return coordinate representing one step in direction
     */
    public Coordinate getStepAddition() {
        return stepAddition;
    }
}
