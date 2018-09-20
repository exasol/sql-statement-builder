package com.exasol.sql.expression;

import com.exasol.sql.FragmentVisitor;

public interface BooleanExpressionFragmentVisitor extends FragmentVisitor {

    public void visit(BooleanExpression booleanExpression);

}
