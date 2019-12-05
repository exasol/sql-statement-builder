# MERGE

You can construct [`MERGE`](https://docs.exasol.com/sql/merge.htm) SQL statements using the `Merge` class.

`Merge` supports a combination of `INSERT`, `UPDATE` and `DELETE` where source data is merged into a destination table. The merge strategy is configurable and depends on whether or not a row in source and destination are considered a match.
Of course the criteria for that match is configurable too.

Note that while the individual merge strategies are optional parts of the `MERGE` statement, you need to pick *at least one* to get a valid statement.

## Creating `MERGE` Commands

You can create a basic `MERGE` like this:

```java
final Merge merge = StatementFactory.getInstance()
    .merge("destination")
    .using("source")
    .on(eq(column("source", "id"), column("destination", "id");
```

As mentioned before, this statement is not complete without selecting at least one [merge strategy](#merge-strategies).

### `MERGE` Strategies

If you need more control over what happens in case of matching rows, you can add `whenMatched()`.

In case of `whenMatched()` select one of the two strategies `thenUpdate()` or `thenDelete()`.

Here is an example for `thenUpdate()`.

```java
merge.whenMatched()
    .thenUpdate() //
    .setToDefault("c2") //
    .set("c3", "foo") //
    .set("c4", 42) //
    .set("c5", integerLiteral(9000));
```

The basic `set()` method expects a column and a value expression. For added convenience the set methods are overloaded to allow using literals directly.

And another example for `thenDelete()`.

```java
merge.thenDelete();
```

Consequently you can use `whenNotMatched()` to control the strategy when row do not match. In this case only `thenInsert()` is supported.

```java
merge.whenNotMatched()
    .thenInsert(1, "Hello");
```

Check the JavaDoc of class `Merge` for a more information.

### Using a Filter on a `MERGE` Strategy

All three merge strategies can be narrowed down with a `WHERE` clause, to limit the dataset they work on.

Here is an example for a `MERGE` statement with a `DELETE` strategy.

```java
merge.using("src") //
    .on(eq(column("src", "c1"), column("dst", "c1"))) //
    .whenMatched() //
    .thenDelete() //
    .where(gt(column("src", "c5"), integerLiteral(1000)));
```

### Using Value Tables in `MERGE` Insert Clauses

in the insert clause of a merge statement you can use `VALUES` to insert [value tables](common_constructs/value_tables.md).

### Rendering `MERGE` Statements

Use the `MergeRenderer` to render `Merge` objects into SQL strings.

For a more general introduction please refer to ["Rendering SQL Statements into Strings"](../rendering.md).