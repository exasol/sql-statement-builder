package com.exasol.sql.dql;

import java.util.ArrayList;
import java.util.List;

import com.exasol.sql.*;

/**
 * Value tables are pseudo-tables constructed from rows and columns of expressions (e.g. literals)
 *
 */
// [impl->dsn~value-table~1]
public class ValueTable extends AbstractFragment implements GenericFragment {
    final List<ValueTableRow> rows = new ArrayList<>();

    /**
     * Create a new {@link ValueTable}
     *
     * @param root SQL statement this table belongs to
     */
    public ValueTable(final Fragment root) {
        super(root);
    }

    /**
     * Append a value table row consisting of value literals to the value table
     *
     * @param literals literals to be appended
     * @return <code>this</code> for fluent programming
     */
    public ValueTable appendRow(final String... literals) {
        this.rows.add(new ValueTableRow(this.root, literals));
        return this;
    }

    /**
     * Get a list of all rows in the value table
     *
     * @return rows
     */
    public List<ValueTableRow> getRows() {
        return this.rows;
    }

    @Override
    public void accept(final FragmentVisitor visitor) {
        visitor.visit(this);
        for (final ValueTableRow row : this.rows) {
            row.accept(visitor);
        }
        visitor.leave(this);
    }
}