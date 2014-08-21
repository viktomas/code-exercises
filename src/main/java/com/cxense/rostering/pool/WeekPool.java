package com.cxense.rostering.pool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vicek on 8/21/14.
 */
public class WeekPool {

    public static final int NMB_OF_DAYS = 7;

    List<DayPool> days;

    public WeekPool() {
        days = new ArrayList<>();
        for (int i = 0; i < NMB_OF_DAYS; i++) {
            days.add(new DayPool());
        }
    }

    public List<DayPool> getDays() {
        return days;
    }

    public void setDays(List<DayPool> days) {
        this.days = days;
    }
}
