package com.exasol.sql.ddl.create.rendering;

import com.exasol.sql.ddl.Schema;
import com.exasol.sql.ddl.create.CreateSchema;
import com.exasol.sql.ddl.create.CreateSchemaVisitor;
import com.exasol.sql.rendering.AbstractFragmentRenderer;
import com.exasol.sql.rendering.StringRendererConfig;

/**
 * The {@link CreateSchemaRenderer} turns SQL statement structures in to SQL strings.
 */
public class CreateSchemaRenderer extends AbstractFragmentRenderer implements CreateSchemaVisitor {
    /**
     * Create a new {@link CreateSchemaRenderer} with custom render settings.
     *
     * @param config render configuration settings
     */
    public CreateSchemaRenderer(final StringRendererConfig config) {
        super(config);
    }

    /**
     * Create an {@link CreateSchemaRenderer} using the default renderer configuration
     *
     * @return CREATE SCHEMA renderer
     */
    public static CreateSchemaRenderer create() {
        return new CreateSchemaRenderer(StringRendererConfig.createDefault());
    }

    /**
     * Create an {@link CreateSchemaRenderer}
     *
     * @param config renderer configuration
     * @return CREATE SCHEMA renderer
     */
    public static CreateSchemaRenderer create(final StringRendererConfig config) {
        return new CreateSchemaRenderer(config);
    }

    @Override
    public void visit(CreateSchema createSchema) {
        appendKeyWord("CREATE SCHEMA ");
        setLastVisited(createSchema);
    }

    @Override
    public void visit(Schema schema) {
        appendAutoQuoted(schema.getName());
        setLastVisited(schema);
    }
}
