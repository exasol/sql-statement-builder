package com.exasol.sql.expression;

import com.exasol.sql.dql.select.Select;

/**
 * This class represents column reference.
 */
public final class ColumnReference implements ValueExpression {
    private final String columnName;
    private final String tableName;
    Select subSelect;

    private ColumnReference(final Select subSelect) {
        this.columnName = null;
        this.tableName = null;
        this.subSelect = subSelect;
    }

    private ColumnReference(final String columnName, final String tableName) {
        this.columnName = columnName;
        this.tableName = tableName;
    }

    /**
     * Create a new {@link ColumnReference} from a column name and a table name.
     *
     * @param tableName  table name
     * @param columnName column name
     *
     * @return new {@link ColumnReference}
     */
    public static ColumnReference column(final String tableName, final String columnName) {
        return new ColumnReference(columnName, tableName);
    }

    /**
     * Create a new {@link ColumnReference} from a column name.
     *
     * @param columnName column name
     * @return new {@link ColumnReference}
     */
    public static ColumnReference of(final String columnName) {
        return new ColumnReference(columnName, null);
    }

    public static ColumnReference of(final Select subSelect) {
        return new ColumnReference(subSelect);
    }

    /**
     * Get the column name.
     *
     * @return column name
     */
    public String getColumnName() {
        return this.columnName;
    }

    /**
     * Get the table name.
     *
     * @return table name
     */
    public String getTableName() {
        return this.tableName;
    }

    public boolean hasSubSelect() {
        return this.subSelect != null;
    }

    public Select getSubSelect() {
        return this.subSelect;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        if ((this.tableName != null) && !this.tableName.isEmpty()) {
            stringBuilder.append(this.tableName);
            stringBuilder.append(".");
        }
        stringBuilder.append(this.columnName);
        return stringBuilder.toString();
    }

    @Override
    public void accept(final ValueExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
