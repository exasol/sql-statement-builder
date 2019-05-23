package com.exasol.sql.expression.rendering;

import com.exasol.sql.*;
import com.exasol.sql.expression.*;
import com.exasol.sql.rendering.*;

/**
 * Renderer for common value expressions
 */
public class ValueExpressionRenderer extends AbstractExpressionRenderer implements ValueExpressionVisitor {
    public ValueExpressionRenderer(final StringRendererConfig config) {
        super(config);
    }

    @Override
    public void visit(final StringLiteral literal) {
        append("'");
        append(literal.toString());
        append("'");
    }

    @Override
    public void visit(final IntegerLiteral literal) {
        append(literal.toString());
    }

    @Override
    public void visit(final ColumnReference columnReference) {
        final String tableName = columnReference.getTableName();
        if (tableName != null && !tableName.isEmpty()) {
            append(tableName);
            append(".");
        }
        append(columnReference.getColumnName());
    }

    @Override
    public void visit(final UnnamedPlaceholder unnamedPlaceholder) {
        append("?");
    }
}