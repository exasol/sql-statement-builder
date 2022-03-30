package com.exasol.sql.expression;

import com.exasol.sql.dql.select.Select;
import com.exasol.sql.expression.comparison.LikeComparison;
import com.exasol.sql.expression.comparison.SimpleComparison;
import com.exasol.sql.expression.comparison.SimpleComparisonOperator;
import com.exasol.sql.expression.literal.BooleanLiteral;
import com.exasol.sql.expression.predicate.*;
import com.exasol.sql.expression.predicate.IsNullPredicate.IsNullPredicateOperator;

/**
 * This class represents boolean terms like {@code AND} or {@code NOT}.
 */
// [impl->dsn~boolean-operators~1]
public abstract class BooleanTerm extends AbstractBooleanExpression {
    private BooleanTerm() {
        super();
    }

    /**
     * Unary {@code NOT} of boolean value.
     *
     * @param value value to invert
     * @return inverted value
     */
    public static BooleanExpression not(final boolean value) {
        return new Not(value);
    }

    /**
     * Unary {@code NOT} of boolean expression.
     *
     * @param expression expression that should be inverted
     * @return inverted epression
     */
    public static BooleanExpression not(final BooleanExpression expression) {
        return new Not(expression);
    }

    /**
     * Logical {@code AND} combination of boolean values.
     *
     * @param values values to combine
     * @return logical {@code AND} combination
     */
    public static BooleanExpression and(final boolean... values) {
        return new And(values);
    }

    /**
     * Logical {@code AND} combination of boolean expression and value.
     *
     * @param expression boolean expression
     * @param value boolean value
     * @return logical {@code AND} combination
     */
    public static BooleanExpression and(final BooleanExpression expression, final boolean value) {
        return new And(expression, BooleanLiteral.of(value));
    }


    /**
     * Logical {@code AND} combination of boolean value and expression.
     *
     * @param value boolean value
     * @param expression boolean expression
     * @return logical {@code AND} combination
     */
    public static BooleanExpression and(final boolean value, final BooleanExpression expression) {
        return new And(BooleanLiteral.of(value), expression);
    }

     /**
     * Logical {@code AND} combination of boolean expressions.
     *
     * @param expressions boolean expressions
     * @return logical {@code AND} combination
     */
    public static BooleanExpression and(final BooleanExpression... expressions) {
        return new And(expressions);
    }

     /**
     * Logical {@code OR} combination of boolean values.
     *
     * @param values boolean values
     * @return logical {@code OR} combination
     */
    public static BooleanExpression or(final boolean... values) {
        return new Or(values);
    }

    /**
     * Logical {@code OR} combination of boolean expression and value.
     *
     * @param expression boolean expression
     * @param value boolean values
     * @return logical {@code OR} combination
     */
    public static BooleanExpression or(final BooleanExpression expression, final boolean value) {
        return new Or(expression, BooleanLiteral.of(value));
    }

    /**
     * Logical {@code OR} combination of boolean value and expression.
     *
     * @param value boolean values
     * @param expression boolean expression
     * @return logical {@code OR} combination
     */
    public static BooleanExpression or(final boolean value, final BooleanExpression expression) {
        return new Or(BooleanLiteral.of(value), expression);
    }

    /**
     * Logical {@code OR} combination of boolean expressions.
     *
     * @param expressions boolean values
     * @return logical {@code OR} combination
     */
    public static BooleanExpression or(final BooleanExpression... expressions) {
        return new Or(expressions);
    }

    /**
     * Comparison with {@code LIKE} operator.
     *
     * @param left left operand
     * @param right right operand
     * @return {@code LIKE} comparison
     */
    public static BooleanExpression like(final ValueExpression left, final ValueExpression right) {
        return LikeComparison.builder().left(left).right(right).build();
    }

    /**
     * Comparison with {@code LIKE} operator.
     *
     * @param left left operand
     * @param right right operand
     * @param escape escape character
     * @return {@code LIKE} comparison
     */
    public static BooleanExpression like(final ValueExpression left, final ValueExpression right, final char escape) {
        return LikeComparison.builder().left(left).right(right).escape(escape).build();
    }

