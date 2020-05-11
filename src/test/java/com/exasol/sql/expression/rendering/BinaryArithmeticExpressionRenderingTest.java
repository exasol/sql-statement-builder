package com.exasol.sql.expression.rendering;

import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersTo;
import static com.exasol.sql.expression.ExpressionTerm.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.dql.select.Select;

public class BinaryArithmeticExpressionRenderingTest {
    private Select select;

    @BeforeEach
    void beforeEach() {
        this.select = StatementFactory.getInstance().select();
    }

    @Test
    void testPlusRendering() {
        this.select.arithmeticExpression(plus(integerLiteral(1000), integerLiteral(234)), "ADD");
        assertThat(this.select, rendersTo("SELECT (1000+234) ADD"));

    }

    @Test
    void testMinusRendering() {
        this.select.arithmeticExpression(minus(integerLiteral(1000), integerLiteral(1)), "SUB");
        assertThat(this.select, rendersTo("SELECT (1000-1) SUB"));

    }

    @Test
    void testMultiplyRendering() {
        this.select.arithmeticExpression(multiply(integerLiteral(1000), integerLiteral(1)), "MULT");
        assertThat(this.select, rendersTo("SELECT (1000*1) MULT"));

    }

    @Test
    void testDivideRendering() {
        this.select.arithmeticExpression(divide(integerLiteral(1000), integerLiteral(1)), "DIV");
        assertThat(this.select, rendersTo("SELECT (1000/1) DIV"));

    }

    @Test
    void testMultilevelExpressionRendering() {
        this.select.arithmeticExpression(divide(plus(integerLiteral(1000), integerLiteral(234)),
                multiply(integerLiteral(1000), integerLiteral(100))));
        assertThat(this.select, rendersTo("SELECT ((1000+234)/(1000*100))"));

    }
}