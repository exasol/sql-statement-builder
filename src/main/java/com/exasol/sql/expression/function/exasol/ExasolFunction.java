package com.exasol.sql.expression.function.exasol;

import java.util.*;

import com.exasol.sql.expression.ValueExpression;
import com.exasol.sql.expression.ValueExpressionVisitor;
import com.exasol.sql.expression.function.Function;
import com.exasol.sql.expression.function.FunctionName;
import com.exasol.util.AbstractTreeNode;

public class ExasolFunction extends AbstractTreeNode implements Function {
    private final FunctionName functionName;
    private final List<ValueExpression> valueExpressions;
    private final String derivedColumnName;

    private ExasolFunction(final Builder builder) {
        this.functionName = builder.functionName;
        this.valueExpressions = builder.valueExpressions;
        this.derivedColumnName = builder.derivedColumnName;
        for (final ValueExpression valueExpression : this.valueExpressions) {
            addChild(valueExpression);
            valueExpression.setParent(this);
        }
    }

    @Override
    public String getFunctionName() {
        return this.functionName.name();
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
    public static Builder builder(final FunctionName functionName) {
        return new Builder(functionName);
    }

    /**
     * Builder for {@link ExasolFunction}.
     */
    public static final class Builder {
        private final FunctionName functionName;
        private final List<ValueExpression> valueExpressions = new ArrayList<>();
        private String derivedColumnName;

        private Builder(final FunctionName functionName) {
            this.functionName = functionName;
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
            return new ExasolFunction(this);
        }
    }
}