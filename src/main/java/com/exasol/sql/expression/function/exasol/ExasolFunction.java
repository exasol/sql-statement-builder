package com.exasol.sql.expression.function.exasol;

import java.util.*;

import com.exasol.sql.expression.*;
import com.exasol.sql.expression.function.Function;
import com.exasol.sql.expression.function.FunctionName;

public class ExasolFunction extends AbstractValueExpression implements Function {
    private final FunctionName functionName;
    private final List<ValueExpression> valueExpressions;
    private final String derivedColumnName;

    private ExasolFunction(final Builder builder) {
        this.functionName = builder.functionName;
        this.valueExpressions = builder.valueExpressions;
        this.derivedColumnName = builder.derivedColumnName;
    }

    @Override
    public String getFunctionName() {
        return this.functionName.name();
    }

    @Override
    public List<ValueExpression> getValueExpressions() {
        return this.valueExpressions;
    }

    @Override
    public boolean hasDerivedColumnName() {
        return this.derivedColumnName != null && !this.derivedColumnName.isEmpty();
    }

    @Override
    public String getDerivedColumnName() {
        return this.derivedColumnName;
    }

    @Override
    public void accept(final ValueExpressionVisitor visitor) {
        visitor.visit(this);
        for (final ValueExpression valueExpression : this.valueExpressions) {
            valueExpression.accept(visitor);
        }
        visitor.leave(this);
    }

    /**
     * Get a builder for {@link ExasolFunction}.
     *
     * @return builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder for {@link ExasolFunction}.
     */
    public static final class Builder {
        private FunctionName functionName;
        private final List<ValueExpression> valueExpressions = new ArrayList<>();
        private String derivedColumnName;

        private Builder() {
        }

        /**
         * Add a function name.
         *
         * @param functionName name of a function
         * @return this instance for fluent programming
         */
        public Builder functionName(final FunctionName functionName) {
            this.functionName = functionName;
            return this;
        }

        /**
         * Add one or more value expressions.
         *
         * @param valueExpression values expressions for function
         * @return this instance for fluent programming
         */
        public Builder valueExpression(final ValueExpression... valueExpression) {
            this.valueExpressions.addAll(Arrays.asList(valueExpression));
            return this;
        }

        /**
         * Set a name for a derived column.
         *
         * @param derivedColumnName a name for a derived column
         * @return this instance for fluent programming
         */
        public Builder derivedColumnName(final String derivedColumnName) {
            this.derivedColumnName = derivedColumnName;
            return this;
        }

        /**
         * Build a new {@link ExasolFunction}.
         *
         * @return new {@link ExasolFunction}
         */
        public ExasolFunction build() {
            validateParameters();
            return new ExasolFunction(this);
        }

        private void validateParameters() {
            if (this.functionName == null || this.valueExpressions.isEmpty()) {
                throw new IllegalArgumentException(
                        "Please add functionName and valueExpression parameters to a Function's builder.");
            }
        }
    }
}