package com.exasol.sql.expression;

public abstract class ExpressionTerm extends AbstractValueExpression {
    private ExpressionTerm() {
        super();
    }

    public static StringLiteral stringLiteral(final String value) {
        return StringLiteral.of(value);
    }

    public static IntegerLiteral integerLiteral(final int value) {
        return IntegerLiteral.of(value);
    }

    public static ColumnReference columnReference(final String column) {
        return ColumnReference.of(column);
    }

    public static ColumnReference columnReference(final String column, final String table) {
        return ColumnReference.column(column, table);
    }
}
