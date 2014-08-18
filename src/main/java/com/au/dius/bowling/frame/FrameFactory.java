package com.au.dius.bowling.frame;

/**
 * Created by vicek on 8/18/14.
 */
public interface FrameFactory {
    /**
     * If factory is not isReady, it adds new roll to it.
     *
     * @param roll players roll
     */
    void roll(int roll);

    /**
     * Is factory isReady to produce Frame?
     *
     * @return can we produce frame?
     */
    boolean isReady();

    /**
     * Creates new Frame from internal state and resets that state so factory is isReady for new use.
     *
     * @return frame created from internal state
     */
    Frame createFrame();
}
