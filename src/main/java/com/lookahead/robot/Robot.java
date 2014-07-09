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

    /**
     * Puts robot on given table.
     *
     * @param table table on which robot is put on
     */
    void putOnTable(Table table);

    /**
     * Returns table on which robot is placed or null, if robot hasn't been placed on table yet.
     *
     * @return table on which robot is placed
     * @return null if robot hasn't been placed on a table yet
     */
    Table getTable();
}
