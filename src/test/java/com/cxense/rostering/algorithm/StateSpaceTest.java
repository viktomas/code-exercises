package com.cxense.rostering.algorithm;

import com.cxense.rostering.employee.Employee;
import com.cxense.rostering.employee.ExperienceLevel;
import com.cxense.rostering.pool.DayPool;
import com.cxense.rostering.pool.ShiftPool;
import com.cxense.rostering.schedule.DaySchedule;
import com.cxense.rostering.schedule.Shift;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

/**
 * Test for creating state space of the algorithm.
 */
public class StateSpaceTest extends StateSpace {

    private Employee john;
    private Employee bob;
    private Employee alice;
    private Employee tom;
    private Employee frank;

    @Before
    public void setUp(){
        john = new Employee("John", ExperienceLevel.INEXPERIENCED);
        bob = new Employee("Bob", ExperienceLevel.EXPERIENCED);
        alice = new Employee("Alice", ExperienceLevel.INEXPERIENCED);
        tom = new Employee("Tom", ExperienceLevel.INEXPERIENCED);
        frank = new Employee("Frank", ExperienceLevel.INEXPERIENCED);
    }


    @Test
    public void testPossibleShifts(){
        ShiftPool pool = new ShiftPool();
        pool.getEmployees().add(john);
        pool.getEmployees().add(bob);
        pool.getEmployees().add(alice);
        List<Shift> possibleShifts = allPossibleShifts(pool);
        assertEquals(3, possibleShifts.size());
        Shift johnBob = new Shift(john,bob);
        Shift johnAlice = new Shift(john, alice);
        Shift bobAlice = new Shift(bob, alice);
        assertTrue(possibleShifts.contains(johnBob));
        assertTrue(possibleShifts.contains(johnAlice));
        assertTrue(possibleShifts.contains(bobAlice));
        pool.getEmployees().add(tom);
        possibleShifts = allPossibleShifts(pool);
        assertEquals(6, possibleShifts.size());
    }

    @Test
    public void testThatPossibleDaysCombinesShifts(){
        Employee hank = new Employee("Hank", ExperienceLevel.INEXPERIENCED);
        DayPool dayPool = new DayPool();
        dayPool.getEarlyShift().addEmployee(john);
        dayPool.getEarlyShift().addEmployee(bob);
        dayPool.getEarlyShift().addEmployee(alice);
        dayPool.getLateShift().addEmployee(tom);
        dayPool.getLateShift().addEmployee(frank);
        dayPool.getLateShift().addEmployee(hank);
        //employees doesn't overlap so it's simple 3x3 possibilities
        List<DaySchedule> possibleShedules = allPossibleDays(dayPool);
        assertEquals(9, possibleShedules.size());
    }

    @Test
    public void testThatEmployeeCannotWorkTwice(){
        DayPool dayPool = new DayPool();
        dayPool.getEarlyShift().addEmployee(john);
        dayPool.getEarlyShift().addEmployee(bob);
        dayPool.getEarlyShift().addEmployee(alice);
        dayPool.getLateShift().addEmployee(tom);
        dayPool.getLateShift().addEmployee(frank);
        dayPool.getLateShift().addEmployee(john);
        //john is trying to much so we have 4 collisions (JB-TJ,JB-FJ, JA-TJ, JA-FJ)
        List<DaySchedule> possibleShedules = allPossibleDays(dayPool);
        assertEquals(5, possibleShedules.size());
    }
}
