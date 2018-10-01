package com.exasol.sql.dql;

import com.exasol.sql.AbstractFragment;
import com.exasol.sql.FragmentVisitor;
import com.exasol.sql.expression.BooleanExpression;

/**
 * This class represents the FROM clause of an SQL SELECT statement.
 */
public class FromClause extends AbstractFragment {
    /**
     * Create a new instance of a {@link FromClause}
     */
    public FromClause() {
        super();
    }

    /**
     * Create a {@link FromClause} from a table name
     *
     * @param name table name
     * @return new instance
     */
    public FromClause from(final String name) {
        addChild(new Table(name));
        return this;
    }

    /**
     * Create a {@link FromClause} from a table name and an alias
     *
     * @param name table name
     * @param as table alias
     * @return new instance
     */
    public FromClause fromTableAs(final String name, final String as) {
        addChild(new Table(name, as));
        return this;
    }

    /**
     * Create a new {@link Join} that belongs to a FROM clause
     *
     * @param name name of the table to be joined
     * @param specification join conditions
     * @return parent FROM clause
     */
    public FromClause join(final String name, final String specification) {
        addChild(new Join(JoinType.DEFAULT, name, specification));
        return this;
    }

    /**
     * Create a new inner {@link Join} that belongs to a FROM clause
     *
     * @param name name of the table to be joined
     * @param specification join conditions
     * @return parent FROM clause
     */
    public FromClause innerJoin(final String name, final String specification) {
        addChild(new Join(JoinType.INNER, name, specification));
        return this;
    }

    /**
     * Create a new left {@link Join} that belongs to a FROM clause
     *
     * @param name name of the table to be joined
     * @param specification join conditions
     * @return parent FROM clause
     */
    public FromClause leftJoin(final String name, final String specification) {
        addChild(new Join(JoinType.LEFT, name, specification));
        return this;
    }

    /**
     * Create a new right {@link Join} that belongs to a FROM clause
     *
     * @param name name of the table to be joined
     * @param specification join conditions
     * @return parent FROM clause
     */
    public FromClause rightJoin(final String name, final String specification) {
        addChild(new Join(JoinType.RIGHT, name, specification));
        return this;
    }

    /**
     * Create a new full {@link Join} that belongs to a FROM clause
     *
     * @param name name of the table to be joined
     * @param specification join conditions
     * @return parent FROM clause
     */
    public FromClause fullJoin(final String name, final String specification) {
        addChild(new Join(JoinType.FULL, name, specification));
        return this;
    }

    /**
     * Create a new left outer {@link Join} that belongs to a FROM clause
     *
     * @param name name of the table to be joined
     * @param specification join conditions
     * @return parent FROM clause
     */
    public FromClause leftOuterJoin(final String name, final String specification) {
        addChild(new Join(JoinType.LEFT_OUTER, name, specification));
        return this;
    }

    /**
     * Create a new right outer {@link Join} that belongs to a FROM clause
     *
     * @param name name of the table to be joined
     * @param specification join conditions
     * @return parent FROM clause
     */
    public FromClause rightOuterJoin(final String name, final String specification) {
        addChild(new Join(JoinType.RIGHT_OUTER, name, specification));
        return this;
    }

    /**
     * Create a new full outer {@link Join} that belongs to a FROM clause
     *
     * @param name name of the table to be joined
     * @param specification join conditions
     * @return parent FROM clause
     */
    public FromClause fullOuterJoin(final String name, final String specification) {
        addChild(new Join(JoinType.FULL_OUTER, name, specification));
        return this;
    }

    /**
     * Create a new full outer {@link LimitClause}
     *
     * @param count maximum number of rows to be included in query result
     * @return new instance
     */
    public LimitClause limit(final int count) {
        final LimitClause limitClause = new LimitClause(count);
        addChild(limitClause);
        return limitClause;
    }

    /**
     * Create a new full outer {@link LimitClause}
     *
     * @param offset index of the first row in the query result
     * @param count maximum number of rows to be included in query result
     * @return new instance
     */
    public LimitClause limit(final int offset, final int count) {
        final LimitClause limitClause = new LimitClause(offset, count);
        addChild(limitClause);
        return limitClause;
    }

    /**
     * Create a new {@link WhereClause}
     *
     * @param expression boolean expression that defines the filter criteria
     * @return new instance
     */
    public WhereClause where(final BooleanExpression expression) {
        final WhereClause whereClause = new WhereClause(expression);
        addChild(whereClause);
        return whereClause;
    }

    @Override
    protected void acceptConcrete(final FragmentVisitor visitor) {
        visitor.visit(this);
    }
}