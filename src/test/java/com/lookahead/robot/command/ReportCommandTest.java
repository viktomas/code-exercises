package com.lookahead.robot.command;


import com.lookahead.robot.Coordinate;
import com.lookahead.robot.Direction;
import com.lookahead.robot.Robot;
import com.lookahead.robot.SimpleRobot;
import com.lookahead.robot.io.Output;
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
    StringOutput stringOutput;

    @Before
    public void init(){
        stringOutput = new StringOutput();
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


    /**
     * Test output class used as mock for testing report command.
     */
    private static class StringOutput implements Output {

        /**
         * Variable holding written output.
         */
        private String line;

        @Override
        public void writeLine(String line) {
            this.line = line;
        }

        @Override
        public void close() {

        }

        /**
         * Gets written output.
         * @return
         */
        public String getLine(){
            return line;
        }
    }
}
