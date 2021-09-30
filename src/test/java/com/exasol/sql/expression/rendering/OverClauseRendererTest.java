package com.exasol.sql.expression.rendering;

import static com.exasol.sql.expression.ExpressionTerm.column;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.exasol.hamcrest.ExceptionAssertions;
import com.exasol.sql.expression.function.exasol.OverClause;
import com.exasol.sql.expression.function.exasol.WindowFrameClause.UnitType;
import com.exasol.sql.expression.function.exasol.WindowFrameClause.WindowFrameType;
import com.exasol.sql.rendering.StringRendererConfig;

class OverClauseRendererTest {

    private OverClauseRenderer renderer;

    @BeforeEach
    void setUp() {
        this.renderer = new OverClauseRenderer(StringRendererConfig.createDefault());
    }

    @Test
    void testVisitEmptyClause() {
        assertOverClauseRendered(new OverClause(), " OVER()");
    }

    @Test
    void testVisitWindowFrameEmptyFails() {
        final OverClause clause = new OverClause().windowFrame(frame -> frame);
        ExceptionAssertions.assertThrowsWithMessage(IllegalStateException.class, () -> this.renderer.visit(clause),
                "E-ESB-3: Type not defined. Set type the window frame.");
    }

    @Test
    void testVisitWindowFrameWithoutUnitFails() {
        final OverClause clause = new OverClause().windowFrame(frame -> frame.type(WindowFrameType.RANGE));
        ExceptionAssertions.assertThrowsWithMessage(IllegalStateException.class, () -> this.renderer.visit(clause),
                "E-ESB-1: First unit not defined. At lease one unit is required for a window frame");
    }

    @Test
    void testVisitWindowFrameMissingExpressionFails() {
        final OverClause clause = new OverClause()
                .windowFrame(frame -> frame.type(WindowFrameType.RANGE).unit(UnitType.PRECEEDING));
        ExceptionAssertions.assertThrowsWithMessage(IllegalStateException.class, () -> this.renderer.visit(clause),
                "E-ESB-2: Expression is required for window frame units PRECEEDING and FOLLOWING. Add expression for unit types PRECEEDING and FOLLOWING.");
    }

    @Test
    void testVisitWindowFrameWithExpressionSucceeds() {
        assertOverClauseRendered(
                new OverClause().windowFrame(
                        frame -> frame.type(WindowFrameType.RANGE).unit(column("col2"), UnitType.PRECEEDING)),
                " OVER( RANGE col2 PRECEEDING)");
    }

    private void assertOverClauseRendered(final OverClause overClause, final String expected) {
        this.renderer.visit(overClause);
        assertThat(this.renderer.render(), equalTo(expected));
    }
}
