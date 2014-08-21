package com.cxense.rostering.pool;

import com.cxense.rostering.employee.Employee;

import java.util.Set;

/**
 * Created by vicek on 8/21/14.
 */
public class ShiftPool {
    private Set<Employee> employees;

    public ShiftPool(Set<Employee> employees) {
        this.employees = employees;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
