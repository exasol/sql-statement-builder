package com.exasol.sql.expression;

import javax.annotation.Generated;

/**
 * This class represents integer literals (or character literals in SQL terms).
 */
public class IntegerLiteral extends AbstractValueExpression {
    private final int literal;

    private IntegerLiteral(final int literal) {
        this.literal = literal;
    }

    /**
     * Create a new {@link IntegerLiteral} from an integer
     *
     * @param literal content
     * @return new {@link IntegerLiteral}
     */
    public static IntegerLiteral of(final int literal) {
        return new IntegerLiteral(literal);
    }

    @Override
    public void accept(final ValueExpressionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return Integer.toString(this.literal);
    }

    @Generated("org.eclipse.Eclipse")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.literal;
        return result;
    }

    @Generated("org.eclipse.Eclipse")
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final IntegerLiteral other = (IntegerLiteral) obj;
        if (this.literal != other.literal) {
            return false;
        }
        return true;
    }
}