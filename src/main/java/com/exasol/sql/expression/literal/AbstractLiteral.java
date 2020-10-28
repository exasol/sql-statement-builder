package com.exasol.sql.expression.literal;

import com.exasol.sql.expression.AbstractValueExpression;
import com.exasol.sql.expression.ValueExpressionVisitor;

/**
 * Abstract basis for {@link Literal}s.
 */
public abstract class AbstractLiteral extends AbstractValueExpression implements Literal {
    @Override
    public final void accept(final ValueExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
