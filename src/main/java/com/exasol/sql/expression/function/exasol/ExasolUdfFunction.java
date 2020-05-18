package com.exasol.sql.expression.function.exasol;

import java.util.*;

import com.exasol.sql.ColumnsDefinition;
import com.exasol.sql.expression.ValueExpression;
import com.exasol.sql.expression.function.AbstractFunction;

/**
 * This class represents a User Defined Function in the Exasol database.
 */
public class ExasolUdfFunction extends AbstractFunction {
    private final Optional<ColumnsDefinition> emitsColumnsDefinition;

    private ExasolUdfFunction(final String functionName, final Optional<ColumnsDefinition> emitsColumnsDefinition,
            final List<ValueExpression> valueExpressions) {
        super(functionName, valueExpressions);
        this.emitsColumnsDefinition = emitsColumnsDefinition;
    }

    /**
     * Create a new {@link ExasolUdfFunction} instance.
     *
     * @param functionName name of the function
     * @param emitsColumnsDefinition column definitions for emits
     * @param valueExpressions zero or more value expressions
     * @return new {@link ExasolUdfFunction}
     */
    public static ExasolUdfFunction of(final String functionName, final ColumnsDefinition emitsColumnsDefinition,
            final ValueExpression... valueExpressions) {
        return new ExasolUdfFunction(functionName, Optional.of(emitsColumnsDefinition),
                Arrays.asList(valueExpressions));
    }

    /**
     * Create a new {@link ExasolUdfFunction} instance.
     *
     * @param functionName name of the function
     * @param valueExpressions zero or more value expressions
     * @return new {@link ExasolUdfFunction}
     */
    public static ExasolUdfFunction of(final String functionName, final ValueExpression... valueExpressions) {
        return new ExasolUdfFunction(functionName, Optional.empty(), Arrays.asList(valueExpressions));
    }

    @Override
    public boolean hasParenthesis() {
        return true;
    }

    /**
     * Check if the EMITS part is required.
     * 
     * @return true if the EMITS part is required
     */
    public boolean hasEmitsColumnsDefinition() {
        return this.emitsColumnsDefinition.isPresent();
    }

    /**
     * Get a columns definition for the EMITS.
     * 
     * @return optional of {@link ColumnsDefinition}
     */
    public Optional<ColumnsDefinition> getEmitsColumnsDefinition() {
        return this.emitsColumnsDefinition;
    }
}