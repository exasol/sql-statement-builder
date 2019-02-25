package com.exasol.datatype;

import com.exasol.sql.AbstractFragment;
import com.exasol.sql.Fragment;

public abstract class AbstractStringDataType extends AbstractFragment implements DataType {
    private final int length;

    public AbstractStringDataType(final Fragment root, final int length, final int maxLength,
          final String dataTypeName) {
        super(root);
        validateLength(length, maxLength, dataTypeName);
        this.length = length;
    }

    private void validateLength(final int length, final int maxLength, final String dataTypeName) {
        if (length < 1 || length > maxLength) {
            throw new IllegalArgumentException(
                  dataTypeName + " should have length between 1 and " + maxLength);
        }
    }

    public int getLength() {
        return this.length;
    }
}
