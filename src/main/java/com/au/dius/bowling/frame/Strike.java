package com.au.dius.bowling.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Frame representing strike.
 */
public class Strike implements Frame {

    /**
     * List of strikes 2 successors
     */
    private List<Frame> successors;

    /**
     * Constructor doesn't need any parameter (always 10 pins)
     */
    public Strike() {
        successors = new ArrayList<>();
    }

    /**
     * Algorithm takes next 2 rolls to compute score from.
     * @return score of this frame
     */
    @Override
    public int getScore() {
        int score = NMB_OF_PINS;

        List<Integer> successorRolls = new ArrayList<>();
        // get all successor rolls
        for(Frame successor : successors){
            successorRolls.addAll(successor.getRolls());
        }
        // separate important to us
        List<Integer> importantRolls = successorRolls.subList(0,Math.min(2,successorRolls.size()));

        for(Integer roll : importantRolls){
            score += roll;
        }
        return score;
    }

    /**
     * Adds maximum of two successors.
     */
    @Override
    public void addSuccessor(Frame frame) {
        if(successors.size()<2){
            successors.add(frame);
        }
    }

    @Override
    public List<Integer> getRolls() {
        return Collections.singletonList(NMB_OF_PINS);
    }


}
