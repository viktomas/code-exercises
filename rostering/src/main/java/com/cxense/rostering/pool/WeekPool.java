package com.cxense.rostering.pool;

import java.util.ArrayList;
import java.util.List;

/**
 * Week pool holds all employees' preferences for shifts. It is used as a main input for a rostering algorithm.
 */
public class WeekPool {

    /**
     * Number of days in the week.
     */
    public static final int NMB_OF_DAYS = 7;

    /**
     * List of all days in the pool.
     */
    List<DayPool> days;

    /**
     * Constructor initializes pool to 7 empty day pools
     */
    public WeekPool() {
        days = new ArrayList<>();
        for (int i = 0; i < NMB_OF_DAYS; i++) {
            days.add(new DayPool());
        }
    }

    /**
     * Returns all days in the week pool.
     * @return all days in the week pool
     */
    public List<DayPool> getDays() {
        return days;
    }
}
