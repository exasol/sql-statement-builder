# Software Architectural Design -- Exasol SQL Statement Builder

## Building Block View

### Select Statement
`dsn~dql-statement~1`

The Data Query Language (DQL) building block is responsible for managing `SELECT` statements.

## Runtime View

### Building Select Statements

#### Accessing the Clauses That Make Up a SELECT Statement
`dsn~select-statement.accessing-clauses~1`

The DQL statement component allows getting the following clauses, provided that they already exist:

* `FROM` clause
* `WHERE` clause

Covers:

* `req~statement-structure.step-wise~1`

Needs: impl, utest

Tags: Select Statement Builder

### Building Boolean Expressions

#### Constructing Boolean Comparison Operations From Operator Strings
`dsn~boolean-operation.comparison.constructing-from-strings~1`

The Boolean Expression builder allows creating expression objects from a string representing the comparison operator (options listed below) and a list of operands.

* `>`
* `<`
* `=`
* `>=`
* `<=`
* `<>`

Covers:

* `req~boolean-operators~1`

Needs: impl, utest

#### Constructing Boolean Comparison Operations From Operator Enumeration
`dsn~boolean-operation.comparison.constructing-from-enum~1`

The Boolean Expression builder allows creating expression objects from a enumeration of comparison operators.
Covers:

* `req~boolean-operators~1`

Needs: impl, utest

#### Forwarded Requirements

* `dsn --> impl, utest : req~comparison-operations~1`
* `dsn --> impl, utest : req~boolean-operators~1`

### Building INSERT Statements

#### Forwarded Requirements

* `dsn --> impl, utest: req~insert-statements~1`
* `dsn --> impl, utest: req~values-as-insert-source~1`

### Rendering Statements

#### Forwarded Requirements

* `dsn --> req~rendering.sql.configurable-case~1`
* `dsn --> impl, utest: req~rendering.sql.select~1`
* `dsn --> impl, utest: req~rendering.sql.insert~1`
