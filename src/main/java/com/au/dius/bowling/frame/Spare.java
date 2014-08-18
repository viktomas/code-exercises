package com.au.dius.bowling.frame;

import org.apache.commons.lang3.Validate;

import java.util.Arrays;
import java.util.List;

/**
 * Represents frame with two rolls and sum of them equal to 10.
 */
public class Spare implements Frame {

    /**
     * First roll
     */
    private int first;
    /**
     * Second roll
     */
    private int second;

    /**
     * Spare needs information about one successor frame.
     */
    private Frame successor;

    public Spare(int first, int second) {
        Validate.isTrue(first+second==10);
        this.first = first;
        this.second = second;
        successor = EmptyFrame.INSTANCE;
    }

    /**
     * Score equals 10 + first roll from successor frame
     * @return score of this frame
     */
    @Override
    public int getScore() {
        return 10 + successor.getRolls().get(0);
    }

    @Override
    public void addSuccessor(Frame frame) {
        // only if successor wasn't assigned before
        if(EmptyFrame.INSTANCE.equals(successor)){
            successor = frame;
        }
    }

    @Override
    public List<Integer> getRolls() {
        return Arrays.asList(first, second);
    }
}
