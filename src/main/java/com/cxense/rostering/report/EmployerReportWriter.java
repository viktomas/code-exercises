package com.cxense.rostering.report;

import com.cxense.rostering.employee.Employee;
import com.cxense.rostering.io.Output;
import com.cxense.rostering.schedule.DaySchedule;
import com.cxense.rostering.schedule.WeekSchedule;
import org.apache.commons.lang3.Validate;

import java.util.Iterator;

/**
 * This Writer writes reports from employer point of view, which means schedule in following format:
 *          | MONDAY        | TUESDAY       | WEDNESDAY     |...
 * Early    | John, Frank   | Maria, Joseph | Ben, Frank    |...
 * Late     | Pete, Bob     | Alice, Jan    | John, Bob     |...
 * ...
 */
public class EmployerReportWriter implements ReportWriter {

    /**
     * Output in which the report is written
     */
    private Output output;

    @Override
    public void setOutput(Output output) {
        this.output = output;
    }

    @Override
    public void writeReport(WeekSchedule weekSchedule) {
        Validate.notNull(output);
        output.writeLine(" | MONDAY | TUESDAY | WEDNESDAY | THURSDAY | FRIDAY | SATURDAY | SUNDAY");

        StringBuilder early = new StringBuilder();
        StringBuilder late = new StringBuilder();
        early.append("Early |");
        early.append("Late |");
        for (DaySchedule daySchedule : weekSchedule.getDays()) {
            /*
            For each day this ugly masquerade
             */
            Iterator<Employee> earlyIterator = daySchedule.getEarlyShift().getEmployees().iterator();
            early.append(earlyIterator.next().getName()).append(",")
                    .append(earlyIterator.next().getName());
            Iterator<Employee> lateIterator = daySchedule.getLateShift().getEmployees().iterator();
            late.append(lateIterator.next().getName()).append(",")
                    .append(lateIterator.next().getName());
            early.append(" | ");
            late.append(" | ");

        }
        output.writeLine(early.toString());
        output.writeLine(late.toString());

    }

}
