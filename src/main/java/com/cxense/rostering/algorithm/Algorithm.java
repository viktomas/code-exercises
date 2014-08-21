package com.cxense.rostering.algorithm;

import com.cxense.rostering.WeekSchedule;
import com.cxense.rostering.employee.Employee;
import com.cxense.rostering.pool.WeekPool;

import java.util.Set;

/**
 * Interface for every rostering algorithm.
 */
public interface Algorithm {
    WeekSchedule run(WeekPool weekPool, Set<Employee> employees);
}
