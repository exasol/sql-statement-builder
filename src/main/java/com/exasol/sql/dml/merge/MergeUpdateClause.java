package com.exasol.sql.dml.merge;

import java.util.ArrayList;
import java.util.List;

import com.exasol.sql.Fragment;
import com.exasol.sql.dql.select.WhereClause;
import com.exasol.sql.expression.*;

/**
 * Represents the {@code MERGE} strategy of updating matched rows.
 */
public class MergeUpdateClause extends MergeMethodDefinition implements MergeFragment {
    private final List<MergeColumnUpdate> columnUpdates = new ArrayList<>();

    /**
     * Create a new instance of a {@link MergeUpdateClause}.
     *
     * @param root root SQL statement this {@code THEN UPDATE} clause belongs to
     */
    public MergeUpdateClause(final Fragment root) {
        super(root);
    }

    /**
     * Update a column with a string value.
     *
     * @param column column to be updated
     * @param literal string literal
     * @return {@code this} for fluent programming
     */
    public MergeUpdateClause set(final String column, final String literal) {
        addColumnUpdate(column, StringLiteral.of(literal));
        return this;
    }

    protected void addColumnUpdate(final String column, final ValueExpression expression) {
        this.columnUpdates.add(new MergeColumnUpdate(this.root, column, expression));
    }

    /**
     * Update a column with an integer value.
     *
     * @param column column to be updated
     * @param literal integer literal
     * @return {@code this} for fluent programming
     */
    public MergeUpdateClause set(final String column, final int literal) {
        addColumnUpdate(column, IntegerLiteral.of(literal));
        return this;
    }

    /**
     * Update a column with the default value defined for that column.
     *
     * @param column column to be updated
     * @return {@code this} for fluent programming
     */
    public MergeUpdateClause setToDefault(final String column) {
        this.columnUpdates.add(new MergeColumnUpdate(this.root, column, DefaultValue.defaultValue()));
        return this;
    }

    /**
     * Add a {@code WHERE} clause to the update definition.
     *
     * @param expression filter expression
     * @return parent {@code MERGE} statement
     */
    public Merge where(final BooleanExpression expression) {
        final Merge merge = (Merge) this.getRoot();
        this.where = new WhereClause(merge, expression);
        return merge;
    }

    @Override
    public void accept(final MergeVisitor visitor) {
        visitor.visit(this);
        for (final MergeColumnUpdate columnUpdate : this.columnUpdates) {
            columnUpdate.accept(visitor);
        }
        if (hasWhere()) {
            this.where.accept(visitor);
        }
    }
}