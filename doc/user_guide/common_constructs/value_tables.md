# Value Tables

Value tables are lists of content definitions for rows. They can appear in [`INSERT`](../statements/insert.md) and [`MERGE`](../statements/merge.md) statements.

## Defining Value Tables

The class `ValueTable` contains multiple methods for defining value tables:
* The `appendRow()` methods allow adding all values for a complete row at once. They are especially useful in case all columns have the same type.
* The `add()` methods on the other hand amend the last row with an additional value.