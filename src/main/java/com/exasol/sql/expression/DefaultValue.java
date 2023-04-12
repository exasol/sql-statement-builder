package com.exasol.sql.expression;

/**
 * The {@link DefaultValue} is an expression that tells the database to take the default value for a column (in case it
 * is defined).
 * <p>
 * This is for example used in updates embedded into {@code MERGE} statements.
 * </p>
 */
// [impl->dsn~literal-values~2]
public final class DefaultValue implements ValueExpression {
    private static final DefaultValue instance = new DefaultValue();

    /**
     * Create a default value expression.
     *
     * @return default value expression
     */
    public static ValueExpression defaultValue() {
        return instance;
    }

    @Override
    public void accept(final ValueExpressionVisitor visitor) {
        visitor.visit(this);
    }
}