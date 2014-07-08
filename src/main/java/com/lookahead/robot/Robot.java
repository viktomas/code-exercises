package com.lookahead.robot;

/**
 * Simple toy robot interface.
 */
public interface Robot {
    /**
     * Return robots x coordinate on the table.
     *
     * @return x coordinate
     */
    int getX();

    /**
     * Return robots y coordinate on the table.
     *
     * @return y coordinate
     */
    int getY();

    /**
     * Returns direction which is robot facing.
     *
     * @return direction which is robot facing
     */
    Direction getDirection();
}
