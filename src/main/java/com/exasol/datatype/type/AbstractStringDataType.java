package com.exasol.datatype.type;

/**
 * Implements common logic for String data types.
 *
 * @param <T> classes extended {@link AbstractStringDataType}
 */
public abstract class AbstractStringDataType<T extends AbstractStringDataType<T>> implements DataType {
    private final String name;
    private final int length;

    /**
     * Create a new common base for a string type.
     *
     * @param length length of the string
     * @param maxLength maximum length
     * @param name name of the type
     */
    protected AbstractStringDataType(final int length, final int maxLength, final String name) {
        validateLength(length, maxLength);
        this.length = length;
        this.name = name;
    }

    private void validateLength(final int length, final int maxLength) {
        if ((length < 1) || (length > maxLength)) {
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

    /**
     * Get the generic self pointer.
     *
     * @return self pointer
     */
    protected abstract T self();
}