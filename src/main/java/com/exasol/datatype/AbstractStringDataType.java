package com.exasol.datatype;

import com.exasol.sql.AbstractFragment;
import com.exasol.sql.Fragment;

public abstract class AbstractStringDataType<T extends AbstractStringDataType>
      extends AbstractFragment implements DataType {
    private final int length;

    public AbstractStringDataType(final Fragment root, final int length, final int maxLength) {
        super(root);
        validateLength(length, maxLength);
        this.length = length;
    }

    private void validateLength(final int length, final int maxLength) {
        if (length < 1 || length > maxLength) {
            throw new IllegalArgumentException(
                  self().getName() + " should have length between 1 and " + maxLength);
        }
    }

    public int getLength() {
        return this.length;
    }

    protected abstract T self();
}
