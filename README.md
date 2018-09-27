# sql-statement-builder

## Usage

```java
import com.exasol.sql.StatementFactory;
import com.exasol.sql.SqlStatement;
import com.exasol.sql.rendering.SqlStatementRenderer;

SqlStatement statement = StatementFactory.getInstance()
  .select()
  .field("firstname", "lastname")
  .from("person");

String statementText = SqlStatementRenderer.render(statement);
```

## Development

The following sub-sections provide information about building and extending the project.

### Build Time Dependencies

The list below show all build time dependencies in alphabetical order. Note that except the Maven build tool all required modules are downloaded automatically by Maven.

| Dependency                                                | Purpose                                                | License                       |
------------------------------------------------------------|--------------------------------------------------------|--------------------------------
| [Apache Maven](https://maven.apache.org/)                 | Build tool                                             | Apache License 2.0            |
| [Equals Verifier](https://github.com/jqno/equalsverifier) | Automatic contract checker for `equals()` and `hash()` | Apache License 2.0            |
| [Hamcrest](http://hamcrest.org/)                          | Advanced matchers for JUnit                            | GNU BSD-3-Clause              |
| [JUnit 5](https://junit.org/junit5/)                      | Unit testing framework                                 | Eclipse Public License 1.0    |
| [Mockito](http://site.mockito.org/)                       | Mocking framework                                      | MIT License                   |