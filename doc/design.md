# Software Architectural Design -- Exasol SQL Statement Builder

## Building Block View

### Select Statement
`dsn~dql-statement~1`

The Data Query Language (DQL) building block is responsible for managing `SELECT` statements.

## Runtime View

### Building Select Statements

##### Accessing the Clauses That Make Up a SELECT Statement
`dsn~select-statement.accessing-clauses~1`

The DQL statement component allows getting the following clauses, provided that they already exist:

* `FROM` clause
* `WHERE` clause

Covers:

* `req~statement-structure.step-wise~1`

Needs: impl, utest, itest

Tags: Select Statement Builder

## Forwarded Requirements

* dsn --> impl, utest : req~comparison-operations~1