package com.exasol.sql.ddl.rendering;

import com.exasol.datatype.Boolean;
import com.exasol.datatype.*;
import com.exasol.sql.Column;
import com.exasol.sql.Field;
import com.exasol.sql.Table;
import com.exasol.sql.ddl.ColumnsDefinition;
import com.exasol.sql.ddl.CreateTable;
import com.exasol.sql.ddl.CreateTableVisitor;
import com.exasol.sql.rendering.AbstractFragmentRenderer;
import com.exasol.sql.rendering.StringRendererConfig;

public class CreateTableRenderer extends AbstractFragmentRenderer implements CreateTableVisitor {
    public CreateTableRenderer(final StringRendererConfig config) {
        super(config);
    }

    public static CreateTableRenderer create() {
        return new CreateTableRenderer(StringRendererConfig.createDefault());
    }

    public static CreateTableRenderer create(final StringRendererConfig config) {
        return new CreateTableRenderer(config);
    }

    @Override
    public void visit(final CreateTable createTable) {
        appendKeyWord("CREATE TABLE ");
        setLastVisited(createTable);
    }

    @Override
    public void visit(final Column column) {
        appendAutoQuoted(column.getColumnName());
        setLastVisited(column);
    }

    @Override
    public void visit(final ColumnsDefinition columnsDefinition) {
        append(" (");
        setLastVisited(columnsDefinition);
    }

    @Override
    public void leave(final ColumnsDefinition columnsDefinition) {
        append(")");
        setLastVisited(columnsDefinition);
    }

    @Override
    public void visit(final Char charColumn) {
        appendStringDataType(charColumn);
    }

    @Override
    public void visit(final Varchar varcharColumn) {
        appendStringDataType(varcharColumn);
    }

    @Override
    public void visit(final Boolean booleanColumn) {
        appendSpace();
        append(booleanColumn.getName());
    }

    @Override
    public void visit(final Date dateColumn) {
        appendSpace();
        append(dateColumn.getName());
    }

    @Override
    public void visit(final Field field) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void visit(final Table table) {
        appendAutoQuoted(table.getName());
        setLastVisited(table);
    }

    private void appendStringDataType(final AbstractStringDataType stringDataType) {
        appendSpace();
        append(stringDataType.getName());
        append("(");
        append(stringDataType.getLength());
        append(")");
        setLastVisited(stringDataType);
    }
}
