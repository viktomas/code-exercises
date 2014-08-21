package com.cxense.rostering.employee;

/**
 * Immutable class representing employee in the rostering system.
 */
public class Employee {

    /**
     * Name of the employee
     */
    private String name;

    /**
     * Level of employees experience
     */
    private ExperienceLevel experience;

    /**
     * Constructor of immutable class.
     * @param name name of employee
     * @param experience level of employees experience
     */
    public Employee(String name, ExperienceLevel experience) {
        this.name = name;
        this.experience = experience;
    }

    /**
     * Gets name of the employee
     * @return name of the employee
     */
    public String getName() {
        return name;
    }

    /**
     * Experience of the employee
     * @return experience of the employee
     */
    public ExperienceLevel getExperience() {
        return experience;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", experience=" + experience +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (experience != employee.experience) return false;
        if (name != null ? !name.equals(employee.name) : employee.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (experience != null ? experience.hashCode() : 0);
        return result;
    }
}
