package com.cxense.rostering.schedule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vicek on 8/21/14.
 */
public class WeekSchedule {

    private List<DaySchedule> days;

    public WeekSchedule() {
        this.days = new ArrayList<>();
    }

    public List<DaySchedule> getDays() {
        return days;
    }
}
