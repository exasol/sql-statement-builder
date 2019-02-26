package com.exasol.datatype;

import com.exasol.sql.ddl.CreateTableVisitor;

/**
 * This class represents different data types in Exasol database
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
