package com.au.dius.bowling;

import com.au.dius.bowling.frame.FinishFrameFactory;
import com.au.dius.bowling.frame.Frame;
import com.au.dius.bowling.frame.FrameFactory;
import com.au.dius.bowling.frame.SimpleFrameFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Main bowling library class representing one bowling game for one player.
 */
public class BowlingGame {

    private List<Frame> frames;
    private FrameFactory factory;

    public BowlingGame() {
        frames = new ArrayList<>();
        factory = new SimpleFrameFactory();
    }

    /**
     * Add one player's bowl.
     *
     * @param noOfPins how many pins were hit
     */
    public void roll(int noOfPins){
        factory.roll(noOfPins);
        if (factory.isReady()) {
            Frame frame = factory.createFrame();
            addSuccessor(frame);
            addToFrames(frame);
            //if this is end of regular game, it switches factory
            if(isEnding()){
                factory = new FinishFrameFactory(frame);
            }
        }

    }

    /**
     * Returns current score of the game.
     * @return current score of the game
     */
    public int score(){
        int sum = 0;
        for(Frame frame : frames){
            sum += frame.getScore();
        }
        return sum;
    }

    /**
     * Adds this frame as a successor to all previous frames.
     * Frames, which doesn't need it just forget it. Little performance trade for less special cases.
     *
     * @param successor new successor frame
     */
    private void addSuccessor(Frame successor){
        for(Frame frame :frames){
            frame.addSuccessor(successor);
        }
    }

    /**
     * Is regular number of frames already depleted?
     * @return is right now the game ending?
     */
    private boolean isEnding(){
        return frames.size() == 10;
    }

    /**
     * This method is there because of finish of the game, when frames over 10 are not added to the list
     * @param frame
     */
    private void addToFrames(Frame frame){
        if(frames.size()<10){
            frames.add(frame);
        }
    }
}
