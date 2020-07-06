package com.exasol.sql.dml.merge;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.sameInstance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MatchedClauseTest {
    private MatchedClause matched;

    @BeforeEach
    void beforeEach() {
        this.matched = new MatchedClause(null);
    }

    @Test
    void testHasUpdateFalseByDefault() {
        assertThat(this.matched.hasUpdate(), equalTo(false));
    }

    @Test
    void testHasUpdateTrue() {
        this.matched.thenUpdate();
        assertThat(this.matched.hasUpdate(), equalTo(true));
    }

    @Test
    void testGetUpdate() {
        final MergeUpdateClause update = this.matched.thenUpdate();
        assertThat(this.matched.getUpdate(), sameInstance(update));
    }

    @Test
    void testHasDeleteFalseByDefault() {
        assertThat(this.matched.hasDelete(), equalTo(false));
    }

    @Test
    void testHasDeleteTrue() {
        this.matched.thenDelete();
        assertThat(this.matched.hasDelete(), equalTo(true));
    }

    @Test
    void testGetDelete() {
        final MergeDeleteClause delete = this.matched.thenDelete();
        assertThat(this.matched.getDelete(), sameInstance(delete));
    }
}