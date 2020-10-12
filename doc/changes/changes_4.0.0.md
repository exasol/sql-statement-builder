# SQL Statement Builder 4.0.0, released 2020-07-06

## Bug Fixes
 
* #78: Fixed the bug with BooleanExpression quotation.

## Features / Enhancements
 
* #80: Added ossindex-maven-plugin and versions-maven-plugin, updated dependencies.
* #81: Ported from Java 8 to Java 11.
* #76: Added `SELECT FROM VALUES ... AS` support.
* #77: Added `SELECT FROM (SELECT ...)` support.

## Refactoring

* #27: Fix sonar findings.

## Dependency updates
 
* Added `org.sonatype.ossindex.maven:ossindex-maven-plugin:3.1.0`
* Added `org.codehaus.mojo:versions-maven-plugin:2.7`
* Added `org.apache.maven.plugins:maven-enforcer-plugin:3.0.0-M3`
* Updated `maven-assembly-plugin` from 3.2.0 to 3.3.0
* Updated `org.junit.jupiter:junit-jupiter-engine` from 5.4.2 to 5.6.2
* Updated `org.mockito:mockito-core` from 2.24.0 to 3.3.3
* Updated `org.junit.jupiter:junit-jupiter-params` from 5.4.2 to 5.6.2
* Updated `nl.jqno.equalsverifier:equalsverifier` from 3.1.4 to 3.4.1
* Updated `org.mockito:mockito-junit-jupiter` from 2.23.4 to 3.3.3
* Updated `org.junit.platform:junit-platform-runner` from 1.4.2 to 1.6.2
* Updated `org.apache.maven.plugins:maven-source-plugin` from 3.0.1 to 3.2.1
* Updated `org.itsallcode:openfasttrace-maven-plugin` from 0.0.2 to 0.1.0
* Removed `org.junit.jupiter:junit-jupiter-api`
* Removed `org.junit.platform:junit-platform-launcher`