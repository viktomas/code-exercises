package com.cxense.rostering.io;


import com.cxense.rostering.io.exception.InputException;

import java.io.*;

/**
 * Input from file.
 */
public class FileInput implements Input {

    /**
     * Reader used for reading file.
     */
    private BufferedReader reader;

    /**
     * Constructor initializes a reader from a file.
     * @param file file which will be read
     */
    public FileInput(File file){
        try {
            reader = new BufferedReader(new FileReader(file));
        }catch(FileNotFoundException e){
            throw new InputException("Given file doesn't exist.");
        }
    }

    @Override
    public String readLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new InputException("Error during reading file.",e);
        }
    }

    @Override
    public void close() {
        try {
            reader.close();
        } catch (IOException e) {
            throw new InputException("Underlying file reader couldn't be closed");
        }
    }
}
