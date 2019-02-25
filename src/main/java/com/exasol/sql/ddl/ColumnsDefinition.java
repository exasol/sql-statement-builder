package com.exasol.sql.ddl;

import com.exasol.datatype.DataType;
import com.exasol.sql.AbstractFragment;
import com.exasol.sql.Column;
import com.exasol.sql.SqlStatement;

import java.util.ArrayList;
import java.util.List;

public class ColumnsDefinition extends AbstractFragment implements CreateTableFragment {
    private final List<Column> columns = new ArrayList<>();

    /**
     * Create an new instance of {@link ColumnsDefinition}
     *
     * @param root root statement
     */
    public ColumnsDefinition(final SqlStatement root) {
        super(root);
    }

    public void add(final String name, final DataType dataType) {
        this.columns.add(new Column(this, name, dataType));
    }

    public List<Column> getColumns() {
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