# Exasol SQL Statement Builder 4.6.2, released 2026-??-??

Code name:

## Summary

This release mark no-args constructor of class `com.exasol.datatype.type.Timestamp` as deprecated. This avoids accidentally using the default timestamp precision 3.

## Refactoring

* #187: Mark no-args constructor of class `com.exasol.datatype.type.Timestamp` as deprecated

## Dependency Updates

### Test Dependency Updates

* Updated `org.junit.jupiter:junit-jupiter-params:5.13.4` to `5.14.4`
* Updated `org.mockito:mockito-junit-jupiter:5.20.0` to `5.23.0`

### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:2.0.5` to `2.1.0`
* Updated `com.exasol:project-keeper-maven-plugin:5.4.3` to `5.7.4`
* Removed `com.exasol:quality-summarizer-maven-plugin:0.2.1`
* Updated `io.github.git-commit-id:git-commit-id-maven-plugin:9.0.2` to `10.0.0`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.14.1` to `3.15.0`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.6.2` to `3.6.3`
* Updated `org.apache.maven.plugins:maven-resources-plugin:3.3.1` to `3.5.0`
* Updated `org.apache.maven.plugins:maven-site-plugin:3.21.0` to `3.22.0`
* Updated `org.apache.maven.plugins:maven-source-plugin:3.2.1` to `3.4.0`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.5.4` to `3.5.6`
* Added `org.codehaus.mojo:build-helper-maven-plugin:3.6.1`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.19.1` to `2.21.0`
* Updated `org.itsallcode:openfasttrace-maven-plugin:2.3.0` to `2.3.1`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.14` to `0.8.15`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:5.2.0.4988` to `5.7.0.6970`
* Updated `org.sonatype.central:central-publishing-maven-plugin:0.9.0` to `0.11.0`
* Added `org.spdx:spdx-maven-plugin:1.0.4`
