package com.cxense.rostering.parsing;

import com.cxense.rostering.io.FileInput;
import com.cxense.rostering.io.Input;
import org.junit.Test;

import java.io.File;

/**
 * Created by vicek on 8/21/14.
 */
public class SimpleParserTest {

    @Test
    public void testParsing(){
        Input input = new FileInput(new File("spec-example.csv"));
        SimpleParser parser = new SimpleParser();
        parser.setInput(input);
        Parser.Product product = parser.parse();
    }
}
