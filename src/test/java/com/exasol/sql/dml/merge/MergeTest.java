package com.exasol.sql.dml.merge;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MergeTest {
    private Merge merge;

    @BeforeEach
    void beforeEach() {
        this.merge = new Merge("dst").using("src");
    }

    @Test
    void testHasMatchedFalseByDefault() {
        assertThat(this.merge.hasMatched(), equalTo(false));
    }

    @Test
    void testHasMatchedTrue() {
        this.merge.whenMatched();
        assertThat(this.merge.hasMatched(), equalTo(true));
    }

    @Test
    void testHasNotMatchedFalseByDefault() {
        assertThat(this.merge.hasNotMatched(), equalTo(false));
    }

    @Test
    void testHasNotMatchedTrue() {
        this.merge.whenNotMatched();
        assertThat(this.merge.hasNotMatched(), equalTo(true));
    }
}