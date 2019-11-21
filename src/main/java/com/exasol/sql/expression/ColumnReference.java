package com.exasol.sql.expression;

/**
 * This class represents column reference.
 */
public final class ColumnReference extends AbstractValueExpression {
    private final String columnName;
    private final String tableName;

    private ColumnReference(final String columnName, final String tableName) {
        this.columnName = columnName;
        this.tableName = tableName;
    }

    /**
     * Create a new {@link ColumnReference} from a column name and a table name.
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

    /**
     * Get the column name.
     *
     * @return column name
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * Get the table name.
     *
     * @return table name
     */
    public String getTableName() {
        return tableName;
    }

    @Override
    public void accept(final ValueExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
