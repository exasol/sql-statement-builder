package com.exasol.sql.dml.merge;

import com.exasol.sql.AbstractFragment;
import com.exasol.sql.Fragment;
import com.exasol.sql.expression.ValueExpression;

/**
 * Update of a value in a column caused by a a {@code MERGE} command.
 */
public class MergeColumnUpdate extends AbstractFragment implements MergeFragment {
    private final String column;
    private final ValueExpression expression;

    /**
     * Create a new instance of a {@link MergeColumnUpdate}.
     *
     * @param root root SQL statement this column update belongs to
     * @param column column to be updated
     * @param expression expression that serves as the value for the update
     */
    public MergeColumnUpdate(final Fragment root, final String column, final ValueExpression expression) {
        super(root);
        this.column = column;
        this.expression = expression;
    }

    /**
     * Get the column to be updated.
     *
     * @return column
     */
    public String getColumn() {
        return this.column;
    }

    /**
     * Get the expression that serves as the value for the update.
     *
     * @return value expression
     */
    public ValueExpression getExpression() {
        return this.expression;
    }

    @Override
    public void accept(final MergeVisitor visitor) {
        visitor.visit(this);
    }
}