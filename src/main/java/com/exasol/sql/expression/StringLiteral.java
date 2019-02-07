package com.exasol.sql.expression;

import javax.annotation.Generated;

/**
 * This class represents string literals (or character literals in SQL terms).
 */
public class StringLiteral extends AbstractValueExpression {
    private final String literal;

    private StringLiteral(final String literal) {
        this.literal = literal;
    }

    /**
     * Create a new {@link StringLiteral} from a string
     *
     * @param literal content
     * @return new {@link StringLiteral}
     */
    public static StringLiteral of(final String literal) {
        return new StringLiteral(literal);
    }

    @Override
    public void accept(final ValueExpressionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return this.literal;
    }

    @Generated("org.eclipse.Eclipse")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.literal == null) ? 0 : this.literal.hashCode());
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
        final StringLiteral other = (StringLiteral) obj;
        if (this.literal == null) {
            if (other.literal != null) {
                return false;
            }
        } else if (!this.literal.equals(other.literal)) {
            return false;
        }
        return true;
    }
}