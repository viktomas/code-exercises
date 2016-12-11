package com.au.dius.bowling.frame;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Test for spare frame class.
 */
public class SpareTest {

    @Test
    public void testCanBeCreated(){
        Frame frame = new Spare(9,1);
    }

    @Test
    public void testGetRolls(){
        Frame frame = new Spare(7,3);
        assertEquals(Arrays.asList(7,3), frame.getRolls());
    }

    @Test
    public void testThatItWorksWithoutSuccessor(){
        Frame frame = new Spare(4,6);
        assertEquals(10,frame.getScore());
    }

    @Test
    public void testThatItUsesSuccessor(){
        Frame frame = new Spare(3,7);
        frame.addSuccessor(new Spare(6,4));
        assertEquals(frame.getScore(),16);
    }

    @Test
    public void testThatItAssignsSuccessorJustOnce(){
        Frame frame = new Spare(3,7);
        frame.addSuccessor(new Spare(6,4));
        frame.addSuccessor(new Spare(1,9));
        assertEquals(frame.getScore(), 16);
    }
}
