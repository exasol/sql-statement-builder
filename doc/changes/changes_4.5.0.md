# Exasol SQL Statement Builder 4.5.0, released 2021-09-??

Code name: Support more Aggregate and Analytic functions

## Summary

This release supports all aggregate and analytics functsions provided by Exasol, e.g. `GROUPING[_ID]`, `PERCENTILE_CONT`, `NTH_VALUE` and many more. See the [ticket](https://github.com/exasol/sql-statement-builder/issues/72) for a complete list.

We also addes support for the keywords `DISTINCT` and `ANY` as well as the [over_clause](https://docs.exasol.com/sql_references/functions/analyticfunctions.htm?Highlight=over_clause) for analytic functions.

## Features

* #72: Added support for more Aggregate and Analytic functions

## Refactoring

* #117: Fixed sonar findings
* #102: Refactored ColumnsDefinitionRenderer

## Dependency Updates

### Test Dependency Updates

* Updated `nl.jqno.equalsverifier:equalsverifier:3.6.1` to `3.7.1`
* Updated `org.junit.jupiter:junit-jupiter:5.7.2` to `5.8.1`
* Updated `org.mockito:mockito-core:3.10.0` to `3.12.4`
* Updated `org.mockito:mockito-junit-jupiter:3.10.0` to `3.12.4`

### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:0.4.0` to `0.6.0`
* Updated `com.exasol:project-keeper-maven-plugin:0.7.1` to `1.2.0`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.0.0-M3` to `3.0.0`
* Updated `org.apache.maven.plugins:maven-gpg-plugin:1.6` to `3.0.1`
* Updated `org.apache.maven.plugins:maven-javadoc-plugin:3.2.0` to `3.3.1`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.6` to `0.8.7`
