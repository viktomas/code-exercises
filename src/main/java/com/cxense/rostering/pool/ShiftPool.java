package com.cxense.rostering.pool;

import com.cxense.rostering.employee.Employee;

import java.util.HashSet;
import java.util.Set;

/**
 * Pool with employees who want to work this particular shift.
 */
public class ShiftPool {
    /**
     * Set of all employees.
     */
    private Set<Employee> employees;

    /**
     * Constructor creates empty pool.
     */
    public ShiftPool() {
        this.employees = new HashSet<>();
    }

    /**
     * Returns set with employees.
     * @return
     */
    public Set<Employee> getEmployees() {
        return employees;
    }

    /**
     * Adds employee to this pool.
     * @param employee employee who wants to work this shift.
     */
    public void addEmployee(Employee employee){
        employees.add(employee);
    }
}
