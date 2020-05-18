package com.exasol.sql.ddl.create.rendering;

import com.exasol.sql.ColumnsDefinition;
import com.exasol.sql.Table;
import com.exasol.sql.ddl.create.CreateTable;
import com.exasol.sql.ddl.create.CreateTableVisitor;
import com.exasol.sql.rendering.*;

/**
 * The {@link CreateTableRenderer} turns SQL statement structures in to SQL strings.
 */
// [impl->dsn~rendering.sql.create~1]
public class CreateTableRenderer extends AbstractFragmentRenderer implements CreateTableVisitor {
    /**
     * Create a new {@link CreateTableRenderer} with custom render settings.
     *
     * @param config render configuration settings
     */
    public CreateTableRenderer(final StringRendererConfig config) {
        super(config);
    }

    /**
     * Create an {@link CreateTableRenderer} using the default renderer configuration
     *
     * @return CREATE TABLE renderer
     */
    public static CreateTableRenderer create() {
        return new CreateTableRenderer(StringRendererConfig.createDefault());
    }

    /**
     * Create an {@link CreateTableRenderer}
     *
     * @param config renderer configuration
     * @return CREATE TABLE renderer
     */
    public static CreateTableRenderer create(final StringRendererConfig config) {
        return new CreateTableRenderer(config);
    }

    @Override
    public void visit(final CreateTable createTable) {
        appendKeyWord("CREATE TABLE ");
    }

    @Override
    public void leave(final CreateTable createTable) {
        appendColumnReference(createTable.getColumns());
        setLastVisited(createTable);
    }

    private void appendColumnReference(final ColumnsDefinition columns) {
        final ColumnsDefinitionRenderer renderer = new ColumnsDefinitionRenderer(this.config);
        columns.accept(renderer);
        append(renderer.render());
    }

    @Override
    public void visit(final Table table) {
        appendAutoQuoted(table.getName());
        setLastVisited(table);
    }
}