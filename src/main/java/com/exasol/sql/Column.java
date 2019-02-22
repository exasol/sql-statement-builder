package com.exasol.sql;

import com.exasol.datatype.DataType;
import com.exasol.sql.ddl.CreateTableFragment;
import com.exasol.sql.ddl.CreateTableVisitor;

public class Column extends AbstractFragment implements CreateTableFragment {
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
    public void accept(final CreateTableVisitor visitor) {
        visitor.visit(this);
        this.dataType.accept(visitor);
    }
}
