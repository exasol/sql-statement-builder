package com.exasol.sql.expression.function.exasol;

import static com.exasol.hamcrest.SqlFragmentRenderResultMatcher.rendersTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import com.exasol.datatype.type.Varchar;
import com.exasol.sql.StatementFactory;
import com.exasol.sql.dql.select.Select;
import com.exasol.sql.expression.NullLiteral;

class ExasolFunctionCastTest {
    @Test
    void testRendering() {
        final Select select = StatementFactory.getInstance().select()
                .function(ExasolFunction.castScalarFunction(NullLiteral.nullLiteral(), new Varchar(254)));
        assertThat(select, rendersTo("SELECT CAST(NULL AS VARCHAR(254))"));
    }

    @Test
    void testRenderingWithName() {
        final Select select = StatementFactory.getInstance().select()
                .function(ExasolFunction.castScalarFunction(NullLiteral.nullLiteral(), new Varchar(254)), "TEST");
        assertThat(select, rendersTo("SELECT CAST(NULL AS VARCHAR(254)) TEST"));
    }
}