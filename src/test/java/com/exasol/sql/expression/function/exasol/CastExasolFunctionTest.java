package com.exasol.sql.expression.function.exasol;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import com.exasol.datatype.type.Varchar;
import com.exasol.sql.expression.literal.NullLiteral;

class CastExasolFunctionTest extends AbstractFunctionTest {
    @Test
    void testRendering() {
        final CastExasolFunction cast = CastExasolFunction.of(NullLiteral.nullLiteral(), new Varchar(254));
        final String render = renderFunction(cast);
        assertThat(render, CoreMatchers.equalTo("CAST(NULL AS  VARCHAR(254))"));
    }
}