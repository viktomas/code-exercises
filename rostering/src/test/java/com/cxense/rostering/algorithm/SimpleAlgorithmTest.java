package com.cxense.rostering.algorithm;

import com.cxense.rostering.employee.Employee;
import com.cxense.rostering.parsing.EmployeePreference;
import static com.cxense.rostering.parsing.EmployeePreference.*;

import com.cxense.rostering.pool.DayPool;
import com.cxense.rostering.pool.WeekPool;
import com.cxense.rostering.schedule.DaySchedule;
import com.cxense.rostering.schedule.Shift;
import com.cxense.rostering.schedule.WeekSchedule;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static com.cxense.rostering.employee.ExperienceLevel.*;

/**
 * Test of algorithm which is solving rostering problem.
 */
public class SimpleAlgorithmTest extends SimpleAlgorithm {

    // ALL EMPLOYEES USED FOR TESTING
    private Set<Employee> employees;
    private Employee phill;
    private Employee dave;
    private Employee sandra;
    private Employee belinda;
    private Employee roger;
    private Employee stef;
    private Employee cassie;
    private Employee bill;

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
        employees = new HashSet<>(Arrays.asList(phill, dave, sandra, belinda, roger, stef, cassie, bill));
    }

    /**
     * Tests whole algorithm against first pool
     */
    @Test
    public void testAlgorithm1(){
        Algorithm algorithm = new SimpleAlgorithm();
        WeekSchedule schedule = algorithm.run(getPool1(), employees);
        assertNotNull(schedule);
    }

    /**
     * Tests whole algorithm against second pool and checks that solution is same as #getSchedule2()
     */
    @Test
    public void testAlgorithm2(){
        Algorithm algorithm = new SimpleAlgorithm();
        WeekSchedule schedule = algorithm.run(getPool2(), employees);
        assertNotNull(schedule);
        assertEquals(getSchedule2(),schedule);
    }

    /**
     * Tests whole algorithm against second pool and checks that solution is same as #getSchedule2()
     */
    @Test
    public void testAlgorithmOnInvalidPool(){
        Algorithm algorithm = new SimpleAlgorithm();
        WeekSchedule schedule = algorithm.run(getInvalidPool(), employees);
        assertNull(schedule);
    }


    /**
     * Tests that #isSolution works for right tip
     */
    @Test
    public void testThatIsSolutionsDeterminatesSolution(){
        assertTrue(isSolution(getSchedule2().getDays(),employees));
    }

    /**
     * #isSolution should show invalid tip
     */
    @Test
    public void testThatIsSolutionFindsWrongTip(){
        assertFalse(isSolution(getInvalidSchedule().getDays(),employees));
    }

    /**
     * Example from specification
     * @return
     */
    public WeekPool getPool1(){
        WeekPool weekPool = new WeekPool();
        addWorker(weekPool,phill,   EITHER, EITHER, EITHER, EITHER, EITHER, EITHER, EITHER);
        addWorker(weekPool,dave,    NONE,   NONE,   EARLY,  EARLY,  EARLY,  EARLY,  EARLY);
        addWorker(weekPool,sandra,  EITHER, EITHER, EITHER, EITHER, EITHER, NONE,   NONE);
        addWorker(weekPool,belinda, LATE,   LATE,   LATE,   LATE,   NONE,   EITHER, EITHER);
        addWorker(weekPool,roger,   EITHER, EITHER, NONE,   EARLY,  LATE,   NONE,   NONE);
        addWorker(weekPool,stef,    NONE,   NONE,   EARLY,  EARLY,  EITHER, EITHER, EITHER);
        addWorker(weekPool,cassie,  NONE,   NONE,   NONE,   EARLY,  EARLY,  NONE,   NONE);
        addWorker(weekPool,bill,    LATE,   NONE,   LATE,   NONE,   NONE,   EITHER, EARLY);
        return weekPool;
    }

    /**
     * Visually trivial to debug
     * @return
     */
    public WeekPool getPool2(){
        WeekPool weekPool = new WeekPool();
        addWorker(weekPool,phill,   EARLY,	NONE,	NONE,	LATE,	EARLY,	NONE,	NONE);
        addWorker(weekPool,dave,    EARLY,	NONE,	NONE,	LATE,	EARLY,	NONE,	NONE);
        addWorker(weekPool,sandra,  LATE,	EARLY,	NONE,	NONE,	LATE,	NONE,	EARLY);
        addWorker(weekPool,belinda, LATE,	EARLY,	NONE,	NONE,	LATE,	NONE,	EARLY);
        addWorker(weekPool,roger,   NONE,	LATE,	EARLY,	NONE,	NONE,	EARLY,	LATE);
        addWorker(weekPool,stef,    NONE,	LATE,	EARLY,	NONE,	NONE,	EARLY,	LATE);
        addWorker(weekPool,cassie,  NONE,	NONE,	LATE,	EARLY,	NONE,	LATE,	NONE);
        addWorker(weekPool,bill,    NONE,	NONE,	LATE,	EARLY,	NONE,	LATE,	NONE);
        return weekPool;
    }

    /**
     * Pool without solution
     * @return
     */
    public WeekPool getInvalidPool(){
        WeekPool weekPool = new WeekPool();
        addWorker(weekPool,phill,   EARLY,	EARLY,	EARLY,	EARLY,	EARLY,	EARLY,	EARLY);
        addWorker(weekPool,dave,    EARLY,	NONE,	NONE,	LATE,	EARLY,	NONE,	NONE);
        addWorker(weekPool,sandra,  LATE,	NONE,	NONE,	NONE,	LATE,	NONE,	EARLY);
        addWorker(weekPool,belinda, LATE,	EARLY,	NONE,	NONE,	LATE,	NONE,	EARLY);
        addWorker(weekPool,roger,   NONE,	LATE,	NONE,	NONE,	NONE,	EARLY,	LATE);
        addWorker(weekPool,stef,    NONE,	LATE,	EARLY,	NONE,	NONE,	EARLY,	LATE);
        addWorker(weekPool,cassie,  NONE,	NONE,	LATE,	EARLY,	NONE,	LATE,	NONE);
        addWorker(weekPool,bill,    NONE,	NONE,	LATE,	NONE,	NONE,	LATE,	NONE);
        return weekPool;
    }

    /**
     * Schedule based on pool2
     * @return
     */
    public WeekSchedule getSchedule2(){
        List<DaySchedule> tip = new LinkedList<>();
        tip.add(createSchedule(phill,dave,sandra,belinda));
        tip.add(createSchedule(sandra, belinda,roger,stef));
        tip.add(createSchedule(roger,stef,cassie,bill));
        tip.add(createSchedule(cassie,bill,phill,dave));
        tip.add(createSchedule(phill,dave,sandra,belinda));
        tip.add(createSchedule(roger,stef,cassie,bill));
        tip.add(createSchedule(sandra, belinda,roger,stef));
        return new WeekSchedule(tip);
    }

    /**
     * Phill is working too much, schedule is invalid
     * @return
     */
    public WeekSchedule getInvalidSchedule(){
        List<DaySchedule> tip = new LinkedList<>();
        tip.add(createSchedule(phill,dave,sandra,belinda));
        tip.add(createSchedule(phill, belinda,roger,stef));
        tip.add(createSchedule(roger,stef,cassie,bill));
        tip.add(createSchedule(cassie,bill,phill,dave));
        tip.add(createSchedule(phill,dave,sandra,belinda));
        tip.add(createSchedule(roger,stef,cassie,bill));
        tip.add(createSchedule(phill, belinda,roger,stef));
        return new WeekSchedule(tip);
    }

    /**
     * Method adds employee to the pool
     * @param weekPool week pool
     * @param employee employee
     * @param preferences 7 preferences for whole week
     */
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

    /**
     * Creates schedule for one day based on four parameters
     * @param earlyFirst employee for early shift
     * @param earlySecond employee for early shift
     * @param lateFirst employee for late shift
     * @param lateSecond employee for late shift
     * @return schedule for one day
     */
    public DaySchedule createSchedule(Employee earlyFirst, Employee earlySecond, Employee lateFirst, Employee lateSecond){
        Shift early = new Shift(earlyFirst,earlySecond);
        Shift late = new Shift(lateFirst,lateSecond);
        return new DaySchedule(early,late);
    }
}
