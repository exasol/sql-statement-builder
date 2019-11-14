package com.exasol.datatype.type;

import com.exasol.sql.ddl.create.CreateTableVisitor;

/**
 * This class represents different SQL data types
 */
public interface DataType {

    /**
     * @param visitor instance of {@link CreateTableVisitor}
     */
    public void accept(final CreateTableVisitor visitor);

    /**
     * @return SQL representation of data type name
     */
    public String getName();
}