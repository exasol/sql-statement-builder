package com.exasol.sql.expression;

import com.exasol.sql.expression.literal.BooleanLiteral;

/**
 * This class implements the logical unary NOT predicate.
 */
public class Not extends AbstractBooleanExpression {

    /**
     * Create a new instance of a unary {@link Not} from a boolean literal
     *
     * @param value boolean literal to be negated
     */
    public Not(final boolean value) {
        this(BooleanLiteral.of(value));
    }

    /**
     * Create a new instance of a unary {@link Not} from a string literal
     *
     * @param value string literal to be negated
     */
    public Not(final String value) {
        this(BooleanLiteral.of(value));
    }

    /**
     * Create a new instance of a unary {@link Not} from a boolean expression
     *
     * @param expression boolean expression literal to be negated
     */
    public Not(final BooleanExpression expression) {
        super(expression);
    }

    /**
     * Get the boolean expression literal to be negated.
     * 
     * @return boolean expression literal to be negated
     */
    public BooleanExpression getNegated() {
        return this.getChild(0);
    }

    @Override
    public void accept(final BooleanExpressionVisitor visitor) {
        visitor.visit(this);
    }
}