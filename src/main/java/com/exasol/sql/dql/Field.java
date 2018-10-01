package com.exasol.sql.dql;

import com.exasol.sql.AbstractFragment;
import com.exasol.sql.FragmentVisitor;

public class Field extends AbstractFragment implements FieldDefinition {
    private final String name;

    protected Field(final String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static Field all() {
        return new Field("*");
    }

    @Override
    protected void acceptConcrete(final FragmentVisitor visitor) {
        visitor.visit(this);
    }
}
