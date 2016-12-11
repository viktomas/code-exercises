package com.cxense.rostering.schedule;

/**
 * Every class implementing this interface might and might not be in its optimal state.
 */
public interface Optimal {

    /**
     * Is current state of instance optimal?
     * @return true if instance is optimal, false otherwise
     */
    boolean isOptimal();
}
