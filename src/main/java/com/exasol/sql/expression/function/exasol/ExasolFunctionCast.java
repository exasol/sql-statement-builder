package com.exasol.sql.expression.function.exasol;

import java.util.List;

import com.exasol.datatype.type.DataType;
import com.exasol.sql.expression.ValueExpression;
import com.exasol.sql.expression.ValueExpressionVisitor;
import com.exasol.sql.expression.function.FunctionName;

/**
 * This class represents the Exasol CAST function.
 */
public class ExasolFunctionCast extends ExasolFunction {
    private final DataType type;

    private ExasolFunctionCast(final ValueExpression value, final DataType type) {
        super(ExasolScalarFunctionCast.CAST, List.of(value));
        this.type = type;
    }

    /**
     * Create a new {@link ExasolFunctionCast} instance.
     *
     * @param valueExpression value to cast
     * @param type            type to cast the value to
     * @return new {@link ExasolFunctionCast}
     */
    public static ExasolFunctionCast of(final ValueExpression valueExpression, final DataType type) {
        return new ExasolFunctionCast(valueExpression, type);
    }

    /**
     * Get the value to cast.
     * 
     * @return value to cast
     */
    public ValueExpression getValue() {
        return this.valueExpressions.get(0);
    }

    /**
     * Get the type to cast the value to.
     * 
     * @return type to cast the value to
     */
    public DataType getType() {
        return this.type;
    }

    @Override
    public boolean hasParenthesis() {
        return true;
    }

    @Override
    public void accept(final ValueExpressionVisitor visitor) {
        visitor.visit(this);
    }

    private enum ExasolScalarFunctionCast implements FunctionName {
        CAST
    }
}