    /**
     * Comparison with inverted {@code LIKE} operator.
     *
     * @param left left operand
     * @param right right operand
     * @return inverted {@code LIKE} comparison
     */
    public static BooleanExpression notLike(final ValueExpression left, final ValueExpression right) {
        return LikeComparison.builder().left(left).right(right).not().build();
    }

    /**
     * Comparison with inverted {@code LIKE} operator.
     *
     * @param left left operand
     * @param right right operand
     * @param escape escape character
     * @return inverted {@code LIKE} comparison
     */
    public static BooleanExpression notLike(final ValueExpression left, final ValueExpression right,
            final char escape) {
        return LikeComparison.builder().left(left).right(right).not().escape(escape).build();
    }

    /**
     * General comparison with operator as string.
     *
     * @param left left operand
     * @param operatorSymbol operator
     * @param right right operand
     * @return comparison
     */
    // [impl->dsn~boolean-operation.comparison.constructing-from-strings~1]
    public static BooleanExpression compare(final ValueExpression left, final String operatorSymbol,
            final ValueExpression right) {
        return new SimpleComparison(SimpleComparisonOperator.ofSymbol(operatorSymbol), left, right);
    }

    /**
     * General comparison with operator enum.
     *
     * @param left left operand
     * @param operator operator
     * @param right right operand
     * @return comparison
     */
    // [impl->dsn~boolean-operation.comparison.constructing-from-enum~1]
    public static BooleanExpression compare(final ValueExpression left, final SimpleComparisonOperator operator,
            final ValueExpression right) {
        return new SimpleComparison(operator, left, right);
    }

    /**
     * Equality comparison.
     *
     * @param left left operand
     * @param right right operand
     * @return comparison
     */
    // [impl->dsn~comparison-operations~1]
    public static BooleanExpression eq(final ValueExpression left, final ValueExpression right) {
        return new SimpleComparison(SimpleComparisonOperator.EQUAL, left, right);
    }

    /**
     * Not-equal comparison.
     *
     * @param left left operand
     * @param right right operand
     * @return comparison
     */
    // [impl->dsn~comparison-operations~1]
    public static BooleanExpression ne(final ValueExpression left, final ValueExpression right) {
        return new SimpleComparison(SimpleComparisonOperator.NOT_EQUAL, left, right);
    }

    /**
     * Less-than comparison.
     *
     * @param left left operand
     * @param right right operand
     * @return comparison
     */
    // [impl->dsn~comparison-operations~1]
    public static BooleanExpression lt(final ValueExpression left, final ValueExpression right) {
        return new SimpleComparison(SimpleComparisonOperator.LESS_THAN, left, right);
    }

    /**
     * Greater-than comparison.
     *
     * @param left left operand
     * @param right right operand
     * @return comparison
     */
    // [impl->dsn~comparison-operations~1]
    public static BooleanExpression gt(final ValueExpression left, final ValueExpression right) {
        return new SimpleComparison(SimpleComparisonOperator.GREATER_THAN, left, right);
    }

    /**
     * Less-than-or-equal comparison.
     *
     * @param left left operand
     * @param right right operand
     * @return comparison
     */
    // [impl->dsn~comparison-operations~1]
    public static BooleanExpression le(final ValueExpression left, final ValueExpression right) {
        return new SimpleComparison(SimpleComparisonOperator.LESS_THAN_OR_EQUAL, left, right);
    }

    /**
     * Greater-than-or-equal comparison.
     *
     * @param left left operand
     * @param right right operand
     * @return comparison
     */
    // [impl->dsn~comparison-operations~1]
    public static BooleanExpression ge(final ValueExpression left, final ValueExpression right) {
        return new SimpleComparison(SimpleComparisonOperator.GREATER_THAN_OR_EQUAL, left, right);
    }

