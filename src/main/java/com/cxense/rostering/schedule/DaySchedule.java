package com.cxense.rostering.schedule;

import com.cxense.rostering.employee.Employee;

import java.util.HashSet;
import java.util.Set;

/**
 * Shedule for one day. It has two shifts.
 */
public class DaySchedule {
    /**
     * Early shift during the day
     */
    private Shift earlyShift;
    /**
     * Late shift during the day
     */
    private Shift lateShift;

    /**
     * Constructor needs both shifts.
     *
     * @param earlyShift Early shift
     * @param lateShift Late shift
     */
    public DaySchedule(Shift earlyShift, Shift lateShift) {
        this.earlyShift = earlyShift;
        this.lateShift = lateShift;
    }

    /**
     * Returns early shift form this day
     * @return
     */
    public Shift getEarlyShift() {
        return earlyShift;
    }

    /**
     * Returns late shift from this day
     * @return
     */
    public Shift getLateShift() {
        return lateShift;
    }

    /**
     * DaySchedule is valid only if nobody works twice that day.
     *
     * @return is shedule valid?
     */
    public boolean isValid(){
        Set<Employee> employees = new HashSet<>();
        employees.addAll(earlyShift.getEmployees());
        employees.addAll(lateShift.getEmployees());
        return employees.size() == 4;
    }
}
