package com.au.dius.bowling;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by vicek on 8/18/14.
 */
public class BowlingGameTest {

    /**
     * Game copied from http://bowling.about.com/od/rulesofthegame/a/bowlingscoring.htm
     * Frame: 	        1 	2 	3 	4 	5 	6 	7 	8 	9 	10
     * Result: 	        X 	7/ 	7 2 9/ 	X 	X 	X 	2 3 6/ 	7/3
     * Frame Score: 	20 	17 	9 	20 	30 	22 	15 	5 	17 	13
     * Running Total: 	20 	37 	46 	66 	96 	118	133 138 155 168
     */
    @Test
    public void testWholeGame1(){
        BowlingGame game = new BowlingGame();
        game.roll(10);
        assertEquals(10,game.score());
        game.roll(7);
        game.roll(3);
        assertEquals(30,game.score());
        game.roll(7);
        game.roll(2);
        assertEquals(46,game.score());
        game.roll(9);
        game.roll(1);
        assertEquals(56,game.score());
        game.roll(10);
        assertEquals(76,game.score());
        game.roll(10);
        assertEquals(96,game.score());
        game.roll(10);
        assertEquals(126,game.score());
        game.roll(2);
        game.roll(3);
        assertEquals(138,game.score());
        game.roll(6);
        game.roll(4);
        assertEquals(148,game.score());
    }
}
