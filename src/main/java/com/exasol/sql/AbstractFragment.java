package com.exasol.sql;

import com.exasol.util.AbstractTreeNode;
import com.exasol.util.TreeNode;

/**
 * This class provides an abstract base for SQL statement fragments. It also
 * keeps track of the relationships to other fragments.
 *
 * @param <T> the type of the concrete class implementing the missing parts.
 */
public abstract class AbstractFragment extends AbstractTreeNode implements Fragment {
    protected AbstractFragment() {
        super();
    }

    @Override
    public void accept(final FragmentVisitor visitor) {
        acceptConcrete(visitor);
        for (final TreeNode child : this.getChildren()) {
            ((Fragment) child).accept(visitor);
        }
    }

    protected abstract void acceptConcrete(final FragmentVisitor visitor);
}