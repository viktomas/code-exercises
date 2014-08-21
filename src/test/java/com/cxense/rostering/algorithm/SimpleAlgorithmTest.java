package com.cxense.rostering.algorithm;

import com.cxense.rostering.employee.Employee;
import com.cxense.rostering.parsing.EmployeePreference;
import static com.cxense.rostering.parsing.EmployeePreference.*;

import com.cxense.rostering.pool.DayPool;
import com.cxense.rostering.pool.WeekPool;
import com.cxense.rostering.schedule.WeekSchedule;
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
public class SimpleAlgorithmTest extends SimpleAlgorithm {

    private Set<Employee> employees;
    private Employee phill;
    private Employee dave;
    private Employee sandra;
    private Employee belinda;
    private Employee roger;
    private Employee stef;
    private Employee cassie;
    private Employee bill;
    private Employee saviour;

    @Before
    public void setUp(){
        phill = new Employee("Pill", EXPERIENCED);
        dave = new Employee("Dave",      INEXPERIENCED);
        sandra = new Employee("Sandra",    INEXPERIENCED);
        belinda = new Employee("Belinda",   EXPERIENCED);
        roger = new Employee("Roger",     EXPERIENCED);
        stef = new Employee("Stef",      INEXPERIENCED);
        cassie = new Employee("Cassie",    EXPERIENCED);
        bill = new Employee("Bill",      INEXPERIENCED);
        saviour = new Employee("Saviour",      EXPERIENCED);
        employees = new HashSet<>(Arrays.asList(phill, dave, sandra, belinda, roger, stef, cassie, bill));



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
    public void testAlgorithm1(){
        WeekPool weekPool = new WeekPool();
        addWorker(weekPool,phill,   EITHER, EITHER, EITHER, EITHER, EITHER, EITHER, EITHER);
        addWorker(weekPool,dave,    NONE,   NONE,   EARLY,  EARLY,  EARLY,  EARLY,  EARLY);
        addWorker(weekPool,sandra,  EITHER, EITHER, EITHER, EITHER, EITHER, NONE,   NONE);
        addWorker(weekPool,belinda, LATE,   LATE,   LATE,   LATE,   NONE,   EITHER, EITHER);
        addWorker(weekPool,roger,   EITHER, EITHER, NONE,   EARLY,  LATE,   NONE,   NONE);
        addWorker(weekPool,stef,    NONE,   NONE,   EARLY,  EARLY,  EITHER, EITHER, EITHER);
        addWorker(weekPool,cassie,  NONE,   NONE,   NONE,   EARLY,  EARLY,  NONE,   NONE);
        addWorker(weekPool,bill,    LATE,   NONE,   LATE,   NONE,   NONE,   EITHER, EARLY);
        Algorithm algorithm = new SimpleAlgorithm();
        WeekSchedule schedule = algorithm.run(weekPool, employees);
        assertNotNull(schedule);
    }

    @Test
    public void testAlgorithm2(){
        WeekPool weekPool = new WeekPool();
        addWorker(weekPool,phill,   EARLY,	NONE,	NONE,	LATE,	EARLY,	NONE,	NONE);
        addWorker(weekPool,dave,    EARLY,	NONE,	NONE,	LATE,	EARLY,	NONE,	NONE);
        addWorker(weekPool,sandra,  LATE,	EARLY,	NONE,	NONE,	LATE,	NONE,	EARLY);
        addWorker(weekPool,belinda, LATE,	EARLY,	NONE,	NONE,	LATE,	NONE,	EARLY);
        addWorker(weekPool,roger,   NONE,	LATE,	EARLY,	NONE,	NONE,	EARLY,	LATE);
        addWorker(weekPool,stef,    NONE,	LATE,	EARLY,	NONE,	NONE,	EARLY,	LATE);
        addWorker(weekPool,cassie,  NONE,	NONE,	LATE,	EARLY,	NONE,	LATE,	NONE);
        addWorker(weekPool,bill,    NONE,	NONE,	LATE,	EARLY,	NONE,	LATE,	NONE);
        Algorithm algorithm = new SimpleAlgorithm();
        WeekSchedule schedule = algorithm.run(weekPool, employees);
        assertNotNull(schedule);

    }
}