    /**
     * Check for null.
     *
     * @param operand operand to be checked for null value
     * @return null check
     */
    // [impl->dsn~predicate-operators~1]
    public static BooleanExpression isNull(final ValueExpression operand) {
        return new IsNullPredicate(operand);
    }

    /**
     * Check for not null.
     *
     * @param operand operand to be checked for not null
     * @return not-null check
     */
    // [impl->dsn~predicate-operators~1]
    public static BooleanExpression isNotNull(final ValueExpression operand) {
        return new IsNullPredicate(IsNullPredicateOperator.IS_NOT_NULL, operand);
    }

    /**
     * Check for value in list.
     *
     * @param to_find operand to find in list
     * @param in_list list to search the first operand in
     * @return in-list search
     */
    // [impl->dsn~predicate-operators~1]
    public static BooleanExpression in(final ValueExpression to_find, final ValueExpression... in_list) {
        return InPredicate.builder().expression(to_find).operands(in_list).build();
    }

    /**
     * Check if value not in list.
     *
     * @param to_find operand to find in list
     * @param in_list list to search the first operand in
     * @return not-in-list search
     */
    // [impl->dsn~predicate-operators~1]
    public static BooleanExpression notIn(final ValueExpression to_find, final ValueExpression... in_list) {
        return InPredicate.builder().expression(to_find).operands(in_list).not().build();
    }

    /**
     * Check for value sub-select.
     *
     * @param operand operand to find in list
     * @param select sub-select to search the operand in
     * @return in-sub-select search
     */
    // [impl->dsn~predicate-operators~1]
    public static BooleanExpression in(final ValueExpression operand, final Select select) {
        return InPredicate.builder().expression(operand).selectQuery(select).build();
    }

    /**
     * Check if value not in sub-select.
     *
     * @param operand operand to find in list
     * @param select sub-select to search the operand in
     * @return not-in-sub-select search
     */
    // [impl->dsn~predicate-operators~1]
    public static BooleanExpression notIn(final ValueExpression operand, final Select select) {
        return InPredicate.builder().expression(operand).selectQuery(select).not().build();
    }

    /**
     * Check if the sub-select has any result.
     *
     * @param select sub-select
     * @return exists-check
     */
    // [impl->dsn~predicate-operators~1]
    public static BooleanExpression exists(final Select select) {
        return new ExistsPredicate(select);
    }

    /**
     * Check if value is between two other values.
     *
     * @param expression expression to check
     * @param start interval start
     * @param end interval end
     * @return between-check
     */
    // [impl->dsn~predicate-operators~1]
    public static BooleanExpression between(final ValueExpression expression, final ValueExpression start,
            final ValueExpression end) {
        return BetweenPredicate.builder().expression(expression).start(start).end(end).build();
    }

    /**
     * Check if value is not between two other values.
     *
     * @param expression expression to check
     * @param start interval start
     * @param end interval end
     * @return not-between-check
     */
    // [impl->dsn~predicate-operators~1]
    public static BooleanExpression notBetween(final ValueExpression expression, final ValueExpression start,
            final ValueExpression end) {
        return BetweenPredicate.builder().expression(expression).start(start).end(end).not().build();
    }

    /**
     * Create a logical operation from an operator name and a list of operands
     *
     * @param operator    name of the operator
     * @param expressions operands
     * @return instance of either {@link And}, {@link Or} or {@link Not}
     * @throws IllegalArgumentException if the operator is unknown or null
     */
    public static BooleanExpression operation(final String operator, final BooleanExpression... expressions) {
        switch (operator.toLowerCase()) {
        case "and":
            return new And(expressions);
        case "or":
            return new Or(expressions);
        case "not":
            if (expressions.length == 1) {
                return new Not(expressions[0]);
            } else {
                throw new IllegalArgumentException(
                        "Logical \"not\" must have exactly one operand. Got " + expressions.length + ".");
            }
        default:
            throw new IllegalArgumentException(
                    "Unknown boolean connector \"" + operator + "\". Must be one of \"and\" or \"or\".");
        }
    }
}
