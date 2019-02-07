package com.exasol.sql.dml;

import java.util.ArrayList;
import java.util.List;

import com.exasol.sql.*;
import com.exasol.sql.expression.*;

/**
 * Container class for values to be inserted by an INSERT statement.
 */
public class InsertValues extends AbstractFragment implements InsertFragment {
    private final List<ValueExpression> values = new ArrayList<>();

    /**
     * Create a new instance of {@link InsertValues}
     *
     * @param root root SQL statement
     */
    public InsertValues(final Fragment root) {
        super(root);
    }

    /**
     * Add one or more string literals
     *
     * @param values string literals
     */
    public void add(final String... values) {
        for (final String value : values) {
            this.getValues().add(StringLiteral.of(value));
        }
    }

    /**
     * Add one or more integer literals
     *
     * @param values integer literals
     */
    public void add(final int... values) {
        for (final int value : values) {
            this.getValues().add(IntegerLiteral.of(value));
        }
    }

    /**
     * Get the values
     *
     * @return value
     */
    public List<ValueExpression> getValues() {
        return this.values;
    }

    @Override
    public void accept(final InsertVisitor visitor) {
        visitor.visit(this);
        // sub-expression left out intentionally
        visitor.leave(this);
    }

    public void addPlaceholder() {
        this.values.add(new UnnamedPlaceholder());
    }
}