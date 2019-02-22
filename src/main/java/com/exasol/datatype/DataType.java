package com.exasol.datatype;

import com.exasol.sql.ddl.CreateTableVisitor;

public interface DataType {
    public void accept(final CreateTableVisitor visitor);
}
