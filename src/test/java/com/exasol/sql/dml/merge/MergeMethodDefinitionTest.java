package com.exasol.sql.dml.merge;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.sql.Fragment;
import com.exasol.sql.expression.BooleanLiteral;

@ExtendWith(MockitoExtension.class)
public class MergeMethodDefinitionTest {
    private DummyMergeMethod mergeMethod;
    @Mock
    private Merge merge;

    @BeforeEach
    public void beforeEach() {
        this.mergeMethod = new DummyMergeMethod(this.merge);
    }

    @Test
    public void testGetWhere() {
        this.mergeMethod.where(BooleanLiteral.of(true));
        assertThat(this.mergeMethod.getWhere().getExpression(), instanceOf(BooleanLiteral.class));
    }

    @Test
    public void testHasWhereFalseByDefault() {
        assertThat(this.mergeMethod.hasWhere(), equalTo(false));
    }

    @Test
    public void testHasWhere() {
        this.mergeMethod.where(BooleanLiteral.of(true));
        assertThat(this.mergeMethod.hasWhere(), equalTo(true));
    }

    private static class DummyMergeMethod extends MergeMethodDefinition {
        public DummyMergeMethod(final Fragment root) {
            super(root);
        }
    }
}