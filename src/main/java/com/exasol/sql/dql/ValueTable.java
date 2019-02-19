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
    private final List<ValueTableRow> rows = new ArrayList<>();

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
     *
     * @return <code>this</code> for fluent programming
     */
    public ValueTable appendRow(final String... literals) {
        this.rows.add(new ValueTableRow(this.root, literals));
        return this;
    }

    /**
     * Append a {@link ValueTableRow} to the {@link ValueTable}
     *
     * @param row row to be appended
     *
     * @return <code>this</code> for fluent programming
     */
    public ValueTable appendRow(final ValueTableRow row) {
        this.rows.add(row);
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

    /**
     * Adds values to the last row of the value table
     *
     * @param values values to be added
     */
    public void add(final String... values) {
        amendLastRow(createLastRowBuilder().add(values).build());
    }

    private ValueTableRow.Builder createLastRowBuilder() {
        final ValueTableRow.Builder builder = ValueTableRow.builder(this.root);
        if (!isEmpty()) {
            builder.add(getLastRow().getExpressions());
        }
        return builder;
    }

    private synchronized void amendLastRow(final ValueTableRow row) {
        if (isEmpty()) {
            this.rows.add(row);
        } else {
            this.rows.set(this.rows.size() - 1, row);
        }

    }

    public void add(final int... values) {
        amendLastRow(createLastRowBuilder().add(values).build());
    }

    public void addPlaceholder() {
        amendLastRow(createLastRowBuilder().addPlaceholder().build());
    }

    private ValueTableRow getLastRow() {
        return this.rows.get(this.rows.size() - 1);
    }

    protected boolean isEmpty() {
        return this.rows.isEmpty();
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