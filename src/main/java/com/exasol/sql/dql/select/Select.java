package com.exasol.sql.dql.select;

import java.util.ArrayList;
import java.util.List;

import com.exasol.sql.*;
import com.exasol.sql.expression.*;
import com.exasol.sql.expression.function.Function;
import com.exasol.sql.expression.function.FunctionName;

/**
 * This class implements an SQL {@link Select} statement.
 */
// [impl->dsn~select-statements~1]
public class Select extends AbstractFragment implements SqlStatement, SelectFragment {
    private Boolean isDistinct = false;
    private final List<DerivedColumn> derivedColumns = new ArrayList<>();
    private FromClause fromClause = null;
    private WhereClause whereClause = null;
    private LimitClause limitClause = null;
    private GroupByClause groupByClause = null;
    private OrderByClause orderByClause = null;

    /**
     * Create a new instance of a {@link Select}.
     */
    public Select() {
        super(null);
    }

    public Boolean getIsDistinct() {
        return this.isDistinct;
    }

    /**
     * Add a wildcard field for all involved fields.
     *
     * @return {@code this} instance for fluent programming
     */
    public Select all() {
        final DerivedColumn derivedColumn = new DerivedColumn(this, ColumnReference.of("*"));
        this.derivedColumns.add(derivedColumn);
        return this;
    }

    public Select distinct() {
        this.isDistinct = true;
        return this;
    }

    /**
     * Add one or more named fields.
     *
     * @param names field name
     * @return {@code this} instance for fluent programming
     */
    public Select field(final String... names) {
        for (final String name : names) {
            final DerivedColumn derivedColumn = new DerivedColumn(this, ColumnReference.of(name));
            this.derivedColumns.add(derivedColumn);
        }
        return this;
    }

    public Select field(final Select select) {
        final DerivedColumn derivedColumn = new DerivedColumn(this, select);
        this.derivedColumns.add(derivedColumn);
        return this;
    }

    public Select field(final Select select, final String derivedColumnName) {
        final DerivedColumn derivedColumn = new DerivedColumn(this, select, derivedColumnName);
        this.derivedColumns.add(derivedColumn);
        return this;
    }

    /**
     * Add a function.
     *
     * @param functionName     name of function
     * @param valueExpressions zero or more value expression
     * @return {@code this} instance for fluent programming
     */
    public Select function(final FunctionName functionName, final ValueExpression... valueExpressions) {
        return function(functionName, "", valueExpressions);
    }

    /**
     * Add a function.
     *
     * @param functionName      name of the function
     * @param valueExpressions  zero or more value expression
     * @param derivedColumnName name under which you can refer to the derived column
     * @return {@code this} instance for fluent programming
     */
    public Select function(final FunctionName functionName, final String derivedColumnName,
            final ValueExpression... valueExpressions) {
        final Function function = ExpressionTerm.function(functionName, valueExpressions);
        return this.function(function, derivedColumnName);
    }

    /**
     * Add a function.
     *
     * @param function          function
     * @param derivedColumnName name under which you can refer to the derived column
     * @return {@code this} instance for fluent programming
     */
    public Select function(final Function function, final String derivedColumnName) {
        final DerivedColumn derivedColumn = new DerivedColumn(this, function, derivedColumnName);
        this.derivedColumns.add(derivedColumn);
        return this;
    }

    /**
     * Add a function.
     *
     * @param function function
     * @return {@code this} instance for fluent programming
     */
    public Select function(final Function function) {
        return function(function, "");
    }

    /**
     * Add a User Defined Function.
     *
     * @param functionName           name of function
     * @param emitsColumnsDefinition column definitions for emits
     * @param valueExpressions       zero or more value expressions
     * @return {@code this} instance for fluent programming
     */
    public Select udf(final String functionName, final ColumnsDefinition emitsColumnsDefinition,
            final ValueExpression... valueExpressions) {
        final Function udf = ExpressionTerm.udf(functionName, emitsColumnsDefinition, valueExpressions);
        return createUdf(udf);
    }

    private Select createUdf(final Function udf) {
        final DerivedColumn derivedColumn = new DerivedColumn(this, udf);
        this.derivedColumns.add(derivedColumn);
        return this;
    }

    /**
     * Add a User Defined Function.
     *
     * @param functionName     a name of function
     * @param valueExpressions zero or more value expressions
     * @return {@code this} instance for fluent programming
     */
    public Select udf(final String functionName, final ValueExpression... valueExpressions) {
        final Function udf = ExpressionTerm.udf(functionName, valueExpressions);
        return createUdf(udf);
    }

    /**
     * Add an arithmetic expression.
     *
     * @deprecated please use a {@link #valueExpression(ValueExpression)} valueExpression} method instead.
     * @param arithmeticExpression arithmetic expression
     * @return {@code this} instance for fluent programming
     */
    @Deprecated(since = "4.0.2")
    public Select arithmeticExpression(final BinaryArithmeticExpression arithmeticExpression) {
        return valueExpression(arithmeticExpression);
    }

