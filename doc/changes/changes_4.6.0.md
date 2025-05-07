# Exasol SQL Statement Builder 4.6.0, released 2025-05-07

Code name: Timestamps with precision

## Summary

This release adds support for specifying the fractional seconds precision for columns of type TIMESTAMP and 
TIMESTAMP WITH LOCAL TIMEZONE

## Features

* #176 Support TIMESTAMP with precision

## Dependency Updates

### Test Dependency Updates

* Updated `nl.jqno.equalsverifier:equalsverifier:3.14.1` to `3.19.4`
* Removed `org.hamcrest:hamcrest-all:1.3`
* Added `org.hamcrest:hamcrest:3.0`
* Updated `org.junit.jupiter:junit-jupiter:5.9.2` to `5.12.2`
* Updated `org.mockito:mockito-core:5.2.0` to `5.17.0`
* Updated `org.mockito:mockito-junit-jupiter:5.2.0` to `5.17.0`

### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:1.2.2` to `2.0.3`
* Updated `com.exasol:project-keeper-maven-plugin:2.9.6` to `5.0.1`
* Added `com.exasol:quality-summarizer-maven-plugin:0.2.0`
* Added `io.github.git-commit-id:git-commit-id-maven-plugin:9.0.1`
* Removed `io.github.zlika:reproducible-build-maven-plugin:0.16`
* Added `org.apache.maven.plugins:maven-artifact-plugin:3.6.0`
* Updated `org.apache.maven.plugins:maven-clean-plugin:3.2.0` to `3.4.1`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.10.1` to `3.14.0`
* Updated `org.apache.maven.plugins:maven-deploy-plugin:3.1.0` to `3.1.4`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.2.1` to `3.5.0`
* Updated `org.apache.maven.plugins:maven-gpg-plugin:3.0.1` to `3.2.7`
* Updated `org.apache.maven.plugins:maven-install-plugin:3.1.2` to `3.1.4`
* Updated `org.apache.maven.plugins:maven-javadoc-plugin:3.4.1` to `3.11.2`
* Updated `org.apache.maven.plugins:maven-site-plugin:3.12.1` to `3.21.0`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.0.0-M8` to `3.5.2`
* Added `org.apache.maven.plugins:maven-toolchains-plugin:3.2.0`
* Added `org.basepom.maven:duplicate-finder-maven-plugin:2.0.1`
* Updated `org.codehaus.mojo:flatten-maven-plugin:1.3.0` to `1.7.0`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.14.2` to `2.18.0`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.8` to `0.8.12`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.1.2184` to `5.0.0.4389`
* Updated `org.sonatype.plugins:nexus-staging-maven-plugin:1.6.13` to `1.7.0`
