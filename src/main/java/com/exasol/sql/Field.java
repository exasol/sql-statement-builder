package com.exasol.sql;

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

    public void accept(final TableValuesVisitor visitor) {
        visitor.visit(this);
    }
}