    /**
     * Add an arithmetic expression.
     *
     * @deprecated please use a {@link #valueExpression(ValueExpression, String)} valueExpression} method instead.
     * @param arithmeticExpression arithmetic expression
     * @param derivedColumnName    name under which you can refer to the derived column
     * @return {@code this} instance for fluent programming
     */
    @Deprecated(since = "4.0.2")
    public Select arithmeticExpression(final BinaryArithmeticExpression arithmeticExpression,
            final String derivedColumnName) {
        return valueExpression(arithmeticExpression, derivedColumnName);
    }

    /**
     * Add a value expression.
     *
     * @param valueExpression value expression
     * @return {@code this} instance for fluent programming
     */
    public Select valueExpression(final ValueExpression valueExpression) {
        final DerivedColumn derivedColumn = new DerivedColumn(this, valueExpression);
        this.derivedColumns.add(derivedColumn);
        return this;
    }

    /**
     * Add a value expression expression.
     *
     * @param valueExpression   value expression
     * @param derivedColumnName name under which you can refer to the derived column
     * @return {@code this} instance for fluent programming
     */
    public Select valueExpression(final ValueExpression valueExpression, final String derivedColumnName) {
        final DerivedColumn derivedColumn = new DerivedColumn(this, valueExpression, derivedColumnName);
        this.derivedColumns.add(derivedColumn);
        return this;
    }

    /**
     * Get the {@link FromClause} of this select statement.
     *
     * @return from clause
     */
    // [impl->dsn~select-statement.out-of-order-clauses~1]
    public synchronized FromClause from() {
        if (this.fromClause == null) {
            this.fromClause = new FromClause(this);
        }
        return this.fromClause;
    }

    /**
     * Create a new full outer {@link LimitClause}.
     *
     * @param count maximum number of rows to be included in query result
     * @return {@code this} for fluent programming
     * @throws IllegalStateException if a limit clause already exists
     */
    // [impl->dsn~select-statement.out-of-order-clauses~1]
    public synchronized Select limit(final int count) {
        if (this.limitClause != null) {
            throw new IllegalStateException(
                    "Tried to create a LIMIT clause in a SELECT statement that already had one.");
        }
        this.limitClause = new LimitClause(this, count);
        return this;
    }

    /**
     * Create a new full outer {@link LimitClause}.
     *
     * @param offset index of the first row in the query result
     * @param count  maximum number of rows to be included in query result
     * @return {@code this} for fluent programming
     * @throws IllegalStateException if a limit clause already exists
     */
    // [impl->dsn~select-statement.out-of-order-clauses~1]
    public synchronized Select limit(final int offset, final int count) {
        if (this.limitClause != null) {
            throw new IllegalStateException(
                    "Tried to create a LIMIT clause in a SELECT statement that already had one.");
        }
        this.limitClause = new LimitClause(this, offset, count);
        return this;
    }

    /**
     * Create a new {@link WhereClause}.
     *
     * @param expression boolean expression that defines the filter criteria
     * @return {@code this} for fluent programming
     */
    // [impl->dsn~select-statement.out-of-order-clauses~1]
    public synchronized Select where(final BooleanExpression expression) {
        if (this.whereClause == null) {
            this.whereClause = new WhereClause(this, expression);
        }
        return this;
    }

    /**
     * Create a new {@link GroupByClause}.
     *
     * @param columnReferences column references
     * @return {@link GroupByClause} instance
     */
    // [impl->dsn~select-statement.out-of-order-clauses~1]
    public synchronized GroupByClause groupBy(final ColumnReference... columnReferences) {
        if (this.groupByClause == null) {
            this.groupByClause = new GroupByClause(this, columnReferences);
        }
        return this.groupByClause;
    }

    /**
     * Create a new {@link OrderByClause}.
     *
     * @param columnReferences column references
     * @return {@link OrderByClause} instance
     */
    // [impl->dsn~select-statement.out-of-order-clauses~1]
    public synchronized OrderByClause orderBy(final ColumnReference... columnReferences) {
        if (this.orderByClause == null) {
            this.orderByClause = new OrderByClause(this, columnReferences);
        }
        return this.orderByClause;
    }

    @Override
    public void accept(final SelectVisitor visitor) {
        visitor.visit(this);

        for (final DerivedColumn derivedColumn : this.derivedColumns) {
            derivedColumn.accept(visitor);
        }
        if (this.fromClause != null) {
            this.fromClause.accept(visitor);
        }
        if (this.whereClause != null) {
            this.whereClause.accept(visitor);
        }
        if (this.groupByClause != null) {
            this.groupByClause.accept(visitor);
        }
        if (this.orderByClause != null) {
            this.orderByClause.accept(visitor);
        }
        if (this.limitClause != null) {
            this.limitClause.accept(visitor);
        }
    }
}
