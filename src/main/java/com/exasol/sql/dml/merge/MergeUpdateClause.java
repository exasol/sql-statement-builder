package com.exasol.sql.dml.merge;

import java.util.ArrayList;
import java.util.List;

import com.exasol.sql.Fragment;
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
     * Update a column with a value expression.
     *
     * @param column column to be updated
     * @param expression value expression
     * @return {@code this} for fluent programming
     */
    public MergeUpdateClause set(final String column, final ValueExpression expression) {
        this.columnUpdates.add(new MergeColumnUpdate(this.root, column, expression));
        return this;
    }

    /**
     * Update a column with a string value.
     *
     * @param column column to be updated
     * @param literal string literal
     * @return {@code this} for fluent programming
     */
    public MergeUpdateClause set(final String column, final String literal) {
        return set(column, StringLiteral.of(literal));
    }

    /**
     * Update a column with a char value.
     *
     * @param column column to be updated
     * @param literal char literal
     * @return {@code this} for fluent programming
     */
    public MergeUpdateClause set(final String column, final char literal) {
        return set(column, StringLiteral.of(literal));
    }

    /**
     * Update a column with an integer value.
     *
     * @param column column to be updated
     * @param literal integer literal
     * @return {@code this} for fluent programming
     */
    public MergeUpdateClause set(final String column, final int literal) {
        return set(column, IntegerLiteral.of(literal));
    }

    /**
     * Update a column with a long value.
     *
     * @param column column to be updated
     * @param literal long literal
     * @return {@code this} for fluent programming
     */
    public MergeUpdateClause set(final String column, final long literal) {
        return set(column, LongLiteral.of(literal));
    }

    /**
     * Update a column with a double value.
     *
     * @param column column to be updated
     * @param literal double literal
     * @return {@code this} for fluent programming
     */
    public MergeUpdateClause set(final String column, final double literal) {
        return set(column, DoubleLiteral.of(literal));
    }

    /**
     * Update a column with a float value.
     *
     * @param column column to be updated
     * @param literal float literal
     * @return {@code this} for fluent programming
     */
    public MergeUpdateClause set(final String column, final float literal) {
        return set(column, FloatLiteral.of(literal));
    }

    /**
     * Update a column with a boolean value.
     *
     * @param column column to be updated
     * @param literal boolean literal
     * @return {@code this} for fluent programming
     */
    public MergeUpdateClause set(final String column, final boolean literal) {
        return set(column, BooleanLiteral.of(literal));
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