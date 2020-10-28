package com.exasol.sql.expression;

import static com.exasol.sql.expression.BooleanTerm.not;
import static com.exasol.sql.expression.ExpressionTerm.stringLiteral;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.exasol.sql.expression.comparison.SimpleComparison;
import com.exasol.sql.expression.comparison.SimpleComparisonOperator;

class TestBooleanTerm {
    // [utest->dsn~boolean-operators~1]
    @Test
    void testOperationYieldsAnd() {
        final BooleanExpression term = BooleanTerm.operation("and", not(true), not(false));
        assertThat(term, instanceOf(And.class));
    }

    // [utest->dsn~boolean-operators~1]
    // [utest->dsn~boolean-operators~1]
    @Test
    void testOperationFromUpperCaseYieldsAnd() {
        assertThat(BooleanTerm.operation("AND", not(true), not(false)), instanceOf(And.class));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testOperationYieldsOr() {
        assertThat(BooleanTerm.operation("or", not(true), not(false)), instanceOf(Or.class));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testOperationFromUpperCaseYieldsOr() {
        assertThat(BooleanTerm.operation("OR", not(true), not(false)), instanceOf(Or.class));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testOperationYieldsNot() {
        assertThat(BooleanTerm.operation("not", not(true)), instanceOf(Not.class));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testOperationFromUpperCaseYieldsNot() {
        assertThat(BooleanTerm.operation("NOT", not(true)), instanceOf(Not.class));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testOperationFromUnknownOperatorThrowsException() {
        final BooleanExpression notTrue = not(true);
        assertThrows(IllegalArgumentException.class, () -> BooleanTerm.operation("illegal", notTrue));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testOperationFromNotWithMoreOrLessThanOneOperandThrowsException() {
        final BooleanExpression notTrue = not(true);
        final BooleanExpression notFalse = not(false);
        assertThrows(IllegalArgumentException.class, () -> BooleanTerm.operation("not", notTrue, notFalse));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testOperationFromUpperCaseUnknownOperatorThrowsException() {
        final BooleanExpression notTrue = not(true);
        assertThrows(IllegalArgumentException.class, () -> BooleanTerm.operation("ILLEGAL", notTrue));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testOperationFromUpperCaseNotWithMoreOrLessThanOneOperandThrowsException() {
        final BooleanExpression notTrue = not(true);
        final BooleanExpression notFalse = not(false);
        assertThrows(IllegalArgumentException.class, () -> BooleanTerm.operation("NOT", notTrue, notFalse));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testOperationFromNullOperatorThrowsException() {
        final BooleanExpression notTrue = not(true);
        final BooleanExpression notFalse = not(false);
        assertThrows(NullPointerException.class, () -> BooleanTerm.operation(null, notTrue, notFalse));
    }

    // [utest->dsn~boolean-operation.comparison.constructing-from-strings~1]
    @Test
    void testOperationFromComparisonOperatorString() {
        assertThat(BooleanTerm.compare(stringLiteral("a"), "<>", stringLiteral("b")),
                instanceOf(SimpleComparison.class));
    }

    // [utest->dsn~boolean-operation.comparison.constructing-from-enum~1]
    @Test
    void testOperationFromComparisonOperatorEnum() {
        assertThat(BooleanTerm.compare(stringLiteral("a"), SimpleComparisonOperator.NOT_EQUAL, stringLiteral("b")),
                instanceOf(SimpleComparison.class));
    }
}