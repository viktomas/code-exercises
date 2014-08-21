package com.cxense.rostering.algorithm;

import com.cxense.rostering.pool.WeekPool;
import com.cxense.rostering.schedule.DaySchedule;

import java.util.List;

/**
 * This class creates state space for simple algorithm.
 */
public class StateSpace {
    /**
     * It returns list of size 7. Each element in this list is
     * another list representing all possible combinations of employees and shifts in the day.
     * @param workPool pool with employees' preferences
     * @return all possible combinations of employees and shifts for each day of the week.
     */
    List<List<DaySchedule>> createStateSpace(WeekPool workPool){
        return null;
    }
}
