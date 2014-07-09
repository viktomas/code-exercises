package com.lookahead.robot;

import com.lookahead.robot.Coordinate;
import com.lookahead.robot.Direction;
import com.lookahead.robot.Robot;
import com.lookahead.robot.Table;
import org.apache.commons.lang3.Validate;

/**
 * Simple implementation of Robot interface. It works basically as representation of robots state and nothing else.
 */
public class SimpleRobot implements Robot {

    private Coordinate currentCoordinate;
    private Direction direction;
    private Table table;


    @Override
    public Coordinate getCoordinate() {
        return currentCoordinate;
    }

    /**
     * Sets coordinate on the table to the robot. Can't be set if robot is not on a table.
     *
     * @param coordinate new robots coordinate.
     */
    @Override
    public void setCoordinate(Coordinate coordinate) {
        Validate.notNull(table);
        Validate.notNull(coordinate);
        this.currentCoordinate = coordinate;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void setDirection(Direction direction) {
        Validate.notNull(direction);
        this.direction = direction;
    }

    @Override
    public void putOnTable(Table table) {
        this.table = table;
    }

    @Override
    public Table getTable() {
        return table;
    }
}
