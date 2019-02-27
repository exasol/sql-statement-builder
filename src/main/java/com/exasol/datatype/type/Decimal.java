package com.exasol.datatype.type;

import com.exasol.sql.ddl.CreateTableVisitor;

/**
 * This class implements the Exasol-proprietary data type decimal
 */
public class Decimal implements DataType {
    private static final String NAME = "DECIMAL";
    private final int precision;
    private final int scale;

    /**
     * Create a new instance of an {@link Decimal} data type
     *
     * @param precision precision for numeric value
     * @param scale     scale for numeric value
     */
    public Decimal(final int precision, final int scale) {
        validatePrecision(precision);
        validateScale(precision, scale);
        this.precision = precision;
        this.scale = scale;
    }

    /**
     * @return precision value
     */
    public int getPrecision() {
        return this.precision;
    }

    /**
     * @return scale value
     */
    public int getScale() {
        return this.scale;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void accept(final CreateTableVisitor visitor) {
        visitor.visit(this);
    }

    private void validatePrecision(final int precision) {
        if (precision < 1 || precision > 36) {
            throw new IllegalArgumentException("Precision must be a number between 1 and 36.");
        }
    }

    private void validateScale(final int precision, final int scale) {
        if (scale < 0 || scale > precision) {
            throw new IllegalArgumentException(
                  "Scale must be a number between 0 and precision - 1.");
        }
    }
}
