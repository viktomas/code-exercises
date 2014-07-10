package com.lookahead.robot.command;

import com.lookahead.robot.Direction;
import com.lookahead.robot.Robot;
import com.lookahead.robot.SimpleRobot;
import com.lookahead.robot.SimpleTable;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Tests abstract class for turning robot.
 */
public class TurnCommandTest {

    @Test
    public void testValidCommand(){
        Robot robot = new SimpleRobot();
        TurnCommand turnCommand = new TurnCommand(robot) {
            @Override
            protected Map<Direction, Direction> nextDirectionMap() {
                return null;
            }
        };
        assertFalse(turnCommand.isValid());
        robot.putOnTable(new SimpleTable(3,3));
        assertTrue(turnCommand.isValid());
    }
}
