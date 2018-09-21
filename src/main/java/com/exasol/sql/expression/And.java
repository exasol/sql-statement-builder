package com.exasol.sql.expression;

/**
 * This class represents
 */
public class And extends AbstractBooleanExpression {

    public And(final BooleanExpression... expressions) {
        super(expressions);
    }

    public And(final String... strings) {
        this(mapLiterals(strings));
    }

    private static BooleanExpression[] mapLiterals(final String[] strings) {
        final BooleanExpression[] literals = new BooleanExpression[strings.length];
        for (int i = 0; i < strings.length; ++i) {
            literals[i] = Literal.of(strings[i]);
        }
        return literals;
    }

    @Override
    public void acceptConcrete(final BooleanExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
