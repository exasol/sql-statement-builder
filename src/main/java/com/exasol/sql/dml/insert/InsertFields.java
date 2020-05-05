package com.exasol.sql.dml.insert;

import java.util.ArrayList;
import java.util.List;

import com.exasol.sql.*;
import com.exasol.sql.dml.merge.MergeFragment;
import com.exasol.sql.dml.merge.MergeVisitor;

/**
 * Field list that defines the fields data is being inserted into.
 */
public class InsertFields extends AbstractFragment implements InsertFragment, MergeFragment {
    private final List<DerivedColumn> derivedColumns = new ArrayList<>();

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
            final DerivedColumn derivedColumn = new DerivedColumn(getRoot(), new Field(name));
            this.derivedColumns.add(derivedColumn);
        }
    }

    @Override
    public void accept(final InsertVisitor visitor) {
        visitor.visit(this);
        for (final DerivedColumn derivedColumn : this.derivedColumns) {
            derivedColumn.accept(visitor);
        }
        visitor.leave(this);
    }

    @Override
    public void accept(final MergeVisitor visitor) {
        visitor.visit(this);
        for (final DerivedColumn derivedColumn : this.derivedColumns) {
            derivedColumn.accept(visitor);
        }
        visitor.leave(this);
    }
}