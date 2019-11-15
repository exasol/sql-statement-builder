package com.exasol.sql.expression.rendering;

import com.exasol.sql.UnnamedPlaceholder;
import com.exasol.sql.expression.ColumnReference;
import com.exasol.sql.expression.DefaultValue;
import com.exasol.sql.expression.IntegerLiteral;
import com.exasol.sql.expression.StringLiteral;
import com.exasol.sql.expression.ValueExpressionVisitor;
import com.exasol.sql.rendering.StringRendererConfig;

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
        if ((tableName != null) && !tableName.isEmpty()) {
            append(tableName);
            append(".");
        }
        append(columnReference.getColumnName());
    }

    @Override
    public void visit(final UnnamedPlaceholder unnamedPlaceholder) {
        append("?");
    }

    @Override
    public void visit(final DefaultValue defaultValue) {
        appendKeyword("DEFAULT");
    }
}