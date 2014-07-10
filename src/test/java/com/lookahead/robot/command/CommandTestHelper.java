package com.lookahead.robot.command;

import com.lookahead.robot.*;

/**
 * Helper methods for command testing.
 */
public class CommandTestHelper {

    /**
     * Basic 4x4 table.
     */
    public static final Table TABLE_4X4 = new SimpleTable(4,4);

    /**
     * Returns robot on 4x4 table on location (0,0) facing NORTH.
     * @return robot on 4x4 table on location (0,0) facing NORTH.
     */
    public static Robot getRobotOnTheTable(){
        Robot robot = new SimpleRobot();
        robot.putOnTable(TABLE_4X4);
        robot.setCoordinate(new Coordinate(0,0));
        robot.setDirection(Direction.NORTH);
        return robot;
    }
}
