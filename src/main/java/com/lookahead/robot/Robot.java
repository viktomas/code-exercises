package com.lookahead.robot;

/**
 * Simple toy robot interface.
 */
public interface Robot {
    /**
     * Return robots coordinate on the table.
     *
     * @return coordinate on the table
     * @return null if robot isn't placed on the table
     */
    Coordinate getCurrentCoordinate();

    /**
     * Returns direction which is robot facing.
     *
     * @return direction which is robot facing
     */
    Direction getDirection();
}
