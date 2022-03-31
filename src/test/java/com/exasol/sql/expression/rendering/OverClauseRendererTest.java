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
                "Type not defined. Set type the window frame.");
    }

    @Test
    void testVisitWindowFrameWithoutUnitFails() {
        final OverClause clause = new OverClause().windowFrame(frame -> frame.type(WindowFrameType.RANGE));
        ExceptionAssertions.assertThrowsWithMessage(IllegalStateException.class, () -> this.renderer.visit(clause),
                "First unit not defined. At least one unit is required for a window frame.");
    }

    @Test
    void testVisitWindowFrameMissingExpressionFails() {
        final OverClause clause = new OverClause()
                .windowFrame(frame -> frame.type(WindowFrameType.RANGE).unit(UnitType.PRECEDING));
        ExceptionAssertions.assertThrowsWithMessage(IllegalStateException.class, () -> this.renderer.visit(clause),
                "Expression is required for window frame units PRECEDING and FOLLOWING."
                        +" Add expression for unit types PRECEDING and FOLLOWING.");
    }

    @Test
    void testVisitWindowFrameWithExpressionSucceeds() {
        assertOverClauseRendered(
                new OverClause().windowFrame(
                        frame -> frame.type(WindowFrameType.RANGE).unit(column("col2"), UnitType.PRECEDING)),
                " OVER( RANGE col2 PRECEDING)");
    }

    private void assertOverClauseRendered(final OverClause overClause, final String expected) {
        this.renderer.visit(overClause);
        assertThat(this.renderer.render(), equalTo(expected));
    }
}
