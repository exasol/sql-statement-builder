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
        final BooleanExpression term = BooleanTerm.operation("and", not("a"), not("b"));
        assertThat(term, instanceOf(And.class));
    }

    // [utest->dsn~boolean-operators~1]
    // [utest->dsn~boolean-operators~1]
    @Test
    void testOperationFromUpperCaseYieldsAnd() {
        assertThat(BooleanTerm.operation("AND", not("a"), not("b")), instanceOf(And.class));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testOperationYieldsOr() {
        assertThat(BooleanTerm.operation("or", not("a"), not("b")), instanceOf(Or.class));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testOperationFromUpperCaseYieldsOr() {
        assertThat(BooleanTerm.operation("OR", not("a"), not("b")), instanceOf(Or.class));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testOperationYieldsNot() {
        assertThat(BooleanTerm.operation("not", not("a")), instanceOf(Not.class));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testOperationFromUpperCaseYieldsNot() {
        assertThat(BooleanTerm.operation("NOT", not("a")), instanceOf(Not.class));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testOperationFromUnknownOperatorThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> BooleanTerm.operation("illegal", not("a")));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testOperationFromNotWithMoreOrLessThanOneOperandThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> BooleanTerm.operation("not", not("a"), not("b")));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testOperationFromUpperCaseUnknownOperatorThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> BooleanTerm.operation("ILLEGAL", not("a")));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testOperationFromUpperCaseNotWithMoreOrLessThanOneOperandThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> BooleanTerm.operation("NOT", not("a"), not("b")));
    }

    // [utest->dsn~boolean-operators~1]
    @Test
    void testOperationFromNullOperatorThrowsException() {
        assertThrows(NullPointerException.class, () -> BooleanTerm.operation(null, not("a"), not("b")));
    }
}