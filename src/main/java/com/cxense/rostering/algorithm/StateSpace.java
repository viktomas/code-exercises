package com.cxense.rostering.algorithm;

import com.cxense.rostering.employee.Employee;
import com.cxense.rostering.pool.DayPool;
import com.cxense.rostering.pool.ShiftPool;
import com.cxense.rostering.pool.WeekPool;
import com.cxense.rostering.schedule.DaySchedule;
import com.cxense.rostering.schedule.Shift;

import java.util.ArrayList;
import java.util.List;

/**
 * This class creates state space for simple algorithm.
 */
public class StateSpace {
    /**
     * It returns list of size 7. Each element in this list is
     * another list representing all possible combinations of employees and shifts in the day.
     * @param workPool pool with employees' preferences
     * @return all possible combinations of employees and shifts for each day of the week.
     */
    public List<List<DaySchedule>> createStateSpace(WeekPool workPool){
        List<List<DaySchedule>> stateSpace = new ArrayList<>(7);
        for (DayPool dayPool : workPool.getDays()) {
            stateSpace.add(allPossibleDays(dayPool));
        }
        return stateSpace;
    }

    /**
     * Get all possible combinations of employees and shifts in the day.
     * @param dayPool pool with employees' preference for the day
     * @return all possible combinations of employees and shifts in the day.
     */
    protected List<DaySchedule> allPossibleDays(DayPool dayPool) {
        List<DaySchedule> result = new ArrayList<>();
        List<Shift> possibleEarlyShifts = allPossibleShifts(dayPool.getEarlyShift());
        List<Shift> possibleLateShifts = allPossibleShifts(dayPool.getLateShift());
        // all possible combinations of early and late shifts
        for (Shift possibleEarlyShift : possibleEarlyShifts) {
            for (Shift possibleLateShift : possibleLateShifts) {
                DaySchedule daySchedule = new DaySchedule(possibleEarlyShift,possibleLateShift);
                if(daySchedule.isValid()){
                    result.add(daySchedule);
                }
            }
        }
        return result;
    }

    /**
     * Returns all possible shifts created from list of employees who preferred given shiftPool.
     * @param shiftPool shiftPool with all employees who wanted to work this shift
     * @return all possible combinations of those employees.
     */
    protected List<Shift> allPossibleShifts(ShiftPool shiftPool) {
        List<Shift> possibleShifts = new ArrayList<>();
        List<Employee> employees = new ArrayList<>(shiftPool.getEmployees());
        for (int i = 0; i < employees.size() -1; i++) {
            for(int j=i+1;j<employees.size(); j++){
                possibleShifts.add(new Shift(employees.get(i),employees.get(j)));
            }
        }
        return possibleShifts;
    }
}
