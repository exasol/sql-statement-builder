package com.exasol.datatype;

import com.exasol.sql.AbstractFragment;
import com.exasol.sql.Fragment;
import com.exasol.sql.ddl.CreateTableVisitor;

public class Decimal extends AbstractFragment implements DataType {
    private static final String NAME = "DECIMAL";
    private final int precision;
    private final int scale;

    public Decimal(final Fragment root, final int precision, final int scale) {
        super(root);
        validatePrecision(precision);
        validateScale(precision, scale);
        this.precision = precision;
        this.scale = scale;
    }

    public int getPrecision() {
        return this.precision;
    }

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
            throw new IllegalArgumentException("Precision should belong interval [1, 36]");
        }
    }

    private void validateScale(final int precision, final int scale) {
        if (scale < 0 || scale > precision) {
            throw new IllegalArgumentException(
                  "Scale should be more or equal to 0 and less than precision.");
        }
    }
}
