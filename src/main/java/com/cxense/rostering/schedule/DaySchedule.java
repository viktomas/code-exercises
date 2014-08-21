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

    @Override
    public String toString() {
        return "DaySchedule{" +
                "earlyShift=" + earlyShift +
                ", lateShift=" + lateShift +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DaySchedule that = (DaySchedule) o;

        if (earlyShift != null ? !earlyShift.equals(that.earlyShift) : that.earlyShift != null) return false;
        if (lateShift != null ? !lateShift.equals(that.lateShift) : that.lateShift != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = earlyShift != null ? earlyShift.hashCode() : 0;
        result = 31 * result + (lateShift != null ? lateShift.hashCode() : 0);
        return result;
    }
}
