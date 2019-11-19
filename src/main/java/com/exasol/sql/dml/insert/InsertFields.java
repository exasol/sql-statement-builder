package com.exasol.sql.dml.insert;

import java.util.ArrayList;
import java.util.List;

import com.exasol.sql.*;
import com.exasol.sql.dml.merge.MergeFragment;
import com.exasol.sql.dml.merge.MergeVisitor;

public class InsertFields extends AbstractFragment implements InsertFragment, MergeFragment {
    private final List<Field> fields = new ArrayList<>();

    /**
     * Create an new instance of {@link InsertFields}
     *
     * @param root root statement
     */
    public InsertFields(final Fragment root) {
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
    public void accept(final InsertVisitor visitor) {
        visitor.visit(this);
        for (final Field field : this.fields) {
            field.accept(visitor);
        }
        visitor.leave(this);
    }

    @Override
    public void accept(final MergeVisitor visitor) {
        visitor.visit(this);
        for (final Field field : this.fields) {
            field.accept(visitor);
        }
        visitor.leave(this);
    }
}