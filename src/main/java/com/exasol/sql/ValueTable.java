package com.exasol.sql;

import java.util.*;

import com.exasol.sql.expression.ValueExpression;

/**
 * Value tables are pseudo-tables constructed from rows and columns of expressions (e.g. literals).
 *
 */
// [impl->dsn~value-table~1]
public class ValueTable extends AbstractFragment {
    private final List<ValueTableRow> rows = new ArrayList<>();
    private String tableNameAlias;
    private final List<String> columnNameAliases = new ArrayList<>();

    /**
     * Create a new {@link ValueTable}.
     *
     * @param root SQL statement this table belongs to
     */
    public ValueTable(final Fragment root) {
        super(root);
    }

    /**
     * Append a value table row consisting of value literals to the value table.
     *
     * @param literals literals to be appended
     *
     * @return {@code this} for fluent programming
     */
    public ValueTable appendRow(final String... literals) {
        this.rows.add(new ValueTableRow(this.root, literals));
        return this;
    }

    /**
     * Append a {@link ValueTableRow} to the {@link ValueTable}.
     *
     * @param row row to be appended
     *
     * @return {@code this} for fluent programming
     */
    public ValueTable appendRow(final ValueTableRow row) {
        this.rows.add(row);
        return this;
    }

    /**
     * Get a list of all rows in the value table.
     *
     * @return rows
     */
    public List<ValueTableRow> getRows() {
        return this.rows;
    }

    /**
     * Add string values to the last row of the value table.
     *
     * @param values values to be added
     */
    public void add(final String... values) {
        amendLastRow(createLastRowBuilder().add(values).build());
    }

    /**
     * Add character values to the last row of the value table.
     *
     * @param values values to be added
     */
    public void add(final char... values) {
        amendLastRow(createLastRowBuilder().add(values).build());
    }

    private synchronized void amendLastRow(final ValueTableRow row) {
        if (isEmpty()) {
            this.rows.add(row);
        } else {
            this.rows.set(this.rows.size() - 1, row);
        }

    }

    private ValueTableRow.Builder createLastRowBuilder() {
        final ValueTableRow.Builder builder = ValueTableRow.builder(this.root);
        if (!isEmpty()) {
            builder.add(getLastRow().getExpressions());
        }
        return builder;
    }

    /**
     * Add integer values to the last row of the value table.
     *
     * @param values values to be added
     */
    public void add(final int... values) {
        amendLastRow(createLastRowBuilder().add(values).build());
    }

    /**
     * Add long values to the last row of the value table.
     *
     * @param values values to be added
     */
    public void add(final long... values) {
        amendLastRow(createLastRowBuilder().add(values).build());
    }

    /**
     * Add double values to the last row of the value table.
     *
     * @param values values to be added
     */
    public void add(final double... values) {
        amendLastRow(createLastRowBuilder().add(values).build());
    }

    /**
     * Add float values to the last row of the value table.
     *
     * @param values values to be added
     */
    public void add(final float... values) {
        amendLastRow(createLastRowBuilder().add(values).build());
    }

    /**
     * Add boolean values to the last row of the value table.
     *
     * @param values values to be added
     */
    public void add(final boolean... values) {
        amendLastRow(createLastRowBuilder().add(values).build());
    }

    /**
     * Add an unnamed placeholder to the value table.
     * <p>
     * Unnamed placeholders are the "?" in a prepared statement which are replaced by the actual variable values.
     * </p>
     */
    public void addPlaceholder() {
        amendLastRow(createLastRowBuilder().addPlaceholder().build());
    }

    /**
     * Add a list of value expressions to the last row of the value table.
     *
     * @param expressions value expressions to be added
     */
    public void add(final ValueExpression... expressions) {
        amendLastRow(createLastRowBuilder().add(Arrays.asList(expressions)).build());
    }

    private ValueTableRow getLastRow() {
        return this.rows.get(this.rows.size() - 1);
    }

    /**
     * Check if the value table is empty.
     *
     * @return {@code true} if the table is empty
     */
    protected boolean isEmpty() {
        return this.rows.isEmpty();
    }

    /**
     * Accept a visitor.
     * 
     * @param visitor to be accepted
     */
    public void accept(final ValueTableVisitor visitor) {
        visitor.visit(this);
        for (final ValueTableRow row : this.rows) {
            row.accept(visitor);
        }
        visitor.leave(this);
    }

    /**
     * Set alias for the value table.
     * 
     * @param tableNameAlias    table name alias
     * @param columnNameAliases zero or more column names aliases
     */
    public void alias(final String tableNameAlias, final String... columnNameAliases) {
        this.tableNameAlias = tableNameAlias;
        if (columnNameAliases.length > 0) {
            this.columnNameAliases.addAll(Arrays.asList(columnNameAliases));
        }
    }

    /**
     * CHeck if the value table has an alias.
     * 
     * @return true if the value table has an alias
     */
    public boolean hasAlias() {
        return (this.tableNameAlias != null) && (!this.tableNameAlias.isEmpty()) //
                && (!this.columnNameAliases.isEmpty());
    }

    /**
     * Get a table name alias.
     * 
     * @return table name alias
     */
    public String getTableNameAlias() {
        return this.tableNameAlias;
    }

    /**
     * Get column name aliases.
     * 
     * @return column name aliases
     */
    public List<String> getColumnNameAliases() {
        return this.columnNameAliases;
    }
}