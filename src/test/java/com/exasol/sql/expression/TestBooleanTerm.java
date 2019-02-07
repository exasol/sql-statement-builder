package com.exasol.sql.expression;

import static com.exasol.sql.expression.BooleanTerm.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

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
        assertThrows(IllegalArgumentException.class, () -> BooleanTerm.operation("illegal", not(true)));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testOperationFromNotWithMoreOrLessThanOneOperandThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> BooleanTerm.operation("not", not(true), not(false)));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testOperationFromUpperCaseUnknownOperatorThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> BooleanTerm.operation("ILLEGAL", not(true)));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testOperationFromUpperCaseNotWithMoreOrLessThanOneOperandThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> BooleanTerm.operation("NOT", not(true), not(false)));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testOperationFromNullOperatorThrowsException() {
        assertThrows(NullPointerException.class, () -> BooleanTerm.operation(null, not(true), not(false)));
    }

    // [utest->dsn~boolean-operation.comparison.constructing-from-strings~1]
    @Test
    void testOperationFromComparisonOperatorString() {
        assertThat(BooleanTerm.compare("a", "<>", "b"), instanceOf(Comparison.class));
    }

    // [utest->dsn~boolean-operation.comparison.constructing-from-enum~1]
    @Test
    void testOperationFromComparisonOperatorEnum() {
        assertThat(BooleanTerm.compare("a", ComparisonOperator.NOT_EQUAL, "b"), instanceOf(Comparison.class));
    }
}