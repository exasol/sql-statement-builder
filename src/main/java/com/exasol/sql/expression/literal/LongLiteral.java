package com.exasol.sql.expression.literal;

/**
 * This class represents long literals.
 */
// [impl->dsn~literal-values~2]
public class LongLiteral extends AbstractLiteral {
    private final long value;

    private LongLiteral(final long value) {
        this.value = value;
    }

    /**
     * Create a new {@link LongLiteral} from a long.
     *
     * @param value content
     * @return new {@link LongLiteral}
     */
    public static LongLiteral of(final long value) {
        return new LongLiteral(value);
    }

    /**
     * Get the value of the {@link LongLiteral}
     *
     * @return long value
     */
    public long getValue() {
        return this.value;
    }

    @Override
    public void accept(final LiteralVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return Long.toString(this.value);
    }
}
