package com.lookahead.robot.command;

import com.lookahead.robot.Direction;
import com.lookahead.robot.Robot;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests TurnRightCommand.
 */
public class TurnRightCommandTest {

    @Test
    public void testProperTurning(){
        Robot robot = CommandTestHelper.getRobotOnTheTable();
        TurnRightCommand cmd = new TurnRightCommand(robot);
        cmd.execute();
        assertEquals("Robot should be facing EAST.", Direction.EAST, robot.getDirection());
        cmd.execute();
        assertEquals("Robot should be facing SOUTH.", Direction.SOUTH, robot.getDirection());
        cmd.execute();
        assertEquals("Robot should be facing WEST.", Direction.WEST, robot.getDirection());
        cmd.execute();
        assertEquals("Robot should be facing NORTH.", Direction.NORTH, robot.getDirection());
    }
}
