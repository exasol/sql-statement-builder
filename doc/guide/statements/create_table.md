# CREATE TABLE

The `CreateTable` class of the SQL Statement Builder provides an entry 
point to defining a CREATE TABLE SQL statement.

## Usage

1. Create an instance of the `CreateTable` class through the `StatementFactory`

  ```java
  CreateTable createTable = StatementFactory.getInstance().createTable("tableName");
  ```

2. Create columns with desired data types using fluent programming

  Construct the columns in the order you want them to appear in the created table.

  For example, create a table with three columns (`DECIMAL`, `CHAR` and `BOOLEAN`):

  ```java
  createTable.decimalColumn("col_decimal", 9, 0)
             .charColumn("col_char", 10)
             .booleanColumn("col_boolean");
  ```

  Please keep in mind that the column name is required when creating a column.
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

  You can find more information about the column types in the SQL Statement Builder's JavaDoc API description.

3. Render the instance of `CreateTable` class

Please read about rendering [here](../rendering.md)


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