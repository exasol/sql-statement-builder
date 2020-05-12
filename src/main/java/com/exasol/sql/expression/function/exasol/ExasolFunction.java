package com.exasol.sql.expression.function.exasol;

import java.util.*;

import com.exasol.sql.expression.ValueExpression;
import com.exasol.sql.expression.ValueExpressionVisitor;
import com.exasol.sql.expression.function.Function;
import com.exasol.sql.expression.function.FunctionName;
import com.exasol.util.AbstractTreeNode;

/**
 * This class represents a function in the Exasol database.
 */
public class ExasolFunction extends AbstractTreeNode implements Function {
    private static final List<String> functionsWithoutParenthesis = Arrays.asList("SYSDATE", "CURRENT_SCHEMA",
            "CURRENT_SESSION", "CURRENT_STATEMENT", "CURRENT_USER", "ROWNUM", "ROWID", "SCOPE_USER", "USER");
    private final FunctionName functionName;
    private final List<ValueExpression> valueExpressions;

    private ExasolFunction(final FunctionName functionName, final List<ValueExpression> valueExpressions) {
        this.functionName = functionName;
        this.valueExpressions = valueExpressions;
        for (final ValueExpression valueExpression : this.valueExpressions) {
            addChild(valueExpression);
            valueExpression.setParent(this);
        }
    }

    /**
     * Create a new {@link ExasolFunction} instance.
     * 
     * @param functionName name of the function
     * @return new {@link ExasolFunction}
     */
    public static ExasolFunction of(final FunctionName functionName) {
        return new ExasolFunction(functionName, Collections.emptyList());
    }

    /**
     * Create a new {@link ExasolFunction} instance.
     *
     * @param functionName name of the function
     * @param valueExpressions zero or more value expressions
     * @return new {@link ExasolFunction}
     */
    public static ExasolFunction of(final FunctionName functionName, final ValueExpression... valueExpressions) {
        return new ExasolFunction(functionName, Arrays.asList(valueExpressions));
    }

    @Override
    public String getFunctionName() {
        return this.functionName.name();
    }

    @Override
    public boolean hasParenthesis() {
        return !functionsWithoutParenthesis.contains(this.functionName.name());
    }

    @Override
    public void accept(final ValueExpressionVisitor visitor) {
        visitor.visit(this);
        for (final ValueExpression valueExpression : this.valueExpressions) {
            valueExpression.accept(visitor);
        }
        visitor.leave(this);
    }
}