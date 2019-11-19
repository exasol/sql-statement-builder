package com.exasol.sql.dml.merge;

import com.exasol.sql.Fragment;
import com.exasol.sql.dml.insert.AbstractInsertValueTable;

public class MergeInsertClause extends AbstractInsertValueTable<MergeInsertClause> implements MergeFragment {
    public MergeInsertClause(final Fragment root) {
        super(root);
    }

    @Override
    protected MergeInsertClause self() {
        return this;
    }

    @Override
    public void accept(final MergeVisitor visitor) {
        visitor.visit(this);
        if (this.insertFields != null) {
            this.insertFields.accept(visitor);
        }
        if (this.insertValueTable != null) {
            this.insertValueTable.accept(visitor);
        }
    }
}