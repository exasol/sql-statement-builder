# MERGE

You can construct [`MERGE`](https://docs.exasol.com/sql/merge.htm) SQL statements using the `Merge` class.

`Merge` supports a combination of `INSERT`, `UPDATE` and `DELETE` where source data is merged into a destination table. The merge strategy is configurable and depends on whether or not a row in source and destination are considered a match.
Of course the criteria for that match is configurable too.

## Creating `MERGE` Commands

You can create a minimalistic `MERGE` like this:

```java
final Merge merge = StatementFactory.getInstance()
    .merge("destination")
    .using("source")
    .on(eq(column("source", "id"), column("destination", "id");
```

### Fine Tuning the `MERGE` Strategies

If you need more control over what happens in case of matching rows, you can add `whenMatched()`.

In case of `whenMatched()` select one of the two strategies `thenUpdate()` or `thenDelete()`.

Here is an example for `thenUpdate()`.

```java
merge.whenMatched()
    .thenUpdate() //
    .setToDefault("c2") //
    .set("c3", "foo") //
    .set("c4", 42);
```

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

### Rendering `MERGE` Statements

Use the `MergeRenderer` to render `Merge` objects into SQL strings.

For a more general introduction please refer to ["Rendering SQL Statements into Strings"](../rendering.md).