package com.exasol.sql.dql;

import java.util.ArrayList;
import java.util.List;

import com.exasol.sql.*;

/**
 * This class represents the FROM clause of an SQL SELECT statement.
 */
public class FromClause extends AbstractFragment implements SelectFragment {
    private final List<Table> tables = new ArrayList<>();
    private final List<Join> joins = new ArrayList<>();
    private final List<ValueTable> valueTables = new ArrayList<>();

    /**
     * Create a new instance of a {@link FromClause}
     *
     * @param root root SQL statement this FROM clause belongs to
     */
    public FromClause(final Fragment root) {
        super(root);
    }

    /**
     * Add a table name to the {@link FromClause}
     *
     * @param name table name
     * @return FROM clause
     */
    public FromClause table(final String name) {
        this.tables.add(new Table(getRoot(), name));
        return this;
    }

    /**
     * Add a table name with an an alias to the {@link FromClause}
     *
     * @param name table name
     * @param as table alias
     * @return FROM clause
     */
    public FromClause tableAs(final String name, final String as) {
        this.tables.add(new Table(getRoot(), name, as));
        return this;
    }

    /**
     * Create a {@link FromClause} from a value table
     *
     * @param valueTable table of value expressions
     * @return new instance
     */
    public FromClause valueTable(final ValueTable valueTable) {
        this.valueTables.add(valueTable);
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
        this.joins.add(new Join(getRoot(), JoinType.DEFAULT, name, specification));
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
        this.joins.add(new Join(getRoot(), JoinType.INNER, name, specification));
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
        this.joins.add(new Join(getRoot(), JoinType.LEFT, name, specification));
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
        this.joins.add(new Join(getRoot(), JoinType.RIGHT, name, specification));
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
        this.joins.add(new Join(getRoot(), JoinType.FULL, name, specification));
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
        this.joins.add(new Join(getRoot(), JoinType.LEFT_OUTER, name, specification));
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
        this.joins.add(new Join(getRoot(), JoinType.RIGHT_OUTER, name, specification));
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
        this.joins.add(new Join(getRoot(), JoinType.FULL_OUTER, name, specification));
        return this;
    }

    @Override
    public void accept(final SelectVisitor visitor) {
        visitor.visit(this);
        for (final Table table : this.tables) {
            table.accept(visitor);
        }
        for (final Join join : this.joins) {
            join.accept(visitor);
        }
        for (final ValueTable valueTable : this.valueTables) {
            valueTable.accept(visitor);
        }
    }
}