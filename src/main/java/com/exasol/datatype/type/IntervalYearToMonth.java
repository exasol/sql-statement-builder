package com.exasol.datatype.type;

import com.exasol.sql.ddl.CreateTableVisitor;

/**
 * This class implements the Exasol-proprietary data type interval year to month
 */
public class IntervalYearToMonth implements DataType {
    private static final String NAME = "INTERVAL YEAR(%s) TO MONTH";
    private final int yearPrecision;

    /**
     * Create a new instance of an {@link IntervalYearToMonth} data type
     *
     * @param yearPrecision year precision value
     */
    public IntervalYearToMonth(final int yearPrecision) {
        validatePrecision(yearPrecision);
        this.yearPrecision = yearPrecision;
    }

    private void validatePrecision(final int yearPrecision) {
        if (yearPrecision < 1 || yearPrecision > 9) {
            throw new IllegalArgumentException("Year precision must be a number between 1 and 9.");
        }
    }

    @Override
    public void accept(final CreateTableVisitor visitor) {
        visitor.visit(this);
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
}
