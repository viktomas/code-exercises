package com.au.dius.bowling.frame;

import org.apache.commons.lang3.Validate;

import java.util.Arrays;
import java.util.List;

/**
 * Represents frame with two throws and less then 10 hit pins total.
 */
public class OpenFrame implements Frame{

    /**
     * First roll
     */
    private int first;
    /**
     * Second roll
     */
    private int second;

    /**
     * Creates Open Frame
     * @param first first roll
     * @param second second roll
     */
    public OpenFrame(int first, int second){
        Validate.inclusiveBetween(0,NMB_OF_PINS-1,first+second);
        this.first = first;
        this.second = second;
    }

    @Override
    public int getScore() {
        return first+second;
    }

    @Override
    public void addSuccessor(Frame frame) {
        //OpenFrame doesn't use successor frames to compute score
    }

    @Override
    public List<Integer> getRolls() {
        return Arrays.asList(first,second);
    }
}
