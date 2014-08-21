package com.cxense.rostering.algorithm;

import com.cxense.rostering.schedule.WeekSchedule;
import com.cxense.rostering.employee.Employee;
import com.cxense.rostering.pool.WeekPool;

import java.util.Set;

/**
 * Interface for every rostering algorithm.
 */
public interface Algorithm {
    /**
     * Algorithm gets weekPool and set of all employees as an input and returns scheduled week.
     *
     * @param weekPool pool with all employees' preferences
     * @param employees set of all employees
     * @return scheduled week
     */
    WeekSchedule run(WeekPool weekPool, Set<Employee> employees);
}
