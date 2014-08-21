package com.cxense.rostering.pool;

import com.cxense.rostering.employee.Employee;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by vicek on 8/21/14.
 */
public class ShiftPool {
    private Set<Employee> employees;

    public ShiftPool() {
        this.employees = new HashSet<>();
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee){
        employees.add(employee);
    }
}
