package com.exasol.sql.dml.merge;

import com.exasol.sql.*;
import com.exasol.sql.expression.BooleanExpression;

/**
 * This class implements an SQL {@link Merge} statement which is a combination of {@code INSERT}, {@code UPDATE} and
 * {@code DELETE}.
 */
//[impl->dsn~merge-statements~1]
public class Merge extends AbstractFragment implements SqlStatement, MergeFragment {
    private final Table destinationTable;
    private UsingClause using;
    private OnClause on;
    private BooleanExpression condition;
    private MatchedClause matched;

    /**
     * Create a new instance of a {@link Merge}.
     *
     * @param destinationTable table into which the data should be merged
     */
    public Merge(final String destinationTable) {
        super(null);
        this.destinationTable = new Table(this, destinationTable);
    }

    /**
     * Define the data source.
     *
     * @param sourceTable table where the data to be merged originates
     * @return {@code this} for fluent programming
     */
    public Merge using(final String sourceTable) {
        this.using = new UsingClause(this, sourceTable);
        return this;
    }

    /**
     * Define the merge criteria.
     *
     * @param condition criteria that must be met for the rows in source and destination to be considered a match.
     * @return {@code this} for fluent programming
     */
    public Merge on(final BooleanExpression condition) {
        this.on = new OnClause(this, condition);
        return this;
    }

    /**
     * Define the merge strategy if the match criteria is met.
     *
     * @return match strategy
     */
    public MatchedClause whenMatched() {
        this.matched = new MatchedClause(this.root);
        return this.matched;
    }

    public MergeInsertClause whenNotMatchedThenInsert() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Get the {@code USING} clause of the {@code MERGE} statement.
     *
     * @return destination table
     */
    public UsingClause getUsing() {
        return this.using;
    }

    /**
     * Get the merge condition.
     *
     * @return criteria that must be met for the rows in source and destination to be considered a match.
     */
    public BooleanExpression getCondition() {
        return this.condition;
    }

    @Override
    public void accept(final MergeVisitor visitor) {
        visitor.visit(this);
        if (this.destinationTable != null) {
            this.destinationTable.accept(visitor);
        }
        if (this.using != null) {
            this.using.accept(visitor);
        }
        if (this.on != null) {
            this.on.accept(visitor);
        }
        if (this.matched != null) {
            this.matched.accept(visitor);
        }
    }
}