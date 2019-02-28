# Exasol SQL Statement Builder User Guide

You can follow this guide to learn more about how to use the SQL Statement
Builder. It provides explanation of currently supported features with various
code examples.

## Overview

The Exasol SQL Statement Builder abstracts programmatic creation of SQL
statements and is intended to replace ubiquitous string concatenation solutions
which make the code hard to read and are prone to errors and security risks.

SQL Statement Builder allows users to create a SQL statement classes using
[fluent programming][fluent] that can render into a string.

**Currently supported SQL statements:**

- [CREATE TABLE](#create-table)
- SELECT
- INSERT INTO

## CREATE TABLE

The `CreateTable` class of the Sql Statement Builder implements SQL CREATE TABLE
functionality.

### Usage

1. Create an instance of the `CreateTable` class through the `StatementFactory`

  ```java
  CreateTable createTable = StatementFactory.getInstance().createTable("tableName");
  ```

2. Create columns with desired data types using fluent programming

  The column order in a final rendered string will be the same as a sequence of
  the method calls.

  For example, create a table with three columns (decimal, char and boolean):

  ```java
  createTable.decimalColumn("col_decimal", 9, 0)
             .charColumn("col_char", 10)
             .booleanColumn("col_boolean");
  ```

  Please keep in mind that the column name if required when creating a column.
  Additionally, some column types require extra parameters, for instance
  `VARCHAR`.

  **Currently the following column types are supported:**

  | Column Type                      | Parameter Required | Example                                                          |
  |:---------------------------------|:-------------------|:-----------------------------------------------------------------|
  |``BOOLEAN``                       | ``false``          | `createTable.booleanColumn("col_bool")`                          |
  |``CHAR``                          | ``true``           | `createTable.charColumn("col_char")`                             |
  |``DATE``                          | ``false``          | `createTable.dateColumn("col_date")`                             |
  |``DECIMAL``                       | ``true``           | `createTable.decimalColumn("col_dec", 18, 0)`                    |
  |``DOUBLE PRECISION``              | ``false``          | `createTable.doublePrecisionColumn("col_double_precision")`      |
  |``INTERVAL DAY TO SECOND``        | ``true``           | `createTable.intervalDayToSecondColumn("col_intdaytosec", 2, 3)` |
  |``INTERVAL YEAR TO MONTH``        | ``true``           | `createTable.intervalYearToMonthColumn("col_intyeartomonth", 2)` |
  |``TIMESTAMP``                     | ``false``          | `createTable.timestampColumn("col_timestamp")`                   |
  |``TIMESTAMP WITH LOCAL TIME ZONE``| ``false``          | `createTable.timestampWithLocalTimeZoneColumn("col_tswithzone")` |
  |``VARCHAR``                       | ``true``           | `createTable.varcharColumn("col_varchar", 100)`                  |

  You can find more information about the column types in JavaDoc files.

3. Render the instance of `CreateTable` class

  Create an instance of the `CreateTableRenderer` using the `create()` method of
  the class. It can be created with the default configuration or with a custom
  configuration.

  An example of creating a custom configuration:

  ```java
  StringRendererConfig config = StringRendererConfig.builder().lowerCase(true).build();
  ```
  If you add a custom configuration, pass an instance to the `create()` method. If
  not, run the method without parameters:

  ```java
  CreateTableRenderer renderer = CreateTableRenderer.create();
  ```

  Then call accept method of the `CreateTable` instance and pass the renderer as
  an argument:

  ```java
  createTable.accept(renderer);
  ```

  And finally render the SQl statement:

  ```java
  String renderedString = renderer.render();
  ```

- The whole example code

  ```java
  CreateTable createTable = StatementFactory.getInstance().createTable("tableName");

  createTable.decimalColumn("col_decimal", 9, 0)
             .charColumn("col_char", 10)
             .booleanColumn("col_boolean");

  //optional step: add config
  StringRendererConfig config = StringRendererConfig.builder().lowerCase(true).build();
  CreateTableRenderer renderer = CreateTableRenderer.create(config);
  createTable.accept(renderer);

  String renderedString = renderer.render();
  ```

[fluent]: https://en.wikipedia.org/wiki/Fluent_interface