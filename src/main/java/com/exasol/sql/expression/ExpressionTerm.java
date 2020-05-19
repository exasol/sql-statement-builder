package com.exasol.sql.expression;

import com.exasol.sql.ColumnsDefinition;
import com.exasol.sql.expression.function.Function;
import com.exasol.sql.expression.function.FunctionName;
import com.exasol.sql.expression.function.exasol.ExasolFunction;
import com.exasol.sql.expression.function.exasol.ExasolUdf;

/**
 * Static factory methods for SQL expressions.
 */
public abstract class ExpressionTerm extends AbstractValueExpression {
    private ExpressionTerm() {
        super();
    }

    /**
     * Create a string literal.
     *
     * @param value literal value
     * @return string literal
     */
    public static StringLiteral stringLiteral(final String value) {
        return StringLiteral.of(value);
    }

    /**
     * Create a string literal from a character.
     *
     * @param value character value
     * @return string literal
     */
    public static StringLiteral stringLiteral(final char value) {
        return StringLiteral.of(value);
    }

    /**
     * Create an integer literal.
     *
     * @param value literal value
     * @return integer literal
     */
    public static IntegerLiteral integerLiteral(final int value) {
        return IntegerLiteral.of(value);
    }

    /**
     * Create an long literal.
     *
     * @param value literal value
     * @return long literal
     */
    public static LongLiteral longLiteral(final long value) {
        return LongLiteral.of(value);
    }

    /**
     * Create a double literal.
     *
     * @param value literal value
     * @return double literal
     */
    public static DoubleLiteral doubleLiteral(final double value) {
        return DoubleLiteral.of(value);
    }

    /**
     * Create a float literal.
     *
     * @param value literal value
     * @return float literal
     */
    public static FloatLiteral floatLiteral(final float value) {
        return FloatLiteral.of(value);
    }

    /**
     * Create a boolean literal.
     *
     * @param value literal value
     * @return boolean literal
     */
    public static BooleanLiteral booleanLiteral(final boolean value) {
        return BooleanLiteral.of(value);
    }

    /**
     * Create a reference to a table column.
     *
     * @param column column name
     * @return column reference
     */
    public static ColumnReference column(final String column) {
        return ColumnReference.of(column);
    }

    /**
     * Create a reference to a column in a specific table.
     * 
     * @param table table name
     * @param column column name
     *
     * @return column reference
     */
    public static ColumnReference column(final String table, final String column) {
        return ColumnReference.column(table, column);
    }

    /**
     * Create a binary arithmetic expression with ADD operator.
     * 
     * @param left left operand
     * @param right right operand
     * @return binary arithmetic expression
     */
    public static BinaryArithmeticExpression plus(final ValueExpression left, final ValueExpression right) {
        return BinaryArithmeticExpression.of(BinaryArithmeticExpression.BinaryArithmeticOperator.ADD, left, right);
    }

    /**
     * Create a binary arithmetic expression with SUBTRACT operator.
     *
     * @param left left operand
     * @param right right operand
     * @return binary arithmetic expression
     */
    public static BinaryArithmeticExpression minus(final ValueExpression left, final ValueExpression right) {
        return BinaryArithmeticExpression.of(BinaryArithmeticExpression.BinaryArithmeticOperator.SUBTRACT, left, right);
    }

    /**
     * Create a binary arithmetic expression with DIVIDE operator.
     *
     * @param left left operand
     * @param right right operand
     * @return binary arithmetic expression
     */
    public static BinaryArithmeticExpression multiply(final ValueExpression left, final ValueExpression right) {
        return BinaryArithmeticExpression.of(BinaryArithmeticExpression.BinaryArithmeticOperator.MULTIPLY, left, right);
    }

    /**
     * Create a binary arithmetic expression with DIVIDE operator.
     *
     * @param left left operand
     * @param right right operand
     * @return binary arithmetic expression
     */
    public static BinaryArithmeticExpression divide(final ValueExpression left, final ValueExpression right) {
        return BinaryArithmeticExpression.of(BinaryArithmeticExpression.BinaryArithmeticOperator.DIVIDE, left, right);
    }

    /**
     * Create an Exasol function.
     *
     * @param functionName a name of function
     * @return function
     */
    public static Function function(final FunctionName functionName) {
        return ExasolFunction.of(functionName);
    }

    /**
     * Create an Exasol function.
     *
     * @param functionName a name of function
     * @param valueExpressions zero or more value expressions
     * @return <code>this</code> instance for fluent programming
     */
    public static Function function(final FunctionName functionName, final ValueExpression... valueExpressions) {
        return ExasolFunction.of(functionName, valueExpressions);
    }

    /**
     * Create a User Defined Function.
     *
     * @param functionName a name of function
     * @param emitsColumnsDefinition column definitions for emits
     * @param valueExpressions zero or more value expressions
     * @return UDF
     */
    public static Function udf(final String functionName, final ColumnsDefinition emitsColumnsDefinition,
            final ValueExpression... valueExpressions) {
        return ExasolUdf.of(functionName, emitsColumnsDefinition, valueExpressions);
    }

    /**
     * Create a User Defined Function.
     *
     * @param functionName a name of function
     * @param valueExpressions zero or more value expressions
     * @return UDF
     */
    public static Function udf(final String functionName, final ValueExpression... valueExpressions) {
        return ExasolUdf.of(functionName, valueExpressions);
    }

    /**
     * Create a NULL literal.
     *
     * @return NULL literal
     */
    public static NullLiteral nullLiteral() {
        return NullLiteral.nullLiteral();
    }
}