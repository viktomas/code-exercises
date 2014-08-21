package com.cxense.rostering.pool;

import java.util.List;

/**
 * Created by vicek on 8/21/14.
 */
public class WeekPool {
    List<DayPool> days;

    public List<DayPool> getDays() {
        return days;
    }

    public void setDays(List<DayPool> days) {
        this.days = days;
    }
}
