package com.lookahead.robot.parser;

import com.lookahead.robot.Robot;
import com.lookahead.robot.Table;
import com.lookahead.robot.io.Output;
import com.lookahead.robot.lexer.Lexer;

/**
 * Parser understand Lexer's output (raw commands) and creates actual commands
 * containing business logic. It works like a Client in command design pattern.
 */
public interface Parser {

    /**
     * Sets Lexer for command input.
     * @param lexer lexer for command input.
     */
    void setLexer(Lexer lexer);

    /**
     * Sets robot as a receiver of commands.
     *
     * @param robot robot who receives commands
     */
    void setRobot(Robot robot);

    /**
     * Set output for commands which use it.
     *
     * @param output output for commands
     */
    void setOutput(Output output);

    /**
     * Sets table on which robot is placed with PLACE command.
     *
     * @param table table for a robot to be placed
     */
    void setTable(Table table);

    /**
     * Starts fetching commands from lexer and executing them.
     */
    void run();
}
