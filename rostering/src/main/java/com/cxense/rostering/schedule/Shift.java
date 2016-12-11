package com.cxense.rostering.schedule;

import com.cxense.rostering.employee.Employee;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * One scheduled shift with 2 employees.
 */
public class Shift implements Optimal {
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

    @Override
    public boolean isOptimal() {
        //OPTIMIZE might be optimized with computation in the constructor
        boolean experienced = false;
        boolean inexperienced = false;
        for (Employee employee : employees) {
            switch(employee.getExperience()){
                case EXPERIENCED:
                    experienced = true;
                    break;
                case INEXPERIENCED:
                    inexperienced = true;
                    break;
            }
        }
        return experienced && inexperienced;
    }

    @Override
    public String toString() {
        return "Shift{" +
                "employees=" + employees +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shift shift = (Shift) o;

        if (employees != null ? !employees.equals(shift.employees) : shift.employees != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return employees != null ? employees.hashCode() : 0;
    }
}
