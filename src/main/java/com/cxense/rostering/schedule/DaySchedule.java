package com.cxense.rostering.schedule;

/**
 * Created by vicek on 8/21/14.
 */
public class DaySchedule {
    private Shift earlyShift;
    private Shift lateShift;

    public DaySchedule(Shift earlyShift, Shift lateShift) {
        this.earlyShift = earlyShift;
        this.lateShift = lateShift;
    }

    public Shift getEarlyShift() {
        return earlyShift;
    }

    public Shift getLateShift() {
        return lateShift;
    }
}
