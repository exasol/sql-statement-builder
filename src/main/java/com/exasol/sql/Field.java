package com.exasol.sql;

import com.exasol.sql.dml.insert.InsertVisitor;
import com.exasol.sql.dml.merge.MergeVisitor;
import com.exasol.sql.dql.select.SelectVisitor;

/**
 * This class represents a table field in an SQL statement.
 */
public class Field extends AbstractFragment {
    private final String name;

    /**
     * Create a new instance of a {@link Field}
     *
     * @param root root SQL statement
     * @param name field name
     */
    public Field(final Fragment root, final String name) {
        super(root);
        this.name = name;
    }

    /**
     * Get the field name
     *
     * @return field name
     */
    public String getName() {
        return this.name;
    }

    public void accept(final InsertVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(final MergeVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(final SelectVisitor visitor) {
        visitor.visit(this);
    }
}