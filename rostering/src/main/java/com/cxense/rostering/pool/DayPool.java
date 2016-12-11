package com.cxense.rostering.pool;

/**
 * Day pool contains all employees' shift preferences for one day.
 */
public class DayPool {
    /**
     * Preferences for early shift
     */
    private ShiftPool earlyShift = new ShiftPool();
    /**
     * Preferences for late shift
     */
    private ShiftPool lateShift = new ShiftPool();

    public ShiftPool getEarlyShift() {
        return earlyShift;
    }

    public ShiftPool getLateShift() {
        return lateShift;
    }

}
