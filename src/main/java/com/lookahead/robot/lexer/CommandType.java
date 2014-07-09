package com.lookahead.robot.lexer;

/**
 * Token which is used to determine command type.
 * This is basic output of lexer.
 */
public enum CommandType {
    /**
     * Puts robot on a table. Requires 3 arguments(Integer x coordinate,
     * Integer y coordinate, Direction direction robot is facing)
     */
    PLACE,
    /**
     * Robot will make one step in its current direction if it's possible.
     */
    MOVE,
    /**
     * Robot will change his direction 90 degrees to the left
     */
    LEFT,
    /**
     * Robot will change his direction 90 degrees to the right
     */
    RIGHT,
    /**
     * Robot will return his coordinates on the table and direction it is facing to the output.
     */
    REPORT
}
