package com.exasol.datatype.type;

import com.exasol.sql.ColumnDefinitionVisitor;

/**
 * This class implements the Exasol-proprietary data type interval day to second
 */
public class IntervalDayToSecond implements DataType {
    private static final String NAME = "INTERVAL DAY(%s) TO SECOND(%s)";
    private final int yearPrecision;
    private final int millisecondPrecision;

    /**
     * Create a new instance of an {@link IntervalDayToSecond} data type
     *
     * @param yearPrecision year precision value
     * @param millisecondPrecision millisecond precision value
     */
    public IntervalDayToSecond(final int yearPrecision, final int millisecondPrecision) {
        validateYearPrecision(yearPrecision);
        validateMillisecondPrecision(millisecondPrecision);
        this.yearPrecision = yearPrecision;
        this.millisecondPrecision = millisecondPrecision;
    }

    private void validateMillisecondPrecision(final int millisecondPrecision) {
        if (millisecondPrecision < 0 || millisecondPrecision > 9) {
            throw new IllegalArgumentException("Millisecond precision must be a number between 0 and 9.");
        }
    }

    private void validateYearPrecision(final int yearPrecision) {
        if (yearPrecision < 1 || yearPrecision > 9) {
            throw new IllegalArgumentException("Year precision must be a number between 1 and 9.");
        }
    }

    @Override
    public String getName() {
        return NAME;
    }

    /**
     * @return year precision
     */
    public int getYearPrecision() {
        return this.yearPrecision;
    }

    /**
     * @return millisecond precisiom
     */
    public int getMillisecondPrecision() {
        return this.millisecondPrecision;
    }

    @Override
    public void accept(final ColumnDefinitionVisitor visitor) {
        visitor.visit(this);
    }
}