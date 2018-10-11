package com.exasol.sql.dml;

import java.util.ArrayList;
import java.util.List;

import com.exasol.sql.*;

public class InsertFields extends AbstractFragment implements InsertFragment {
    private final List<Field> fields = new ArrayList<>();

    /**
     * Create an new instance of {@link InsertFields}
     *
     * @param root
     */
    public InsertFields(final SqlStatement root) {
        super(root);
    }

    /**
     * Define fields into which should be inserted
     *
     * @param names field names
     */
    void add(final String... names) {
        for (final String name : names) {
            this.fields.add(new Field(getRoot(), name));
        }
    }

    @Override
    public Fragment getRoot() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void accept(final InsertVisitor visitor) {
        visitor.visit(this);
        for (final Field field : this.fields) {
            field.accept(visitor);
        }
        visitor.leave(this);
    }
}