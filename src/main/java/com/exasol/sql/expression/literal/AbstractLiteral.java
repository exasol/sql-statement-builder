package com.exasol.sql.expression.literal;

import com.exasol.sql.expression.ValueExpression;
import com.exasol.sql.expression.ValueExpressionVisitor;

/**
 * Abstract basis for {@link Literal}s.
 */
public abstract class AbstractLiteral implements Literal, ValueExpression {
    @Override
    public final void accept(final ValueExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
