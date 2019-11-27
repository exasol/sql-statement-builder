package com.exasol.sql.dml.merge;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.sameInstance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NotMatchedClauseTest {
    private NotMatchedClause notMatched;

    @BeforeEach
    void beforeEach() {
        this.notMatched = new NotMatchedClause(null);
    }

    @Test
    void testHasInsertFalseByDefault() {
        assertThat(this.notMatched.hasInsert(), equalTo(false));
    }

    @Test
    void testHasInsertTrue() {
        this.notMatched.thenInsert();
        assertThat(this.notMatched.hasInsert(), equalTo(true));
    }

    @Test
    void testGetInsert() {
        final MergeInsertClause insert = this.notMatched.thenInsert();
        assertThat(this.notMatched.getInsert(), sameInstance(insert));
    }
}