# SQL Statement Builder

![exasol-testcontainers logo](doc/images/sql-statement-builder_128x128.png)

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
final Select select = StatementFactory.getInstance().select() //
        .field("fieldA", "tableA.fieldB", "tableB.*");
select.from().table("schemaA.tableA");
select.limit(10);
final StringRendererConfig config = StringRendererConfig.builder().quoteIdentifiers(true).build();
final SelectRenderer renderer = new SelectRenderer(config);
select.accept(renderer);
final String sql = renderer.render();
```

## Table of Contents

### Information for Users

"Users" from the perspective of the `sql-statement-builder` are developers integrating the module into their own software.

* [User Guide](doc/user_guide/user_guide.md)
* [API Documentation](https://exasol.github.io/sql-statement-builder/index.html)
* [MIT License](LICENSE)
* [Changelog](doc/changes/changelog.md)
* [Dependencies](dependencies.md)

### Information for Developers

* [System Requirement Specification](doc/system_requirements.md)
* [Design](doc/design.md)