# SQL Statement Builder 4.4.0, released 2021-XX-XX

Code Name: More Predicates

## Features / Improvements

* #104: Added additional Exasol predicates

## Refactoring
 
* #98: Refactored comparison and like class structure
   The refactoring changed the internal representation of the `Comparison`.
   The public API from `BooleanTerm` did however not change.
* #95: Refactored `ValueExpressionVisitor` 

## Dependency Updates

* Updated `org.mockito:mockito-core:3.5.13` to `3.8.0`
* Updated `org.mockito:mockito-junit-jupiter:3.5.13` to `3.8.0`
* Updated `nl.jqno.equalsverifier:equalsverifier:3.4.3` to `3.5.5`

### Plugin Updates

* Updated `org.codehaus.mojo:versions-maven-plugin:2.7` to `2.8.1`
* Updated `org.itsallcode:openfasttrace-maven-plugin:0.1.0` to `1.0.0`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.5` to `0.8.6`

