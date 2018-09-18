package com.exasol.sql.dql;

import com.exasol.sql.*;

public class Field extends AbstractFragement implements FieldDefinition {
    private final String name;

    protected Field(final Fragment parent, final String name) {
        super(parent);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static Field all(final Fragment parent) {
        return new Field(parent, "*");
    }

    @Override
    protected void acceptConcrete(final FragmentVisitor visitor) {
        visitor.visit(this);
    }
}
