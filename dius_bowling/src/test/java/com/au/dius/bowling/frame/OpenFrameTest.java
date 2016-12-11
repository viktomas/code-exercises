package com.au.dius.bowling.frame;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Test for opne frame class
 */
public class OpenFrameTest {

    @Test
    public void testCanBeCreated(){
        Frame f = new OpenFrame(0,5);
    }

    @Test
    public void testGetRolls(){
        Frame f = new OpenFrame(1,8);
        assertEquals("#getRolls doesn't return proper list", Arrays.asList(1,8),f.getRolls());
    }
}
