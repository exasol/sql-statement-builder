package com.exasol.sql;

import com.exasol.datatype.DataType;

public class Column extends AbstractFragment implements GenericFragment {
    private final String columnName;
    private final DataType dataType;

    public Column(final Fragment root, final String columnName, final DataType dataType) {
        super(root);
        this.columnName = columnName;
        this.dataType = dataType;
    }

    public String getColumnName() {
        return this.columnName;
    }

    public DataType getDataType() {
        return this.dataType;
    }

    @Override
    public void accept(final FragmentVisitor visitor) {

    }
}
