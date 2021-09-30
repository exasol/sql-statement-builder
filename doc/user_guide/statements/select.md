# SELECT 

You can construct [`SELECT`](https://docs.exasol.com/sql/select.htm) SQL statements using the `Select` class.

## Creating `SELECT` Statements

You can create a basic `SELECT` like this:

```java
Select select = StatementFactory.getInstance().select()
    .field("fieldA", "tableA.fieldB", "tableB.*");
select.from().table("schemaA.tableA");
select.limit(10);
```

### `SELECT` Components

Here you can find a list of supported `SELECT` statement's components:

- [Derived column](#derived-column) (field, function, arithmetic expression, etc)
- [`FROM` clause](#from-clause)
- [`WHERE` clause](#where-clause)
- [`LIMIT` clause](#limit-clause)
- [`GROUP BY` clause](#group-by-clause)
- [`ORDER BY` clause](#order-by-clause)

#### Derived Column

A `SELECT` statement can contain one or more derived columns. Here we describe all supported types of derived columns.

- The `field` represents a column in a table. You can create one or more fields using a method `field( ... )` of the `Select` class.

    ```java
    Select selectWithOneField = factory.select().field("fieldA");

    Select selectWithMultipleFileds = factory.select().field("fieldA", "tableA.fieldB", "tableB.*");
    ```

- The `asterisk / *` is  a wildcard representing all fields. Create an asterisk using the `all()` method.

    ```java
    Select selectWithAllFields = factory.select().all();
    ```

- The factory method `function(...)` adds a pre-defined function to a statement that evaluates to a value expression.
You can only create functions that the ESB supports. Check [the list of supported functions](../list_of_supported_exasol_functions.md).

    You can also set a name for a derived field that contains a function. 
A function takes any number of [`ValueExpression`](../../../src/main/java/com/exasol/sql/expression/ValueExpression.java)s and renders them in the order they were added. 

    The `function(...)` factory method does not validate the function arguments.

    ```java
    Select select = factory.select()
        .function(ExasolScalarFunction.RANDOM, "RANDOM_1")
        .function(ExasolScalarFunction.RANDOM, "RANDOM_2", ExpressionTerm.integerLiteral(5), ExpressionTerm.integerLiteral(20));
    ```

- The factory method `udf(...)` adds a user defined function to a statement. 
A udf takes a name of function and any number of [`ValueExpression`](../../../src/main/java/com/exasol/sql/expression/ValueExpression.java)s. You can also create a udf with `EMITS` part containing column definitions.
  
    ```java
    Select selectWithoutEmits = StatementFactory.getInstance().select().udf("my_average", column("x"));
    selectWithoutEmits.from().table("t");

    ColumnsDefinition columnsDefinition = ColumnsDefinition.builder().decimalColumn("id", 18, 0)
        .varcharColumn("user_name", 100).decimalColumn("PAGE_VISITS", 18, 0).build();
    Select selectWithEmits = StatementFactory.getInstance().select().udf("sample_simple", columnsDefinition,
            column("id"), column("user_name"), column("page_visits"), integerLiteral(20));
    selectWithEmits.from().table("people");
    ```

- To add special functions (e.g. analytic functions) to a statement you can use the `function()` method that takes a `Function` as argument. See the [section about creating functions](#creating-functions) for details.

- An `arithmetic expression` is a binary value expression using one of the following arithmetic operators: `+`, `-`, `*`, `/`.
Add an arithmetic expression using an `arithmeticExpression( ... )` method. You can also set a name for a derived field that contains an arithmetic expression. 

    ```java
    Select select = factory.select()
        .arithmeticExpression(ExpressionTerm.plus(ExpressionTerm.integerLiteral(1000), ExpressionTerm.integerLiteral(234)),
                              "ADD");
    ```

#### `FROM` clause

A `SELECT` statement can contain a single `FROM` clause. To start a `FROM` clause, use a method `from()` of the `Select` class.

You can append references to database tables with the `table( ... )` method.

If you want to refer to such a table by an alias,  append it with `tableAs( ... )` method.

You can also add value tables, containing a user-constructed set or rows and columns. Unlike a real table, the contents are pre-defined in the query. To use this structure, create a `ValueTable` object first. Then reference that object using the  `valueTable( ... )` method of the `Select`.

```java
Select selectFromTable = factory.select().all();
selectFromTable.from().table("table1");

Select selectFromTableAs = factory.select().all();
selectFromTableAs.from().tableAs("table", "t");

ValueTable values = new ValueTable(this.select);
values.appendRow("r1c1", "r1c2").appendRow("r2c1", "r2c2");
Select selectFromValueTable = factory.select().all();
selectFromValueTable.from().valueTable(values);
```

The `FROM` clause also supports different types of `JOIN`:

- join
- inner join
- left join
- right join
- full join
- left outer join
- right outer join
- full outer join

To add a `JOIN` clause you need to add a left table and then use one of the join methods. For example, `innerJoin( ... )`; 

```java
Select selectFromTable = factory.select().all();
selectFromTable.from().table("left_table")
        .innerJoin("right_table", "left_table.foo_id = right_table.foo_id");  
```
#### `WHERE` clause

A `SELECT` statement can contain one `WHERE` clause with a boolean expression as filter criteria. To add a `WHERE` clause, use a method `where( ... )` of the `Select` class. 

```java
Select select = factory.select.all();
select.from().table("person");
select.where(eq(ExpressionTerm.stringLiteral("foo"), ExpressionTerm.stringLiteral("bar")));
```

#### `LIMIT` clause

A `SELECT` statement can contain one `LIMIT` clause with count and an optional offset. To add a `LIMIT` clause, use a method `limit( ... )` of the `Select` class. 

```java
Select select = factory.select().all();
select.from().table("t");
select.limit(1);
```

#### `GROUP BY` clause

A `SELECT` statement can contain one `GROUP BY` clause. To start a `GROUP BY` clause, use the `groupBy()` method of the `Select` class. 

The `GROUP BY` clause supports a `HAVING` clause. To add it use a `having( ... )` method.

```java
Select select = factory.select().all();
select.from().table("t");
select.groupBy(column("t", "city"), column("t", "order"), column("t", "price"))
                   .having(lt(column("t", "price"), integerLiteral(10)));
```

#### `ORDER BY` clause

A `SELECT` statement can contain one `ORDER BY` clause.

To start a `ORDER BY` clause, use the `orderBy()` method of the `Select` class. You can also use `nullsFirst()` / `nullsLast()` and `asc()` / `desc()` methods within this clause.

```java
Select select = factory.select().all();
select.from().table("t");
select.orderBy(column("t", "city"), column("t", "price"))
        .nullsFirst().asc();
```

## Creating Functions

When you need to use special functions like analytic functions, you can add them to a statement like this:

```java
Function function = ... // create function
Select select = factory.select().function(function, "<column>");
```

### Analytic functions

Exasols [analytic functions](https://docs.exasol.com/sql_references/functions/analyticfunctions.htm) support a special syntax. You can specify the keywords `DISTINCT` and `ALL` as well as an `OVER` clause.

To create a new `AnalyticFunction` use the following code to use it in a `SELECT` statement:

```java
AnalyticFunction function = AnalyticFunction.of(ExasolAnalyticAggregateFunctions.ANY,
            BooleanTerm.lt(column("age"), integerLiteral(30)));
// configure the function
Select select = factory.select().function(function, "<column>");
```

#### Keywords `DISTINCT` and `ALL`

You create an analytic function with a keyword `DISTINCT` or `ALL` like this:

```java
AnalyticFunction function = ...
function.keywordDistinct();
// or
function.keywordAll();
```

Example:

```java
AnalyticFunction function = AnalyticFunction.of(ExasolAnalyticAggregateFunctions.ANY,
                        BooleanTerm.lt(column("age"), integerLiteral(30)))
        .keywordDistinct();
// -> ANY(DISTINCT(age < 30))
```

#### Adding an `OVER` clause

You can create and configure the `OverClause` directly or use a configurator lambda:

```java
OverClause over = new OverClause().windowName("window1");
// configure over clause
function.over(over);

// or use the configurator lambda:
function.over(over -> over.windowName("window1"));
```

The `OverClause` offers four methods for configuration:

* `windowName()`
* `orderBy()`
* `partitionBy()`
* `windowFrame()`

##### `windowName()`

Add a named window like this:

```java
over.windowName("window1");
// -> OVER(window1)
```

##### `orderBy()`

You can add an `ORDER BY` clause like this:

```java
over.orderBy(new OrderByClause(select, column("city"), column("price")).asc().nullsFirst());
// -> OVER(ORDER BY city, price ASC NULLS FIRST)
```

##### `partitionBy()`

You can partition by one or more columns:

```java
over.partitionBy(column("city"), column("price"));
// -> OVER(PARTITION BY city, price)
```

##### `windowFrame()`

To add a window frame clause use a configurator lambda:

```java
over.windowFrame(frame -> frame.type(WindowFrameType.ROWS) /* ... */);
```

Window frames consist of three parts:

1. The mandatory window frame unit type (`ROWS`, `RANGE` or `GROUPS`)

    You specify the type like this:

    ```java
    over.windowFrame(frame -> frame.type(WindowFrameType.ROWS) /* ... */);
    // -> OVER(ROWS ...)
    ```

2. The mandatory unit specification. This can bei either a single condition or a `BETWEEN ... AND` range:

    * Specify a single condition like this:

        ```java
        over.windowFrame(frame -> frame.type(WindowFrameType.ROWS)
                .unit(UnitType.CURRENT_ROW));
        // -> ROWS CURRENT ROW

        over.windowFrame(frame -> frame.type(WindowFrameType.ROWS)
                .unit(integerLiteral(1), UnitType.PRECEEDING)));
        // -> ROWS 1 PRECEEDING
        ```
    * Specify a range like this:

        ```java
        over.windowFrame(frame -> frame.type(WindowFrameType.ROWS)
                .unitBetween(UnitType.UNBOUNDED_PRECEEDING, UnitType.UNBOUNDED_FOLLOWING));
        // -> ROWS BETWEEN UNBOUNDED PRECEEDING AND UNBOUNDED FOLLOWING

        over.windowFrame(frame -> frame.type(WindowFrameType.ROWS)
                .unitBetween(column("col1"), UnitType.PRECEEDING, column("col2"), UnitType.FOLLOWING));
        // -> ROWS BETWEEN col1 PRECEEDING AND col2 FOLLOWING
        ```

3. An optional exclusion:

    ```java
    over.windowFrame(frame -> frame.type(WindowFrameType.ROWS)
            .unit(UnitType.CURRENT_ROW)
            .exclude(WindowFrameExclusionType.NO_OTHERS));
    // -> ROWS CURRENT ROW EXCLUDE NO OTHERS
    ```
