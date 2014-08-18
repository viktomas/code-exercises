package com.au.dius.bowling.frame;

import java.util.List;

/**
 * Represents one frame in scoreboard.
 */
public interface Frame {
    /**
     * Computes score. If it is a strike or spare, score can be incomplete(if all successors are not present).
     *
     * @return score of the frame
     */
    int getScore();

    /**
     * Adds successor frame to this frame. It is used for strikes and spares.
     *
     * @param frame successor frame. Order dependent! if there are frames A, B, C, and this is frame A,
     *              #addSuccessor has to be called in following order:
     *              <code>
     *              A.addSuccessor(B)
     *              A.addSuccessor(C)
     *              </code>
     */
    void addSuccessor(Frame frame);

    /**
     * List of rolls in this frame (can have 1 or 2 members)
     *
     * @return List of rolls in this frame (can have 1 or 2 members)
     */
    List<Integer> getRolls();
}
