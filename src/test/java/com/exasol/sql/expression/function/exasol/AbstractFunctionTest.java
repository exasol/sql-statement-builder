package com.exasol.sql.expression.function.exasol;

import com.exasol.sql.expression.function.Function;
import com.exasol.sql.expression.function.FunctionVisitor;
import com.exasol.sql.expression.rendering.ValueExpressionRenderer;
import com.exasol.sql.rendering.StringRendererConfig;

public abstract class AbstractFunctionTest {
    protected String renderFunction(final Function cast) {
        final ValueExpressionRenderer functionVisitor = new ValueExpressionRenderer(
                StringRendererConfig.builder().build());
        cast.accept((FunctionVisitor) functionVisitor);
        return functionVisitor.render();
    }
}