package com.exasol.sql.dml.merge;

import com.exasol.sql.AbstractFragment;
import com.exasol.sql.Fragment;

public class MergeInsertClause extends AbstractFragment implements MergeFragment {
    public MergeInsertClause(final Fragment root) {
        super(root);
    }

    @Override
    public void accept(final MergeVisitor visitor) {
        visitor.visit(this);
    }
}