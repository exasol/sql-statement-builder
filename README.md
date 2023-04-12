# SQL Statement Builder

[![Build Status](https://github.com/exasol/sql-statement-builder/actions/workflows/ci-build.yml/badge.svg)](https://github.com/exasol/sql-statement-builder/actions/workflows/ci-build.yml)
[![Maven Central &ndash; Exasol SQL Statement Builder](https://img.shields.io/maven-central/v/com.exasol/sql-statement-builder-java8)](https://search.maven.org/artifact/com.exasol/sql-statement-builder-java8)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Asql-statement-builder-java8&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.exasol%3Asql-statement-builder-java8)

[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Asql-statement-builder-java8&metric=security_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Asql-statement-builder-java8)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Asql-statement-builder-java8&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Asql-statement-builder-java8)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Asql-statement-builder-java8&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Asql-statement-builder-java8)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Asql-statement-builder-java8&metric=sqale_index)](https://sonarcloud.io/dashboard?id=com.exasol%3Asql-statement-builder-java8)

[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Asql-statement-builder-java8&metric=code_smells)](https://sonarcloud.io/dashboard?id=com.exasol%3Asql-statement-builder-java8)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Asql-statement-builder-java8&metric=coverage)](https://sonarcloud.io/dashboard?id=com.exasol%3Asql-statement-builder-java8)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Asql-statement-builder-java8&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=com.exasol%3Asql-statement-builder-java8)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Asql-statement-builder-java8&metric=ncloc)](https://sonarcloud.io/dashboard?id=com.exasol%3Asql-statement-builder-java8)

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
Select select = StatementFactory.getInstance().select()
    .field("fieldA", "tableA.fieldB", "tableB.*");
select.from().table("schemaA.tableA");
select.limit(10);
StringRendererConfig config = StringRendererConfig.builder().quoteIdentifiers(true).build();
SelectRenderer renderer = new SelectRenderer(config);
select.accept(renderer);
String sql = renderer.render();
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
