# INSERT 

You can construct [`INSERT`](https://docs.exasol.com/sql/insert.htm) SQL statements using the `Insert` class.

## Creating `INSERT` Statements

Create an `INSERT` statement:

```java
        final Insert insert = StatementFactory.getInstance()
                .insertInto("tableName")
                .values("value1","value2","value3");
```

Create an `INSERT` statement for specific fields:

```java
        final Insert insert = StatementFactory.getInstance()
                .insertInto("tableName")
                .field("column1", "column2","column3")
                .values("value1","value2","value3");
```

### Using Placeholders

In SQL you can use value placeholders (`?`) in prepared statements. This allows you to add the values later in a safe way.

You can add single placeholders in the following way:

```java
		        final Insert insert = StatementFactory.getInstance()
                .insertInto("testTable")
                .field("column1")
                .valuePlaceholder();
```

Here is an example with multiple placeholders in one statement:

```java
        final Insert insert = StatementFactory.getInstance()
                .insertInto("testTable")
                .field("column1", "column2","column3")
                .valuePlaceholders(3);
```

### Using Value Tables

You can also use a value table in an insert:

```java
        final Insert insert = StatementFactory.getInstance().insertInto("tableName");
        final ValueTable table = new ValueTable(insert);
        table.appendRow("a", "b")
                .appendRow("c", "d");
        insert.valueTable(table);
``` 

More info on value tables [value tables](../common_constructs/value_tables.md).

### Rendering `INSERT` Statements

Use the `InsertRenderer` to render `Insert` objects into SQL strings.

```java
        final StringRendererConfig config = StringRendererConfig.builder().quoteIdentifiers(true).build();
        final InsertRenderer renderer = new InsertRenderer(config);
        insert.accept(renderer);
        final String sql = renderer.render();
``` 

For a more general introduction please refer to ["Rendering SQL Statements into Strings"](../rendering.md).