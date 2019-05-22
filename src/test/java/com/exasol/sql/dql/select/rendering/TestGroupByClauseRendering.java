package com.exasol.sql.dql.select.rendering;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.dql.select.Select;
import com.exasol.sql.expression.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TestGroupByClauseRendering {
    private Select select;

    @BeforeEach
    void beforeEach() {
        this.select = StatementFactory.getInstance().select();
        this.select.all().from().table("t");
    }

    @Test
    void testGroupByClause(){
        assertThat(this.select.groupBy("city"), rendersTo("SELECT * FROM t GROUP BY city"));
    }

    @Test
    void testGroupByClauseMultipleColumns(){
        assertThat(this.select.groupBy("city", "order", "price"), rendersTo("SELECT * FROM t GROUP BY city, order, price"));
    }

    @Test
    void testGroupByClauseMultipleColumnsWithHaving(){
        assertThat(this.select.groupBy("city", "order", "price").having(BooleanTerm.lt("price", "10")), rendersTo("SELECT * FROM t GROUP BY city, order, price HAVING 'price' < '10'"));
    }

    @Test
    void testGroupByClauseMultipleColumnsWithMultipleHaving(){
        assertThat(this.select.groupBy("city", "order", "price").having(BooleanTerm.and(BooleanTerm.lt("price", "10"), BooleanTerm.ne("price", "5"))), rendersTo("SELECT * FROM t GROUP BY city, order, price HAVING ('price' < '10') AND ('price' <> '5')"));
    }
}