package com.au.dius.bowling.frame;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Tests that frame factory produces frames properly
 */
public class FrameFactoryTest {

    private FrameFactory factory;

    @Before
    public void setUp(){
        factory = new FrameFactory();
    }

    @Test
    public void testItIsNotReadyAtStart(){
        assertFalse(factory.isReady());
    }

    @Test
    public void testItCanCreateStrike(){
        factory.roll(10);
        assertTrue(factory.isReady());
        Frame frame = factory.createFrame();
        assertEquals(10,frame.getScore());
        frame.addSuccessor(new OpenFrame(1,2));
        assertEquals(13,frame.getScore());
    }

    @Test
    public void testItCanCreateSpare(){
        factory.roll(8);
        factory.roll(2);
        assertTrue(factory.isReady());
        Frame frame = factory.createFrame();
        assertEquals(10,frame.getScore());
        frame.addSuccessor(new OpenFrame(1,2));
        assertEquals(11,frame.getScore());
    }

    @Test
    public void testItCanCreateOpen(){
        factory.roll(6);
        factory.roll(2);
        assertTrue(factory.isReady());
        Frame frame = factory.createFrame();
        assertEquals(8,frame.getScore());
        frame.addSuccessor(new OpenFrame(1,2));
        assertEquals(8,frame.getScore());
    }

    @Test
    public void testItIsNotReadyAfterOneRoll(){
        factory.roll(9);
        assertFalse(factory.isReady());
    }
}
