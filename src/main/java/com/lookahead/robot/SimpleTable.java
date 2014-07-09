package com.lookahead.robot;

import org.apache.commons.lang3.Validate;

/**
 * Created by vicek on 7/9/14.
 */
public class SimpleTable implements Table {

    private static final int MIN_X = 0;
    private static final int MIN_Y = 0;

    private int maxX;

    private int maxY;

    public SimpleTable(int maxX, int maxY) {
        Validate.isTrue(maxX >= 0 && maxY >= 0, "Size of the table can't be negative.");
        this.maxX = maxX;
        this.maxY = maxY;
    }

    @Override
    public boolean isOnTable(Coordinate coordinate) {
        boolean rightX = coordinate.getX() >= MIN_X && coordinate.getX() <= maxX;
        boolean rightY = coordinate.getY() >= MIN_Y && coordinate.getY() <= maxY;
        return rightX && rightY;
    }
}
