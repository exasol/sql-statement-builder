package com.exasol.sql.dql.select.rendering;

import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersTo;
import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersWithConfigTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.exasol.sql.StatementFactory;
import com.exasol.sql.ValueTable;
import com.exasol.sql.dql.select.Select;
import com.exasol.sql.rendering.StringRendererConfig;

class TestValueTableRendering {
    private Select select;

    @BeforeEach
    void beforeEach() {
        this.select = StatementFactory.getInstance().select();
    }

    // [utest->dsn~rendering.sql.select~1]
    @Test
    void testSelectFromMultipleTableAs() {
        final ValueTable values = new ValueTable(this.select);
        values.appendRow("r1c1", "r1c2").appendRow("r2c1", "r2c2");
        assertThat(this.select.all().from().valueTable(values),
                rendersTo("SELECT * FROM (VALUES ('r1c1', 'r1c2'), ('r2c1', 'r2c2'))"));
    }

    @Test
    void testSelectFromValuesAs() {
        final ValueTable values = new ValueTable(this.select);
        values.add(1, 2);
        final Select select = this.select.field("COL1");
        select.from().valueTableAs(values, "T", "COL1", "COL2");
        assertThat(select, rendersTo("SELECT COL1 FROM (VALUES (1, 2)) AS T(COL1, COL2)"));
    }

    @Test
    void testSelectFromValuesAsWithQuotation() {
        final StringRendererConfig config = StringRendererConfig.builder().quoteIdentifiers(true).build();
        final ValueTable values = new ValueTable(this.select);
        values.add(1, 2);
        final Select select = this.select.field("COL1");
        select.from().valueTableAs(values, "T", "COL1", "COL2");
        assertThat(select,
                rendersWithConfigTo(config, "SELECT \"COL1\" FROM (VALUES (1, 2)) AS \"T\"(\"COL1\", \"COL2\")"));
    }

    @Test
    void testSelectFromValuesAsWithQuotationOneColumn() {
        final StringRendererConfig config = StringRendererConfig.builder().quoteIdentifiers(true).build();
        final ValueTable values = new ValueTable(this.select);
        values.add(1);
        final Select select = this.select.field("COL1");
        select.from().valueTableAs(values, "T", "COL1");
        assertThat(select, rendersWithConfigTo(config, "SELECT \"COL1\" FROM (VALUES (1)) AS \"T\"(\"COL1\")"));
    }
}