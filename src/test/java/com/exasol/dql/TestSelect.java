package com.exasol.dql;

import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.dql.FromClause;
import com.exasol.sql.dql.Select;

class TestSelect {
    // [impl->req~statement-structure.step-wise~1]
    @Test
    void testGetFrom() {
        final Select select = StatementFactory.getInstance().select().all();
        final FromClause from = select.from().table("persons");
        assertThat(select.from(), sameInstance(from));
    }
}