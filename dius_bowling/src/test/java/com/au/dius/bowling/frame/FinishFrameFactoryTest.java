package com.au.dius.bowling.frame;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Test of factory which handles end of the game.
 */
public class FinishFrameFactoryTest {

    private FinishFrameFactory factory;

    @Test
    public void testItDoesNothingIfLastFrameIsOpen(){
        factory = new FinishFrameFactory(new OpenFrame(1,2));
        assertFalse(factory.isReady());
        factory.roll(5);
        assertFalse(factory.isReady());
        factory.roll(5);
        assertFalse(factory.isReady());
    }

    @Test
    public void testItTakesOneRollIfLastFrameIsSpare(){
        factory = new FinishFrameFactory(new Spare(3,7));
        factory.roll(5);
        assertTrue(factory.isReady());
        Frame frame = factory.createFrame();
        assertEquals(Arrays.asList(5,0), frame.getRolls());
    }

    @Test
    public void testItTakesTwoRollsIfLastFrameIsStrike(){
        factory = new FinishFrameFactory(new Strike());
        factory.roll(10);
        assertTrue(factory.isReady());
        Frame frame = factory.createFrame();
        assertEquals(10,frame.getScore());
        assertFalse(factory.isReady());
        factory.roll(5);
        assertTrue(factory.isReady());
        frame = factory.createFrame();
        assertEquals(Arrays.asList(5,0), frame.getRolls());
    }

    @Test
    public void testTheBestThrowEver(){
        factory = new FinishFrameFactory(new Strike());
        factory.roll(10);
        assertTrue(factory.isReady());
        Frame frame = factory.createFrame();
        assertEquals(10,frame.getScore());
        assertFalse(factory.isReady());
        factory.roll(10);
        assertTrue(factory.isReady());
        frame = factory.createFrame();
        assertEquals(10,frame.getScore());
    }

    @Test
    public void testItDoesNothingAfterDepletingRollCount(){
        factory = new FinishFrameFactory(new Spare(3,7));
        factory.roll(5);
        Frame frame = factory.createFrame();
        assertFalse(factory.isReady());
        factory.roll(3);
        assertFalse(factory.isReady());
        factory.roll(3);
        assertFalse(factory.isReady());
    }
}
