package com.exasol.datatype.type;

/**
 * Implements common logic for String data types
 *
 * @param <T> classes extended {@link AbstractStringDataType}
 */
public abstract class AbstractStringDataType<T extends AbstractStringDataType> implements DataType {
    private final String name;
    private final int length;

    protected AbstractStringDataType(final int length, final int maxLength, final String name) {
        validateLength(length, maxLength);
        this.length = length;
        this.name = name;
    }

    private void validateLength(final int length, final int maxLength) {
        if (length < 1 || length > maxLength) {
            throw new IllegalArgumentException(self().getName() + " should have length between 1 and " + maxLength);
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * @return pre-defined length for stored strings
     */
    public int getLength() {
        return this.length;
    }

    protected abstract T self();
}
