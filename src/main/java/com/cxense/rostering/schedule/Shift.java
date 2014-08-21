package com.cxense.rostering.schedule;

import com.cxense.rostering.employee.Employee;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * One scheduled shift with 2 employees.
 */
public class Shift {
    /**
     * Employees int the shift.
     */
    private Set<Employee> employees;

    /**
     * Creates shift.
     *
     * @param first employee who is working in this shift
     * @param second employee who is working in this shift
     */
    public Shift(Employee first, Employee second) {
        employees = new HashSet<>();
        employees.add(first);
        employees.add(second);
    }

    /**
     * Returns set of employees.
     *
     * @return set of employees.
     */
    public Set<Employee> getEmployees(){
        return Collections.unmodifiableSet(employees);
    }
}
