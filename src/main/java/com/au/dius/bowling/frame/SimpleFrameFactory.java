package com.au.dius.bowling.frame;

import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vicek on 8/18/14.
 */
public class SimpleFrameFactory implements FrameFactory {

    /**
     * rolls for creating frames
     */
    protected List<Integer> rolls;

    public SimpleFrameFactory() {
        rolls = new ArrayList<>();
    }

    @Override
    public void roll(int roll){
        Validate.isTrue(!isReady());
        Validate.inclusiveBetween(0,10,roll);
        rolls.add(roll);
    }

    @Override
    public boolean isReady(){
        return rolls.size()==2 || strike();
    }

    @Override
    public Frame createFrame(){
        Validate.isTrue(isReady());
        Frame resultFrame;
        if (strike()) {
            resultFrame = new Strike();
        }else if(rollsSum() == Frame.NMB_OF_PINS){
            resultFrame = new Spare(rolls.get(0),rolls.get(1));
        }else{
            resultFrame = new OpenFrame(rolls.get(0),rolls.get(1));
        }
        rolls = new ArrayList<>();
        return resultFrame;
    }

    /**
     * Is state of this factory strike?
     *
     * @return Is state of this factory strike?
     */
    protected boolean strike() {
        return rolls.size() == 1 && rolls.get(0) == Frame.NMB_OF_PINS;
    }

    /**
     * Returns sum of rolls score.
     *
     * @return sum of rolls score.
     */
    private int rollsSum() {
        int sum = 0;
        for (Integer roll : rolls) {
            sum += roll;
        }
        return sum;
    }
}
