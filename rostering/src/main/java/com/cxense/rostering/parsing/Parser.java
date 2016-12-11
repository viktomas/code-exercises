package com.cxense.rostering.parsing;

import com.cxense.rostering.employee.Employee;
import com.cxense.rostering.io.Input;
import com.cxense.rostering.pool.WeekPool;

import java.util.Set;

/**
 * Interface for all possible parsers
 */
public interface Parser {

    /**
     * Sets input for parsing
     * @param input
     */
    void setInput(Input input);

    /**
     * Parse Week pool. It gets input from InputReader. It closes reader after finished
     * @return pool as an input for rostering algorithm
     */
    Product parse();

    /**
     * Product of parsing
     */
    public class Product{
        /**
         * Pool with employees' preferences for whole week.
         */
        public WeekPool weekPool;
        /**
         * List of all employees.
         */
        public Set<Employee> employees;
    }
}
