package com.exasol.sql.expression.function;

import com.exasol.sql.expression.function.exasol.*;

/**
 * Visitor interface for {@link Function}.
 */
public interface FunctionVisitor {
    /**
     * Visit a built-in Exasol function.
     *
     * @param function function to visit
     */
    public void visit(final ExasolFunction function);

    /**
     * Visit an Exasol user-defined function (UDF).
     *
     * @param function UDF to visit
     */
    public void visit(final ExasolUdf function);

    /**
     * Visit an explicit cast.
     *
     * @param castFunction cast function to visit
     */
    public void visit(final CastExasolFunction castFunction);

    /**
     * Visit an analytic function.
     *
     * @param analyticFunction analytic function to visit
     */
    public void visit(final AnalyticFunction analyticFunction);
}
