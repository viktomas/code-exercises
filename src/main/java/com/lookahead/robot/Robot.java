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
    Coordinate getCoordinate();

    /**
     * Sets new coordinate to the robot.
     *
     * @param coordinate new robots coordinate. Can't be null
     */
    void setCoordinate(Coordinate coordinate);

    /**
     * Returns direction which is robot facing.
     *
     * @return direction which is robot facing
     */
    Direction getDirection();

    /**
     * Sets new direction to the robot.
     *
     * @param direction  new robot's direction.
     */
    void setDirection(Direction direction);

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
