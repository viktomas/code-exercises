package com.cxense.rostering.parsing;

import com.cxense.rostering.employee.Employee;
import com.cxense.rostering.employee.ExperienceLevel;
import com.cxense.rostering.io.Input;
import com.cxense.rostering.pool.WeekPool;
import org.apache.commons.lang3.Validate;

import java.util.*;

/**
 * Simple implementation of parser, takes lines from input and tranforms them into WeekPoll
 */
public class SimpleParser implements Parser {

    private Input input;

    @Override
    public void setInput(Input input) {
        this.input = input;
    }

    @Override
    public Product parse() {
        Validate.notNull(input);
        String line = input.readLine();
        if(line == null){
            throw new RuntimeException("Empty input");
        }
        WeekPool weekPool = new WeekPool();
        Set<Employee> employees = new HashSet<>();
        line = input.readLine();
        while(line != null){
            processInputLine(weekPool,employees,line);
            line = input.readLine();
        }
        input.close();
        Product product = new Product();
        product.employees = employees;
        product.weekPool = weekPool;
        return product;
    }

    private void processInputLine(WeekPool weekPool, Set<Employee> employees, String line) {
        //array of all input
        String[] columns = line.trim().split("\\s*,\\s*");
        Validate.isTrue(columns.length == 9);
        //Create employee
        String name = columns[0];
        ExperienceLevel level = columns[1].equals("Y") ? ExperienceLevel.EXPERIENCED : ExperienceLevel.INEXPERIENCED;
        Employee employee = new Employee(name,level);
        employees.add(employee);

        /*
        Put preferences into a week pool
         */
        String[] preferences = Arrays.copyOfRange(columns,2,9);
        for (int i = 0; i < preferences.length; i++) {
            switch(preferences[i]){
                case "Early":
                    weekPool.getDays().get(i).getEarlyShift().addEmployee(employee);
                    break;
                case "Late":
                    weekPool.getDays().get(i).getLateShift().addEmployee(employee);
                    break;
                case "Either":
                    weekPool.getDays().get(i).getEarlyShift().addEmployee(employee);
                    weekPool.getDays().get(i).getLateShift().addEmployee(employee);
                    break;
                case "None":
                    break;
                default:
                    throw new RuntimeException("Unknown preference: "+preferences[i]);
            }
        }
    }
}
