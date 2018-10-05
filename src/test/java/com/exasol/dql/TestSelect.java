package com.exasol.dql;

import static com.exasol.sql.expression.BooleanTerm.eq;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.dql.FromClause;
import com.exasol.sql.dql.Select;
import com.exasol.sql.expression.BooleanExpression;

class TestSelect {
    private Select select;

    @BeforeEach
    void beforeEach() {
        this.select = StatementFactory.getInstance().select();
    }

    // [impl->req~statement-structure.step-wise~1]
    @Test
    void testGetFrom() {
        this.select.all();
        final FromClause from = this.select.from().table("persons");
        assertThat(this.select.from(), sameInstance(from));
    }

    // [impl->req~statement-structure.step-wise~1]
    @Test
    void testGetWhere() {
        final BooleanExpression expression = eq("firstname", "Jane");
        this.select.all().from().table("persons").where(expression);
        assertThat(this.select.where().getExpression(), sameInstance(expression));
    }

    // [impl->req~statement-structure.step-wise~1]
    @Test
    void testGetNonExistingFromThrowsException() {
        assertThrows(IllegalStateException.class, () -> this.select.from());
    }
}