package com.exasol.sql.expression.literal;

import com.exasol.sql.expression.ValueExpression;

/**
 * Interface for classes that represent literal values.
 */
public interface Literal extends ValueExpression {
    /**
     * Accept a {@link LiteralVisitor}.
     * 
     * @param visitor visitor to accept
     */
    public void accept(final LiteralVisitor visitor);
}
