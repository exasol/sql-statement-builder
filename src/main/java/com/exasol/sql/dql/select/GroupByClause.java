package com.exasol.sql.dql.select;

import java.util.*;

import com.exasol.sql.*;
import com.exasol.sql.expression.*;

/**
 * This class represents the group class clause of an SQL statement.
 */
public class GroupByClause extends AbstractFragment implements SelectFragment {
    private final SqlStatement rootStatement;
    private final List<String> columnName;
    private BooleanExpression booleanExpression;

    /**
     * Create a new instance of a {@link GroupByClause}
     *
     * @param rootStatement       SQL statement this GROUP BY clause belongs to
     * @param columnName boolean expression servicing as criteria for the WHERE clause
     */
    public GroupByClause(final SqlStatement rootStatement, final String... columnName) {
        super(rootStatement);
        this.rootStatement = rootStatement;
        this.columnName = Arrays.asList(columnName);
    }

    @Override
    public void accept(final SelectVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * Get column name.
     * 
     * @return column name
     */
    public List<String> getColumnName() {
        return columnName;
    }

    public Select having(final BooleanExpression booleanExpression) {
        this.booleanExpression = booleanExpression;
        return (Select) rootStatement;
    }

    /**
     * Get the boolean expression.
     *
     * @return boolean expression
     */
    public BooleanExpression getBooleanExpression() {
        return booleanExpression;
    }
}
