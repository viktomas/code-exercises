package com.cxense.rostering.report;

import com.cxense.rostering.io.Output;
import com.cxense.rostering.schedule.WeekSchedule;

/**
 * Simple interface for writing a report of the algorithm.
 */
public interface ReportWriter {

    /**
     * Set's output for writing a report.
     * @param output output for writing a report
     */
    void setOutput(Output output);

    /**
     * Writes schedule into output
     * @param weekSchedule schedule which is output of a algorithm
     */
    void writeReport(WeekSchedule weekSchedule);

}
