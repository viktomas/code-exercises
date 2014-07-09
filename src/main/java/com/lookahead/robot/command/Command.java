package com.lookahead.robot.command;

/**
 * Command implementations contain all business logic.
 */
public interface Command {

    /**
     * Executes command.
     */
    void execute();

    boolean isValid();
}
