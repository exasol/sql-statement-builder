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

* Removed `com.exasol:error-reporting-java:0.4.0`

### Test Dependency Updates

* Updated `nl.jqno.equalsverifier:equalsverifier:3.7.1` to `3.10`
* Updated `org.junit.jupiter:junit-jupiter:5.8.1` to `5.8.2`
* Updated `org.mockito:mockito-core:3.12.4` to `4.4.0`
* Updated `org.mockito:mockito-junit-jupiter:3.12.4` to `4.4.0`

### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:0.6.0` to `0.7.1`
* Updated `com.exasol:project-keeper-maven-plugin:1.2.0` to `2.2.0`
* Updated `io.github.zlika:reproducible-build-maven-plugin:0.13` to `0.15`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.8.1` to `3.9.0`
* Updated `org.apache.maven.plugins:maven-deploy-plugin:2.8.2` to `3.0.0-M1`
* Updated `org.apache.maven.plugins:maven-jar-plugin:3.2.0` to `3.2.2`
* Updated `org.apache.maven.plugins:maven-site-plugin:3.9.1` to `3.11.0`
* Added `org.codehaus.mojo:flatten-maven-plugin:1.2.7`
* Updated `org.itsallcode:openfasttrace-maven-plugin:1.0.0` to `1.5.0`