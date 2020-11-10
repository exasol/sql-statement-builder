package com.exasol.sql.expression.function;

import com.exasol.sql.expression.function.exasol.CastExasolFunction;
import com.exasol.sql.expression.function.exasol.ExasolFunction;
import com.exasol.sql.expression.function.exasol.ExasolUdf;

/**
 * Visitor interface for {@link Function}.
 */
public interface FunctionVisitor {
    public void visit(ExasolFunction function);

    public void visit(ExasolUdf function);

    public void visit(CastExasolFunction castFunction);
}
