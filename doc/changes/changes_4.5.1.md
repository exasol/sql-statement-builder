# Exasol SQL Statement Builder 4.5.1, released 2021-03-??

Code name: Java 17 compatibility

## Summary

In this release we added compatibility for Java 17 and updated dependencies.

We also fixed typos in window frame clauses ("Preceding" instead of "Preceeding").

## Refactoring

* #119: Made Java 17 compatible
* #129: Fixed broken links and updated dependencies.

## Dependency Updates

### Compile Dependency Updates

* Updated `com.exasol:error-reporting-java:0.4.0` to `0.4.1`

### Test Dependency Updates

* Updated `nl.jqno.equalsverifier:equalsverifier:3.7.1` to `3.7.2`
* Updated `org.mockito:mockito-core:3.12.4` to `4.0.0`
* Updated `org.mockito:mockito-junit-jupiter:3.12.4` to `4.0.0`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:1.2.0` to `1.3.4`
