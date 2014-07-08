package com.lookahead.robot;

/**
 * Generic table on which robot can be placed.
 */
public interface Table {
    /**
     * Height of the table
     * @return height of the table
     */
    int getHeight();

    /**
     * Width of the table
     * @return width of the table
     */
    int getWidth();

    /**
     * Returns robot if there is any. Otherwise returns null.
     * @return null if there is no robot
     * @return robot, if there is any
     */
    Robot getRobot();

    /**
     * Is there a robot on the table?
     * @return is there a robot on the table
     */
    boolean hasRobot();

    /**
     * Puts robot on the table.
     * @param robot robot which should be put on the table
     */
    void setRobot(Robot robot);
}
