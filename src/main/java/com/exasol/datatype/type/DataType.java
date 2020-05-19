package com.exasol.datatype.type;

import com.exasol.sql.ColumnDefinitionVisitor;

/**
 * This class represents different SQL data types
 */
public interface DataType {

    /**
     * @param visitor instance of {@link ColumnDefinitionVisitor}
     */
    public void accept(final ColumnDefinitionVisitor visitor);

    /**
     * @return SQL representation of data type name
     */
    public String getName();
}