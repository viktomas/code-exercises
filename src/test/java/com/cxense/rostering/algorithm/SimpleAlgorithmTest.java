package com.cxense.rostering.algorithm;

import com.cxense.rostering.employee.Employee;
import com.cxense.rostering.parsing.EmployeePreference;
import static com.cxense.rostering.parsing.EmployeePreference.*;

import com.cxense.rostering.pool.DayPool;
import com.cxense.rostering.pool.WeekPool;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static com.cxense.rostering.employee.ExperienceLevel.*;

/**
 * Created by vicek on 8/21/14.
 */
public class SimpleAlgorithmTest {

    private WeekPool weekPool;
    private Set<Employee> employees;

    @Before
    public void setUp(){
        weekPool = new WeekPool();
        Employee phill = new Employee("Pill", EXPERIENCED);
        Employee dave = new Employee("Dave",      INEXPERIENCED);
        Employee sandra = new Employee("Sandra",    INEXPERIENCED);
        Employee belinda = new Employee("Belinda",   EXPERIENCED);
        Employee roger = new Employee("Roger",     EXPERIENCED);
        Employee stef = new Employee("Stef",      INEXPERIENCED);
        Employee cassie = new Employee("Cassie",    EXPERIENCED);
        Employee bill = new Employee("Bill",      INEXPERIENCED);
        employees = new HashSet<>(Arrays.asList(phill, dave, sandra, belinda, roger, stef, cassie, bill));
        addWorker(weekPool,phill,   EITHER, EITHER, EITHER, EITHER, EITHER, EITHER, EITHER);
        addWorker(weekPool,dave,    NONE,   NONE,   EARLY,  EARLY,  EARLY,  EARLY,  EARLY);
        addWorker(weekPool,sandra,  EITHER, EITHER, EITHER, EITHER, EITHER, NONE,   NONE);
        addWorker(weekPool,belinda, LATE,   LATE,   LATE,   LATE,   NONE,   EITHER, EITHER);
        addWorker(weekPool,roger,   EITHER, EITHER, NONE,   EARLY,  LATE,   NONE,   NONE);
        addWorker(weekPool,stef,    NONE,   NONE,   EARLY,  EARLY,  EITHER, EITHER, EITHER);
        addWorker(weekPool,cassie,  NONE,   NONE,   NONE,   EARLY,  EARLY,  NONE,   NONE);
        addWorker(weekPool,bill,    LATE,   NONE,   LATE,   NONE,   NONE,   EITHER, EARLY);


    }

    public void addWorker(WeekPool weekPool,Employee employee, EmployeePreference... preferences){
        assertEquals(WeekPool.NMB_OF_DAYS,preferences.length);
        for (int i = 0; i < WeekPool.NMB_OF_DAYS; i++) {
            DayPool dayPool = weekPool.getDays().get(i);
            EmployeePreference preference = preferences[i];
            switch(preference){
                case EARLY:
                    dayPool.getEarlyShift().getEmployees().add(employee);
                    break;
                case LATE:
                    dayPool.getLateShift().getEmployees().add(employee);
                    break;
                case EITHER:
                    dayPool.getEarlyShift().getEmployees().add(employee);
                    dayPool.getLateShift().getEmployees().add(employee);
            }
        }
    }

    @Test
    public void testAlgorithm(){
        Algorithm algorithm = new SimpleAlgorithm();
        algorithm.run(weekPool,employees);
    }
}
