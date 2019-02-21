package com.exasol.sql.ddl;

import com.exasol.datatype.Boolean;
import com.exasol.sql.AbstractFragment;
import com.exasol.sql.Column;
import com.exasol.sql.Fragment;
import com.exasol.sql.Table;

import java.util.ArrayList;
import java.util.List;

public class CreateTable extends AbstractFragment {
    private final List<Column> columns;
    private final Table table;

    public CreateTable(final Fragment root, final String tableName) {
        super(root);
        this.table = new Table(this, tableName);
        this.columns = new ArrayList<>();
    }

    public String getTableName() {
        return this.table.getName();
    }

    public CreateTable booleanColumn(final String columnName) {
        this.columns.add(new Column(this, columnName, Boolean.bool()));
        return this;
    }

    public List<Column> getColumns() {
        return this.columns;
    }
}
