# SELECT 

You can construct [`SELECT`](https://docs.exasol.com/sql/select.htm) SQL statements using the `Select` class.

## Creating `SELECT` Commands

You can create a basic `SELECT` like this:

```java
final Select select = StatementFactory.getInstance().select() //
        .field("fieldA", "tableA.fieldB", "tableB.*");
select.from().table("schemaA.tableA");
select.limit(10);
```

### `SELECT` components

Here you can find a list of supported `SELECT` statement's components:

- derived column (field, function, arithmetic expression, etc)
- `FROM` clause

#### Derived Column

A `SELECT` statement can contain one or more derived columns. Here we describe all supported types of a derived column.

- `field` represents a column in a table. You can create one or more fields using a method `field( ... )` of the `Select` class.

```java
final Select selectWithOneField = StatementFactory.getInstance().select() //
        .field("fieldA");

final Select selectWithMultipleFileds = StatementFactory.getInstance().select() //
        .field("fieldA", "tableA.fieldB", "tableB.*");
```

- `star / *` is  a wildcard field for all involved fields. Create a star using an `all()` method.

```java
final Select selectWithOneField = StatementFactory.getInstance().select() //
        .all();
```

- `function` is a pre-defined function executing against a database. Create functions using a `function( ... )` method.
To create a function you need a name of a function which is included into the list of supported functions of the SSB.
You can also add a name for a derived field that contains a function. 
A function takes any number of [`ValueExpression`](../../../src/main/java/com/exasol/sql/expression/ValueExpression.java) 
and renders them in the order they were added. 

A function doesn't check if a user adds correct and allowed by database arguments.

```java
final Select select = StatementFactory.getInstance().select() //
    .function(ExasolScalarFunction.RANDOM, "RANDOM_1") //
    .function(ExasolScalarFunction.RANDOM, "RANDOM_2", ExpressionTerm.integerLiteral(5), ExpressionTerm.integerLiteral(20));
```

- `arithmetic expression` is a binary arithmetic expression with one of the following arithmetic operator: +, -, *, /.
Add an arithmetic expression using an `arithmeticExpression( ... )` method.
You can also add a name for a derived field that contains an arithmetic expression. 

```java
final Select select = StatementFactory.getInstance().select() //
    .arithmeticExpression(ExpressionTerm.plus(ExpressionTerm.integerLiteral(1000), ExpressionTerm.integerLiteral(234)), "ADD");
```

#### `FROM` clause

A `SELECT` statement can contain one `FROM` clause.
To start a `FROM` clause, use a method `from()` of the `Select` class. `
FROM` clause appends one or more of following structures:

- `table` is a name of the table. You can append it with `table( ... )` method.

- `table with AS clause`is a name of table with an alias. You can append it with `tableAs( ... )` method.

- `value table` is a mocked table which you define inside the `SELECT` statement. To use this structure you need to create a `ValueTable` first.
Then you can use `valueTable( ... )` method of the `Select`.

```java
final Select selectFromTable = StatementFactory.getInstance().select().all();
select.from().table("table1");

final Select selectFromTableAs = StatementFactory.getInstance().select().all();
select.from().tableAs("table", "t");

final ValueTable values = new ValueTable(this.select);
values.appendRow("r1c1", "r1c2").appendRow("r2c1", "r2c2");
final Select selectFromValueTable = StatementFactory.getInstance().select().all();
select.from().valueTable(values);
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
final Select selectFromTable = StatementFactory.getInstance().select().all();
select.from().table("left_table").innerJoin("right_table", "left_table.foo_id = right_table.foo_id");  
```
#### `WHERE` clause

A `SELECT` statement can contain one `WHERE` clause with a boolean expression.
To add a `WHERE` clause, use a method `where( ... )` of the `Select` class. 

```java
Select select = StatementFactory.getInstance().select();
select.all().from().table("person");
select.where(eq(ExpressionTerm.stringLiteral("foo"), ExpressionTerm.stringLiteral("bar")));
```

#### `LIMIT` clause

A `SELECT` statement can contain one `LIMIT` clause with offset and count.
To add a `LIMIT` clause, use a method `limit( ... )` of the `Select` class. 

```java
Select select = StatementFactory.getInstance().select();
select.all().from().table("t");
select.limit(1);
```

#### `GROUP BY` clause

A `SELECT` statement can contain one `GROUP BY` clause.
To start a `GROUP BY` clause, use a method `groupBy()` of the `Select` class. 

```java
Select select = StatementFactory.getInstance().select();
select.all().from().table("t");
select.groupBy(column("t", "city"), column("t", "order"), column("t", "price"))
                        .having(lt(column("t", "price"), integerLiteral(10)));
```
