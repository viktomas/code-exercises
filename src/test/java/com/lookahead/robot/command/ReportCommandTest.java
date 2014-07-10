package com.lookahead.robot.command;


import com.lookahead.robot.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Tests proper function of report command();
 */
public class ReportCommandTest {

    /**
     * Mock output object
     */
    TestUtils.StringOutput stringOutput;

    @Before
    public void init(){
        stringOutput = new TestUtils.StringOutput();
    }


    @Test
    public void testValidCommand(){
        Robot robot = new SimpleRobot();
        ReportCommand cmd = new ReportCommand(robot,stringOutput);
        assertFalse(cmd.isValid());
        robot = CommandTestHelper.getRobotOnTheTable();
        cmd = new ReportCommand(robot,stringOutput);
        assertTrue(cmd.isValid());
    }

    @Test
    public void testProperOutput(){
        Robot robot = CommandTestHelper.getRobotOnTheTable();
        ReportCommand cmd = new ReportCommand(robot,stringOutput);
        cmd.execute();
        assertEquals("Command output is wrong!", "0,0,NORTH", stringOutput.getLine());
        robot.setCoordinate(new Coordinate(3,2));
        robot.setDirection(Direction.SOUTH);
        cmd.execute();
        assertEquals("Command output is wrong!", "3,2,SOUTH", stringOutput.getLine());
    }

}
