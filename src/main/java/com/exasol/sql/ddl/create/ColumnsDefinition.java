package com.exasol.sql.ddl.create;

import java.util.ArrayList;
import java.util.List;

import com.exasol.datatype.type.DataType;
import com.exasol.sql.AbstractFragment;
import com.exasol.sql.SqlStatement;

/**
 * This class represents a list of column definitions in an SQL statement
 */
public class ColumnsDefinition extends AbstractFragment implements CreateTableFragment {
    private final List<Column> columns = new ArrayList<>();

    /**
     * Create an new instance of {@link ColumnsDefinition}
     *
     * @param root root statement
     */
    protected ColumnsDefinition(final SqlStatement root) {
        super(root);
    }

    /**
     * Add a new column to the {@link ColumnsDefinition}
     *
     * @param name     name of the column to be added
     * @param dataType data type of the column to be added
     */
    public void add(final String name, final DataType dataType) {
        this.columns.add(new Column(this, name, dataType));
    }

    protected List<Column> getColumns() {
        return this.columns;
    }

    @Override
    public void accept(final CreateTableVisitor visitor) {
        visitor.visit(this);
        for (final Column column : this.columns) {
            column.accept(visitor);
        }
        visitor.leave(this);
    }
}