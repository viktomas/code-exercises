package com.cxense.rostering.algorithm;

import com.cxense.rostering.schedule.DaySchedule;
import com.cxense.rostering.schedule.Shift;
import com.cxense.rostering.schedule.WeekSchedule;
import com.cxense.rostering.employee.Employee;
import com.cxense.rostering.pool.WeekPool;

import java.util.*;

/**
 * Simple implementation of rostering algorithm.
 */
public class SimpleAlgorithm implements Algorithm {

    /**
     * Generator of state space
     */
    private StateSpace stateSpaceGen = new StateSpace();

    /**
     * Algorithm goes through every state in state space unless it finds a optimal solution.
     *
     * @param weekPool pool with all employees' preferences
     * @param employees set of all employees
     * @return scheduled week or null if there is no solution.
     */
    @Override
    public WeekSchedule run(WeekPool weekPool, Set<Employee> employees) {
        List<List<DaySchedule>> stateSpace = stateSpaceGen.createStateSpace(weekPool);
        return iterate(0, Collections.EMPTY_LIST, stateSpace, employees);
    }

    /**
     * Recursive method for building tips for week schedule. It either just iterates trough the state space or if it
     * is last call (sunday)(tip.size() == 7) then it tests that tip for solution.
     *
     * @param dayIndex which day are we adding to the tip
     * @param weekTip tipped days from previous call
     * @param stateSpace state space
     * @param employees all employees
     * @return schedule if solution is found or null if not
     */
    private WeekSchedule iterate(int dayIndex, List<DaySchedule> weekTip, List<List<DaySchedule>> stateSpace, Set<Employee> employees) {
        List<DaySchedule> possibleDays = stateSpace.get(dayIndex);
        for (DaySchedule possibleDay : possibleDays) {
            List<DaySchedule> innerTip = new LinkedList<>(weekTip);
            innerTip.add(possibleDay);
            if(innerTip.size() == WeekPool.NMB_OF_DAYS){
                if(isSolution(innerTip, employees)){
                    WeekSchedule weekSchedule = new WeekSchedule(innerTip);
                    return weekSchedule;
                }else{
                    return null;
                }
            }else{
                WeekSchedule weekSchedule = iterate(dayIndex+1, innerTip, stateSpace, employees);
                if (weekSchedule!=null){
                    return weekSchedule;
                }
            }
        }
        return null;
    }

    /**
     * Tests tip for solution. Because the way state space is generated, only thing we need to test is that every
     * employee works at least one and no more than four shifts.
     * @param tip tip which might be a solution
     * @param employees all employees
     * @return is tip a solution?
     */
    protected boolean isSolution(List<DaySchedule> tip, Set<Employee> employees) {
        Map<Employee,Integer> shiftCount = new HashMap<>();
        for (Employee employee : employees) {
            shiftCount.put(employee,0);
        }
        for (DaySchedule daySchedule : tip) {
            addCount(daySchedule.getEarlyShift(),shiftCount);
            addCount(daySchedule.getLateShift(),shiftCount);
        }
        for (Integer count : shiftCount.values()) {
            if(count < 1 || count > 4){
                return false;
            }
        }
        return true;
    }

    /**
     * Adds one to shift count for every employee who works the shift.
     * @param shift shift
     * @param shiftCount count of shifts for employees
     */
    private void addCount(Shift shift, Map<Employee,Integer> shiftCount){
        for (Employee employee : shift.getEmployees()) {
            int count = shiftCount.get(employee);
            shiftCount.put(employee,count + 1);
        }
    }
}
