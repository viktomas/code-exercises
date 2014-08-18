package com.au.dius.bowling.frame;

/**
 * New type of factory which handles special case of game end.
 */
public class FinishFrameFactory extends SimpleFrameFactory{

    /**
     * How many rolls after end we have.
     */
    private int rollCount;

    /**
     * We compute how many rolls we have left from last (tenth) frame.
     *
     * @param lastFrame last frame.
     */
    public FinishFrameFactory(Frame lastFrame) {
        if(lastFrame.getScore()==10){
            if(lastFrame.getRolls().size()==1){
                rollCount=2;
            }else{
                rollCount=1;
            }
        }else{
            rollCount = 0;
        }
    }

    @Override
    public void roll(int roll) {
        if(rollCount == 0 && rolls.isEmpty()){
            //game ended, nothing to do here.
            return;
        }
        super.roll(roll);
        rollCount--;
    }

    /**
     * Now it can be ready even if there is just one non-10 roll
     *
     * @return is factory ready to create?
     */
    @Override
    public boolean isReady() {
        return super.isReady() || (rollCount == 0 && !rolls.isEmpty());
    }

    /**
     * Now it creates OpenFrame even from one roll if it is necessary.
     * @see SimpleFrameFactory#createFrame()
     */
    @Override
    public Frame createFrame() {
        if (rollCount == 0 && rolls.size() == 1 && !strike()) {
            Frame result = new OpenFrame(rolls.get(0), 0);
            rolls.clear();
            return result;
        } else {
            return super.createFrame();
        }
    }
}
