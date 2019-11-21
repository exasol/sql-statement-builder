package com.exasol.sql.expression;

/**
 * Static factory methods for SQL expressions.
 */
public abstract class ExpressionTerm extends AbstractValueExpression {
    private ExpressionTerm() {
        super();
    }

    /**
     * Create a string literal.
     *
     * @param value literal value
     * @return string literal
     */
    public static StringLiteral stringLiteral(final String value) {
        return StringLiteral.of(value);
    }

    /**
     * Create an integer literal.
     *
     * @param value literal value
     * @return integer literal
     */
    public static IntegerLiteral integerLiteral(final int value) {
        return IntegerLiteral.of(value);
    }

    /**
     * Create a reference to a table column.
     *
     * @param column column name
     * @return column reference
     */
    public static ColumnReference columnReference(final String column) {
        return ColumnReference.of(column);
    }

    /**
     * Create a reference to a column in a specific table.
     * 
     * @param column column name
     * @param table table name
     * @return column reference
     */
    public static ColumnReference columnReference(final String column, final String table) {
        return ColumnReference.column(table, column);
    }
}