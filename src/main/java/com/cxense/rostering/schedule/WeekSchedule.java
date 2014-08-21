package com.cxense.rostering.schedule;

import java.util.ArrayList;
import java.util.List;

/**
 * Schedule for whole week
 */
public class WeekSchedule implements Optimal {

    /**
     * List with scheduled days in the week.
     */
    private List<DaySchedule> days;

    /**
     * Empty constructor.
     */
    public WeekSchedule() {
        this.days = new ArrayList<>();
    }

    /**
     * Creates with given days
     * @param days scheduled days in the week
     */
    public WeekSchedule(List<DaySchedule> days) {
        this.days = days;
    }

    /**
     * Get list of scheduled days
     * @return list of scheduled days
     */
    public List<DaySchedule> getDays() {
        return days;
    }

    @Override
    public boolean isOptimal() {
        for (DaySchedule day : days) {
            if(!day.isOptimal()){
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "WeekSchedule{" +
                "days=" + days +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeekSchedule that = (WeekSchedule) o;

        if (days != null ? !days.equals(that.days) : that.days != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return days != null ? days.hashCode() : 0;
    }
}
