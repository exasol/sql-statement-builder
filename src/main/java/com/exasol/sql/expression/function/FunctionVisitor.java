package com.exasol.sql.expression.function;

import com.exasol.sql.expression.function.exasol.*;

/**
 * Visitor interface for {@link Function}.
 */
public interface FunctionVisitor {
    public void visit(ExasolFunction function);

    public void visit(ExasolUdf function);

    public void visit(CastExasolFunction castFunction);

    public void visit(AnalyticFunction analyticFunction);
}
