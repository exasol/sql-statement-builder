# Software Architectural Design -- Exasol SQL Statement Builder

## Building Block View

### Select Statement
`dsn~dql-statement~1`

The Data Query Language (DQL) building block is responsible for managing `SELECT` statements.

## Solution Strategy

### Fluent Programming

###### Statement Construction With Fluent Programming
`dsn~statement-construction-with-fluent-programming~1`

All statement builders use the "fluent programming" model, where the return type of each builder step determines the possible next structural elements that can be added.

Comment:

This is a design principle that cuts across the whole project. Therefore locating it in a single test or implementation part makes no sense.

Covers:

* `req~statement-structure-limited-at-compile-time~1`

## Runtime View

### Building Select Statements

#### Accessing the Clauses That Make Up a SELECT Statement
`dsn~select-statement.out-of-order-clauses~1`

`SELECT` commands allow attaching the following clauses in any order:

* `FROM` clause
* `WHERE` clause
* `LIMIT` clause

Covers:

* `req~statement-structure.step-wise~1`

Needs: impl, utest

Tags: Select Statement Builder

### Building Boolean Expressions

#### Forwarded Requirements

* `dsn --> impl, utest: req~boolean-operators~1`
* `dsn --> impl, utest: req~comparison-operations~1`

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

### Building INSERT Statements

#### Forwarded Requirements

* `dsn --> impl, utest: req~insert-statements~1`
* `dsn --> impl, utest: req~values-as-insert-source~1`

### Rendering Statements

#### Forwarded Requirements

* `dsn --> impl, utest: req~rendering.sql.configurable-case~1`
* `dsn --> impl, utest: req~rendering.sql.select~1`
* `dsn --> impl, utest: req~rendering.sql.insert~1`
