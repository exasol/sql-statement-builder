package com.exasol.sql;

import com.exasol.sql.dml.insert.InsertFragment;
import com.exasol.sql.dml.insert.InsertVisitor;
import com.exasol.sql.dml.merge.MergeFragment;
import com.exasol.sql.dml.merge.MergeVisitor;
import com.exasol.sql.dql.select.SelectFragment;
import com.exasol.sql.dql.select.SelectVisitor;
import com.exasol.sql.expression.ValueExpression;

/**
 * This class represents a derived column in an SQL query.
 */
public class DerivedColumn extends AbstractFragment implements SelectFragment, MergeFragment, InsertFragment {
    private final ValueExpression valueExpression;

    /**
     * Create a new instance of a {@link DerivedColumn}.
     *
     * @param root root SQL statement this fragment belongs to
     * @param valueExpression derived column's content
     */
    public DerivedColumn(final Fragment root, final ValueExpression valueExpression) {
        super(root);
        this.valueExpression = valueExpression;
    }

    /**
     * Get a value expression that belongs to this derived column.
     * 
     * @return a value expression
     */
    public ValueExpression getValueExpression() {
        return valueExpression;
    }

    @Override
    public void accept(final SelectVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void accept(final InsertVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void accept(final MergeVisitor visitor) {
        visitor.visit(this);
    }
}