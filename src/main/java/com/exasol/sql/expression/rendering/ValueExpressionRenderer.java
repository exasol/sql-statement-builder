package com.exasol.sql.expression.rendering;

import com.exasol.sql.UnnamedPlaceholder;
import com.exasol.sql.expression.Value;
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
    public void visit(final Value value) {
        final Object object = value.get();
        if (object instanceof String) {
            append("'");
            append((String) object);
            append("'");
        } else {
            this.builder.append(value.get().toString());
        }
    }

    @Override
    public void visit(final UnnamedPlaceholder unnamedPlaceholder) {
        append("?");
    }
}