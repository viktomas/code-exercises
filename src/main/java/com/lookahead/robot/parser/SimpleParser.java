package com.lookahead.robot.parser;

import com.lookahead.robot.Coordinate;
import com.lookahead.robot.Direction;
import com.lookahead.robot.Robot;
import com.lookahead.robot.Table;
import com.lookahead.robot.command.*;
import com.lookahead.robot.io.Output;
import com.lookahead.robot.lexer.Lexer;
import com.lookahead.robot.lexer.RawCommand;
import org.apache.commons.lang3.Validate;

import java.util.List;

/**
 * Simple implementation of commands parser. It takes tokens (raw commands) from lexer
 * and creates actual commands from them. In this implementation it works not just as
 * Client, but also as Invoker (Command design pattern). Reason why separate Invoker
 * isn't used is explained below.
 */
public class SimpleParser implements Parser {

    /**
     * Lexer as a source of raw commands
     */
    private Lexer lexer;
    /**
     * Robot as a Reciever of commands
     */
    private Robot robot;
    /**
     * Output used with Report command
     */
    private Output output;
    /**
     * Table on which is robot placed with first valid PLACE command.
     */
    private Table table;

    @Override
    public void setLexer(Lexer lexer) {
        this.lexer = lexer;
    }

    @Override
    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void setOutput(Output output) {
        this.output = output;
    }

    @Override
    public void setTable(Table table) {
        this.table = table;
    }

    /**
     * Method iterates through whole input and converts tokens (raw commands)
     * into actual commands which it also executes.
     */
    @Override
    public void run() {
        Validate.notNull(lexer);
        Validate.notNull(robot);
        Validate.notNull(output);
        Validate.notNull(table);
        try{
            RawCommand rawCommand;
            while((rawCommand = lexer.fetchCommand())!= null) {
                Command cmd = parseRawCommand(rawCommand);
                /* here we are excluding Invoker from command pattern,
                because there would be one class with one method with
                one line calling cmd.execute. It can be easily added in
                the feature*/
                cmd.execute();
            }
        }finally{
            // We always want to close underlying Streams.
            lexer.closeInput();
            output.close();
        }

    }

    /**
     * This method converts raw command into actual command.
     *
     * @param rawCommand raw command
     * @return actual command which can be then executed
     */
    private Command parseRawCommand(RawCommand rawCommand) {
        switch (rawCommand.getCommandType()){
            case PLACE: return buildPlaceCommand(rawCommand.getArgs());
            case MOVE: return new MoveCommand(robot);
            case LEFT: return new TurnLeftCommand(robot);
            case RIGHT: return new TurnRightCommand(robot);
            case REPORT: return new ReportCommand(robot,output);
            case INVALID: return Command.VOID;
        }
        throw new IllegalArgumentException("Raw command without known type was passed.");
    }

    /**
     * Helper method for building place command from its arguments. It's only dirty method
     * because it uses force typing of objects. The only source of those arguments is Lexer.
     * And Lexer gives us arguments in required order and required type.
     *
     * @param args list of arguments for place command
     * @return place command which can be executed.
     */
    private Command buildPlaceCommand(List<Object> args) {
        Integer x = (Integer) args.get(0);
        Integer y = (Integer) args.get(1);
        Direction direction = (Direction) args.get(2);
        return new PlaceCommand(table,robot,new Coordinate(x,y),direction);
    }
}
