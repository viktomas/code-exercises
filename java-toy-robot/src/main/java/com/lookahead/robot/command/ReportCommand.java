package com.lookahead.robot.command;

import com.lookahead.robot.Coordinate;
import com.lookahead.robot.Direction;
import com.lookahead.robot.Robot;
import com.lookahead.robot.io.Output;

/**
 * Command which reports robots position and direction on table.
 */
public class ReportCommand implements Command {

    /**
     * Robot who is "receiving" this command
     */
    private Robot robot;

    /**
     * Output on which report will be written
     */
    private Output output;

    /**
     * Command is built with robot and output variables. It will be executed on robot and
     * result will be written on output.
     *
     * @param robot robot on which command is executed
     * @param output output to which result of this command will be written.
     */
    public ReportCommand(Robot robot, Output output) {
        this.robot = robot;
        this.output = output;
    }

    @Override
    public void execute() {
        if(isValid()){
            Coordinate coordinate = robot.getCoordinate();
            Direction direction = robot.getDirection();
            StringBuilder line = new StringBuilder();
            line.append(coordinate.getX()).append(",")
                .append(coordinate.getY()).append(",")
                .append(direction.getName());
            output.writeLine(line.toString());
        }
    }

    /**
     * Command is valid if robot has been already placed on the table.
     *
     * @return is possible to execute command?
     */
    @Override
    public boolean isValid() {
        return robot.getTable() != null;
    }
}
