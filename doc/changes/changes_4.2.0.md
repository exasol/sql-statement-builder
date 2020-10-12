# SQL Statement Builder 4.2.0, released 2020-10-12

Code Name: LIKE predicate support

## Summary

In this release we have deprecated `arithmeticExpression(...)` methods of `Select` class. 
Please, use `valueExpression(...)` methods instead.

## Features / Enhancements
 
* #91: Added support for LIKE predicate.

## Dependency updates

* Added org.junit.jupiter:junit-jupiter:5.7.0
* Updated org.mockito:mockito-core:3.3.3 to version 3.5.13
* Updated org.mockito:mockito-junit-jupiter:3.3.3 to version 3.5.13
* Updated nl.jqno.equalsverifier:equalsverifier:3.4.1 to version 3.4.3
* Removed org.junit.jupiter:junit-jupiter-engine
* Removed org.junit.jupiter:junit-jupiter-params
* Removed org.junit.platform:junit-platform-runner