package com.exasol.sql.expression.rendering;

import com.exasol.sql.expression.*;
import com.exasol.sql.rendering.StringRendererConfig;
import com.exasol.util.TreeNode;

public class BooleanExpressionRenderer implements BooleanExpressionVisitor {
    private final StringRendererConfig config;
    private final StringBuilder front = new StringBuilder();
    private final StringBuilder back = new StringBuilder();
    private boolean needSeparator;

    public BooleanExpressionRenderer(final StringRendererConfig config) {
        this.config = config;
    }

    public BooleanExpressionRenderer() {
        this.config = new StringRendererConfig.Builder().build();
    }

    private void appendKeyWord(final String keyword) {
        appendSeparatorIfNecessary();
        this.front.append(this.config.produceLowerCase() ? keyword : keyword.toUpperCase());
        this.needSeparator = true;
    }

    @Override
    public void visit(final Not not) {
        appendKeyWord("NOT");
    }

    @Override
    public void visit(final And and) {

    }

    @Override
    public void visit(final Literal literal) {
        if (literal.isChild() && !literal.isFirstSibling()) {
            final TreeNode parent = literal.getParent();
            if (parent instanceof And) {
                appendKeyWord("AND");
            }
        }
        appendLiteral(literal.toString());
    }

    private void appendLiteral(final String string) {
        appendSeparatorIfNecessary();
        this.front.append(string);
        this.needSeparator = true;
    }

    private void appendSeparatorIfNecessary() {
        if (this.needSeparator) {
            this.front.append(" ");
        }
    }

    @Override
    public void visit(final BooleanTerm booleanTerm) {
        if (booleanTerm.isChild()) {
            this.front.append("(");
            this.back.append(")");
            this.needSeparator = false;
        }
    }

    public String render() {
        return this.front.append(this.back).toString();
    }
}