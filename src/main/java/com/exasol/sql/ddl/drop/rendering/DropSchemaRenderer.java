package com.exasol.sql.ddl.drop.rendering;

import com.exasol.sql.ddl.Schema;
import com.exasol.sql.ddl.drop.*;
import com.exasol.sql.rendering.AbstractFragmentRenderer;
import com.exasol.sql.rendering.StringRendererConfig;

/**
 * The {@link DropSchemaRenderer} turns SQL statement structures in to SQL strings.
 */
public class DropSchemaRenderer extends AbstractFragmentRenderer implements DropSchemaVisitor {
    /**
     * Create a new {@link DropSchemaRenderer} with custom render settings.
     *
     * @param config render configuration settings
     */
    public DropSchemaRenderer(final StringRendererConfig config) {
        super(config);
    }

    /**
     * Create an {@link DropSchemaRenderer} using the default renderer configuration
     *
     * @return DROP SCHEMA renderer
     */
    public static DropSchemaRenderer create() {
        return new DropSchemaRenderer(StringRendererConfig.createDefault());
    }

    /**
     * Create an {@link DropSchemaRenderer}
     *
     * @param config renderer configuration
     * @return DROP SCHEMA renderer
     */
    public static DropSchemaRenderer create(final StringRendererConfig config) {
        return new DropSchemaRenderer(config);
    }

    @Override
    public void visit(DropSchema dropSchema) {
        appendKeyWord("DROP SCHEMA ");
        if (dropSchema.getIfExists()) {
            appendKeyWord("IF EXISTS ");
        }
        setLastVisited(dropSchema);
    }

    @Override
    public void visit(Schema schema) {
        appendAutoQuoted(schema.getName());
        setLastVisited(schema);
    }

    @Override
    public void visit(Cascade cascade) {
        appendKeyWord(" CASCADE");
        setLastVisited(cascade);
    }

    @Override
    public void visit(Restrict restrict) {
        appendKeyWord(" RESTRICT");
        setLastVisited(restrict);
    }
}
