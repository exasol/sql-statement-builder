# sql-statement-builder

[![Build Status](https://travis-ci.com/exasol/sql-statement-builder.svg?branch=develop)](https://travis-ci.com/exasol/sql-statement-builder)
[![Maven Central](https://img.shields.io/maven-central/v/com.exasol/sql-statement-builder)](https://search.maven.org/artifact/com.exasol/sql-statement-builder)

SonarCloud results:

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Asql-statement-builder&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.exasol%3Asql-statement-builder)

[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Asql-statement-builder&metric=security_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Asql-statement-builder)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Asql-statement-builder&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Asql-statement-builder)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Asql-statement-builder&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Asql-statement-builder)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Asql-statement-builder&metric=sqale_index)](https://sonarcloud.io/dashboard?id=com.exasol%3Asql-statement-builder)

[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Asql-statement-builder&metric=code_smells)](https://sonarcloud.io/dashboard?id=com.exasol%3Asql-statement-builder)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Asql-statement-builder&metric=coverage)](https://sonarcloud.io/dashboard?id=com.exasol%3Asql-statement-builder)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Asql-statement-builder&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=com.exasol%3Asql-statement-builder)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Asql-statement-builder&metric=ncloc)](https://sonarcloud.io/dashboard?id=com.exasol%3Asql-statement-builder)

The Exasol SQL Statement Builder abstracts programmatic creation of SQL statements and is intended to replace ubiquitous string concatenation solutions which make the code hard to read and are prone to error and security risks.

Goals:

1. Foster clean and readable code
1. Allow for thorough validation of dynamic parts
1. Detect as many errors as possible at *compile time*
1. Don't repeat yourself (DRY)
1. Allow extension for different SQL dialects

## In a Nutshell

The following example gives you an idea about what you can do with the SQL Statement Builder. Check our [user guide](doc/user_guide/user_guide.md) for more details.

```java
final Select select = StatementFactory.getInstance()
    .select()
    .field("fieldA", "tableA.fieldB", "tableB.*")
    .from()
    .table("schemaA.tableA")
    .limit(10);
final StringRendererConfig config = StringRendererConfig.builder().useQuotes().build();
final SelectRenderer renderer = new SelectRenderer(config);
final String sql = renderer.render(select);
```

## Table of Contents

### Information for Users

"Users" from the perspective of the `sql-statement-builder` are developers integrating the module into their own software.

* [User Guide](doc/user_guide/user_guide.md)
* [API Documentation](https://javadoc.io/doc/com.exasol/sql-statement-builder)
* [MIT License](LICENSE)

### Information for Developers

* [System Requirement Specification](doc/system_requirements.md)
* [Design](doc/design.md)

## Dependencies

### Build Dependencies

The list below show all build dependencies in alphabetical order. Note that except the Maven build tool all required modules are downloaded automatically by Maven.

| Dependency                                                                     | Purpose                                                | License                       |
---------------------------------------------------------------------------------|--------------------------------------------------------|--------------------------------
| [Apache Maven](https://maven.apache.org/)                                      | Build tool                                             | Apache License 2.0            |
| [Equals Verifier](https://github.com/jqno/equalsverifier)                      | Automatic contract checker for `equals()` and `hash()` | Apache License 2.0            |
| [Hamcrest](http://hamcrest.org/)                                               | Advanced matchers for JUnit                            | GNU BSD-3-Clause              |
| [JUnit 5](https://junit.org/junit5/)                                           | Unit testing framework                                 | Eclipse Public License 1.0    |
| [Maven GPG Plugin](https://maven.apache.org/plugins/maven-gpg-plugin/)         | Signs JARs                                             | Apache License 2.0            |
| [Maven JavaDoc Plugin](https://maven.apache.org/plugins/maven-javadoc-plugin/) | Creates JavaDoc JARs                                   | Apache License 2.0            |
| [Maven Source Plugin](https://maven.apache.org/plugins/maven-source-plugin/)   | Creates source JARs                                    | Apache License 2.0            |
| [Mockito](http://site.mockito.org/)                                            | Mocking framework                                      | MIT License                   |
| [OFT Maven Plugin](https://github.com/itsallcode/openfasttrace-maven-plugin)   | Requirement tracing                                    | GPL 3.0                       |
