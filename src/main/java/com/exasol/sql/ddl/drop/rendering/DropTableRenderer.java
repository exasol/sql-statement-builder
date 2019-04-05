package com.exasol.sql.ddl.drop.rendering;

import com.exasol.sql.Table;
import com.exasol.sql.ddl.drop.*;
import com.exasol.sql.rendering.AbstractFragmentRenderer;
import com.exasol.sql.rendering.StringRendererConfig;

/**
 * The {@link DropTableRenderer} turns SQL statement structures in to SQL strings.
 */
public class DropTableRenderer extends AbstractFragmentRenderer implements DropTableVisitor {
    /**
     * Create a new {@link DropTableRenderer} with custom render settings.
     *
     * @param config render configuration settings
     */
    public DropTableRenderer(final StringRendererConfig config) {
        super(config);
    }

    /**
     * Create an {@link DropTableRenderer} using the default renderer configuration
     *
     * @return insert renderer
     */
    public static DropTableRenderer create() {
        return new DropTableRenderer(StringRendererConfig.createDefault());
    }

    /**
     * Create an {@link DropTableRenderer}
     *
     * @param config renderer configuration
     * @return create table renderer
     */
    public static DropTableRenderer create(final StringRendererConfig config) {
        return new DropTableRenderer(config);
    }

    @Override
    public void visit(final DropTable dropTable) {
        appendKeyWord("DROP TABLE ");
        if (dropTable.getIfExists()) {
            appendKeyWord("IF EXISTS ");
        }
        setLastVisited(dropTable);
    }

    @Override
    public void visit(final CascadeConstraints cascadeConstraints) {
        appendKeyWord(" CASCADE CONSTRAINTS");
    }

    @Override
    public void visit(final Table table) {
        appendAutoQuoted(table.getName());
        setLastVisited(table);
    }
}
