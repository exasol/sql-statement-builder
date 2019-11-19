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
    private NotMatchedClause notMatched;

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
     * Create a new instance of a {@link Merge}.
     *
     * @param destinationTable table into which the data should be merged
     * @param as table alias
     */
    public Merge(final String destinationTable, final String as) {
        super(null);
        this.destinationTable = new Table(this, destinationTable, as);
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
     * Define the data source.
     *
     * @param sourceTable table where the data to be merged originates
     * @param as table alias
     * @return {@code this} for fluent programming
     */
    public Merge using(final String sourceTable, final String as) {
        this.using = new UsingClause(this, sourceTable, as);
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

    /**
     * Define the merge strategy if the match criteria is not met.
     *
     * @return not matched strategy
     */
    public NotMatchedClause whenNotMatched() {
        this.notMatched = new NotMatchedClause(this.root);
        return this.notMatched;
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
        if (this.notMatched != null) {
            this.notMatched.accept(visitor);
        }
    }
}