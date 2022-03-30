package com.exasol.sql;

import com.exasol.datatype.type.DataType;

/**
 * This class represents a column in an SQL statement
 */
public class Column extends AbstractFragment {
    private final String columnName;
    private final DataType dataType;

    /**
     * Create a new instance of a {@link Column}
     *
     * @param root root SQL statement
     * @param columnName column name
     * @param dataType data type
     */
    public Column(final Fragment root, final String columnName, final DataType dataType) {
        super(root);
        this.columnName = columnName;
        this.dataType = dataType;
    }

    /**
     * Get the column name
     *
     * @return column name
     */
    public String getColumnName() {
        return this.columnName;
    }

    /**
     * Get the column data type
     *
     * @return {@link DataType}
     */
    public DataType getDataType() {
        return this.dataType;
    }

    /**
     * Accept a {@link ColumnDefinitionVisitor}.
     *
     * @param visitor visitor to accept
     */
    public void accept(final ColumnDefinitionVisitor visitor) {
        visitor.visit(this);
        this.dataType.accept(visitor);
    }
}