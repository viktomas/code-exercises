package com.cxense.rostering.pool;

/**
 * Created by vicek on 8/21/14.
 */
public class DayPool {
    private ShiftPool earlyShift;
    private ShiftPool lateShift;

    public ShiftPool getEarlyShift() {
        return earlyShift;
    }

    public void setEarlyShift(ShiftPool earlyShift) {
        this.earlyShift = earlyShift;
    }

    public ShiftPool getLateShift() {
        return lateShift;
    }

    public void setLateShift(ShiftPool lateShift) {
        this.lateShift = lateShift;
    }
}
