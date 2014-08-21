package com.cxense.rostering.parsing;

import com.cxense.rostering.io.Input;
import com.cxense.rostering.pool.WeekPool;

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
    WeekPool parse();
}
