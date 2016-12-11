package com.au.dius.bowling.frame;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Test for strike frame.
 */
public class StrikeTest {

    private Frame frame;

    @Before
    public void setUp(){
        frame = new Strike();
    }


    @Test
    public void testGetRolls(){
        assertEquals(Arrays.asList(10),frame.getRolls());
    }

    @Test
    public void testItWorksWithoutSuccessors(){
        assertEquals(10,frame.getScore());
    }

    @Test
    public void testItWorksWithOneStrikeSuccessor(){
        frame.addSuccessor(new Strike());
        assertEquals(20,frame.getScore());
    }

    @Test
    public void testItWorksWithTwoStrikeSuccessors(){
        frame.addSuccessor(new Strike());
        frame.addSuccessor(new Strike());
        assertEquals(30,frame.getScore());
    }

    @Test
    public void testItWorksWithTwoNormalSuccessors(){
        frame.addSuccessor(new OpenFrame(1,5));
        frame.addSuccessor(new OpenFrame(4,3));
        assertEquals(16,frame.getScore());
    }

    @Test
    public void testItWorksWithStrikeAndOpen(){
        frame.addSuccessor(new Strike());
        frame.addSuccessor(new OpenFrame(4,3));
        assertEquals(24,frame.getScore());
    }
}
