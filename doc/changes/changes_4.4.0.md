# SQL Statement Builder 4.4.0, released 2021-03-04

Code Name: More Predicates

## Summary

In this release, we have added additional Exasol predicates such as `IS [NOT]
NULL` or `[NOT] BETWEEN`. We also refactored the comparison and `LIKE`
operators.

## Features / Improvements

* #104: Added additional predicates

## Refactoring
 
* #98: Refactored comparison and like class structure
   The refactoring changed the internal representation of the `Comparison`.
   The public API from `BooleanTerm` did however not change.
* #95: Refactored `ValueExpressionVisitor` 

## Dependency Updates

* Updated `org.mockito:mockito-core:3.5.13` to `3.8.0`
* Updated `org.mockito:mockito-junit-jupiter:3.5.13` to `3.8.0`
* Updated `org.junit.jupiter:junit-jupiter:5.7.0` to `5.7.1`
* Updated `nl.jqno.equalsverifier:equalsverifier:3.4.3` to `3.5.5`

### Plugin Updates

* Updated `org.codehaus.mojo:versions-maven-plugin:2.7` to `2.8.1`
* Updated `org.itsallcode:openfasttrace-maven-plugin:0.1.0` to `1.0.0`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.5` to `0.8.6`

