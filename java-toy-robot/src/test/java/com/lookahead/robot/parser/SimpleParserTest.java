package com.lookahead.robot.parser;

import com.lookahead.robot.*;
import com.lookahead.robot.io.Input;
import com.lookahead.robot.lexer.CommandType;
import com.lookahead.robot.lexer.Lexer;
import com.lookahead.robot.lexer.RawCommand;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Test SimpleParser class.
 */
public class SimpleParserTest {

    /**
     * Lexer as a source of raw commands
     */
    private MockLexer lexer;
    /**
     * Robot as a Reciever of commands
     */
    private Robot robot;
    /**
     * Output used with Report command
     */
    private TestUtils.StringOutput output;
    /**
     * Table on which is robot placed with first valid PLACE command.
     */
    private Table table;

    /**
     * Instance of tested class
     */
    private SimpleParser parser;

    @Before
    public void init(){
        lexer = new MockLexer();
        robot = new SimpleRobot();
        output = new TestUtils.StringOutput();
        table = new SimpleTable(4,4);
        parser = new SimpleParser();
        parser.setLexer(lexer);
        parser.setRobot(robot);
        parser.setOutput(output);
        parser.setTable(table);
    }

    /**
     * Testing that parser is capable of sucessfull assembly of place command
     */
    @Test
    public void testPlace(){
        lexer.setRawCommand(TestUtils.buildRawPlace(2,1,Direction.EAST));
        parser.run();
        assertEquals(table,robot.getTable());
        assertPositionAndDirection(2,1,Direction.EAST);
    }

    /**
     * Test that parser can assemble and execute move command
     */
    @Test
    public void testMove(){
        robot.putOnTable(table,new Coordinate(0,1),Direction.SOUTH);
        lexer.setRawCommand(new RawCommand(CommandType.MOVE));
        parser.run();
        assertPositionAndDirection(0,0,Direction.SOUTH);
        lexer.setRawCommand(new RawCommand(CommandType.MOVE));
        parser.run();
        //now we couldn't move
        assertPositionAndDirection(0,0,Direction.SOUTH);
    }

    /**
     * Test that parser can assemble and execute commands left and right.
     */
    @Test
    public void testTurning(){
        robot.putOnTable(table,new Coordinate(0,0),Direction.NORTH);
        //turn right
        lexer.setRawCommand(new RawCommand(CommandType.RIGHT));
        parser.run();
        assertPositionAndDirection(0,0,Direction.EAST);
        //turn left
        lexer.setRawCommand(new RawCommand(CommandType.LEFT));
        parser.run();
        //turn left again
        lexer.setRawCommand(new RawCommand(CommandType.LEFT));
        parser.run();
        assertPositionAndDirection(0,0,Direction.WEST);
    }

    /**
     * Test that parser works with reporting command
     */
    @Test
    public void testReporting(){
        robot.putOnTable(table,new Coordinate(3,1),Direction.WEST);
        lexer.setRawCommand(new RawCommand(CommandType.REPORT));
        parser.run();
        assertEquals("3,1,WEST", output.getLine());
    }

    /**
     * Test that nothing happen if we send in invalid command.
     */
    @Test
    public void testInvalid(){
        robot.putOnTable(table,new Coordinate(0,0),Direction.NORTH);
        //invalid token
        lexer.setRawCommand(new RawCommand(CommandType.INVALID));
        parser.run();
        assertPositionAndDirection(0,0,Direction.NORTH);
    }

    private void assertPositionAndDirection(int x, int y, Direction direction){
        assertEquals(new Coordinate(x,y),robot.getCoordinate());
        assertEquals(direction, robot.getDirection());
    }


    /**
     * Mock lexer class for providing RawCommnads to Parser
     */
    private static class MockLexer implements Lexer {

        /**
         * Raw command which is returned with first fetch();
          */
        private RawCommand rawCommand;

        @Override
        public void setInput(Input input) {

        }

        @Override
        public void closeInput() {

        }

        /**
         * Returns raw command and then null, so parser won't end up in infinite loop.
         * @return raw command and then null, so parser won't end up in infinite loop.
         */
        @Override
        public RawCommand fetchCommand() {
            RawCommand result = rawCommand;
            rawCommand = null;
            return result;
        }

        public void setRawCommand(RawCommand rawCommand) {
            this.rawCommand = rawCommand;
        }
    }

}
