package com.exasol.sql.dql.select.rendering;

import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersTo;
import static com.exasol.sql.expression.BooleanTerm.eq;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.dql.select.Select;
import com.exasol.sql.expression.BooleanTerm;
import com.exasol.sql.expression.ExpressionTerm;

class TestWhereRendering {
    private Select select;

    @BeforeEach
    void beforeEach() {
        this.select = StatementFactory.getInstance().select();
        this.select.all().from().table("books");
    }

    @Test
    void testWhere() {
        assertThat(this.select.where(eq(ExpressionTerm.stringLiteral("foo"), ExpressionTerm.stringLiteral("bar"))),
                rendersTo("SELECT * FROM books WHERE 'foo' = 'bar'"));
    }

    @Test
    void testWhereWithLike() {
        assertThat(
                this.select
                        .where(BooleanTerm.like(ExpressionTerm.column("ISBN"), ExpressionTerm.stringLiteral("123%"))),
                rendersTo("SELECT * FROM books WHERE ISBN LIKE '123%'"));
    }

    @Test
    void testWhereWithLikeWithEscape() {
        assertThat(
                this.select.where(
                        BooleanTerm.like(ExpressionTerm.column("ISBN"), ExpressionTerm.stringLiteral("123%"), '/')),
                rendersTo("SELECT * FROM books WHERE ISBN LIKE '123%' ESCAPE '/'"));
    }

    @Test
    void testWhereWithNotLike() {
        assertThat(
                this.select.where(
                        BooleanTerm.notLike(ExpressionTerm.column("ISBN"), ExpressionTerm.stringLiteral("123%"))),
                rendersTo("SELECT * FROM books WHERE ISBN NOT LIKE '123%'"));
    }

    @Test
    void testWhereWithNotLikeWithEscape() {
        assertThat(
                this.select.where(
                        BooleanTerm.notLike(ExpressionTerm.column("ISBN"), ExpressionTerm.stringLiteral("123%"), '/')),
                rendersTo("SELECT * FROM books WHERE ISBN NOT LIKE '123%' ESCAPE '/'"));
    }
}