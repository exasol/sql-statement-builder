package com.exasol.sql.dml;

import java.util.ArrayList;
import java.util.List;

import com.exasol.sql.*;
import com.exasol.sql.expression.Value;
import com.exasol.sql.expression.ValueExpression;

/**
 * Container class for values to be inserted by an INSERT statement.
 */
public class InsertValues extends AbstractFragment implements InsertFragment {
    private final List<ValueExpression> values = new ArrayList<>();

    /**
     * Create a new instance of {@link InsertValues
     *
     * @param root root SQL statement
     */
    public InsertValues(final Fragment root) {
        super(root);
    }

    /**
     * Add one or more values
     *
     * @param values values
     */
    public void add(final Object... values) {
        for (final Object value : values) {
            this.getValues().add(new Value(value));
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