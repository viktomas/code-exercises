package com.au.dius.bowling.frame;

import java.util.Arrays;
import java.util.List;

/**
 * Singleton of empty frame used to avoid null,
 */
public enum EmptyFrame implements Frame {
    /**
     * Singleton instance
     */
    INSTANCE;

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public void addSuccessor(Frame frame) {

    }

    /**
     * Returns array with zero scores.
     * @return array with zero scores.
     */
    @Override
    public List<Integer> getRolls() {
        return Arrays.asList(0,0);
    }
}
