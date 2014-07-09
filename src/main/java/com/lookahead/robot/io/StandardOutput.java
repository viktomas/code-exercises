package com.lookahead.robot.io;

import com.lookahead.robot.io.exception.OutputException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Output writing to standard system output.
 */
public class StandardOutput implements Output {

    /**
     * Writer writing to standard input.
     */
    private BufferedWriter writer;

    /**
     * Constructor initializes the writer.
     */
    public StandardOutput() {
        this.writer = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    @Override
    public void writeLine(String line) {
        try {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            throw new OutputException("Error during write to standard output", e);
        }

    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            throw new OutputException("Underlying writer couldn't be properly closed.",e);
        }
    }
}
