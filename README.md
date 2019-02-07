# sql-statement-builder

[![Build Status](https://travis-ci.com/EXASOL/sql-statement-builder.svg?branch=develop)](https://travis-ci.com/EXASOL/sql-statement-builder)

The Exasol SQL Statement Builder abstracts programmatic creation of SQL statements and is intended to replace ubiquitous string concatenation solutions which make the code hard to read and are prone to error and security risks.

Goals:

1. Foster clean and readable code
1. Allow for thorough validation of dynamic parts
1. Detect as many errors as possible at *compile time*
1. Don't repeat yourself (DRY)
1. Allow extension for different SQL dialects

## Usage

```java
import com.exasol.sql.StatementFactory;
import com.exasol.sql.SqlStatement;
import com.exasol.sql.rendering.SelectRenderer;

SqlStatement statement = StatementFactory.getInstance()
  .select().field("firstname", "lastname")
  .from().table("person");

String statementText = SqlStatementRenderer.render(statement);
```

## Development

The following sub-sections provide information about building and extending the project.

### Build Time Dependencies

The list below show all build time dependencies in alphabetical order. Note that except the Maven build tool all required modules are downloaded automatically by Maven.

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

### Planned Milestones

The milestones listed below are a rough outline and might be subject to change depending on which constructs are needed more. The plan will be updated accordingly.

#### M1

* Basic support for Data Query Language (DQL) statement constructs (SELECT, FROM, JOIN, WHERE)
* Rendering to string
* Exasol Dialect only

#### M2

* Validation for constructs from M1

(Later milestones will always include validation of the newly learned milestones)

#### M3

* Scalar functions

#### M4

* Sub-Selects including validation

#### Later Milstones (very coarse)

* Data Manipulation Language (DML) statements
* Data Definition Language (DDL) statements
* Support for Standard SQL
* Support for other dialects (help welcome!)
