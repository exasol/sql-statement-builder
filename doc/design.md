# Software Architectural Design -- Exasol SQL Statement Builder

## Building Block View

### Select Statement
`dsn~dql-statement~1`

The Data Query Language (DQL) building block is responsible for managing `SELECT` statements.

## Solution Strategy

### Fluent Programming

#### Statement Construction With Fluent Programming
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
* `GROUP BY` clause
* `ORDER BY` clause

Covers:

* `req~statement-structure.step-wise~1`

Needs: impl, utest

Tags: Select Statement Builder

### Building Boolean Expressions

#### Forwarded Requirements

* `dsn --> impl, utest: req~boolean-operators~1`
* `dsn --> impl, utest: req~boolean-literals~1`
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

### Building `SELECT` Statements

#### Forwarded Requirements

* `dsn --> impl, utest: req~value-table~1`

### Building `CREATE` Statements

#### Forwarded Requirements

* `dsn --> impl, utest: req~create-statements~1`

### Building `DROP` Statements

#### Forwarded Requirements

* `dsn --> impl, utest: req~drop-statements~1`

### Building `INSERT` Statements

#### Forwarded Requirements

* `dsn --> impl, utest: req~insert-statements~1`
* `dsn --> impl, utest: req~values-as-insert-source~1`

### Building `MERGE` Statements 

#### Forwarded Requirements

* `dsn --> impl, utest: req~merge-statements~1`

### Rendering Statements

#### Forwarded Requirements

* `dsn --> impl, utest: req~rendering.sql.configurable-case~1`
* `dsn --> impl, utest: req~rendering.sql.select~1`
* `dsn --> impl, utest: req~rendering.sql.create~1`
* `dsn --> impl, utest: req~rendering.sql.drop~1`
* `dsn --> impl, utest: req~rendering.sql.insert~1`

#### Renderer add Double Quotes for Schema, Table and Column Identifiers
`dsn~rendering.add-double-quotes-for-schema-table-and-column-identifiers~1`

The renderer sets the following identifiers in double quotes if configured:

* Schema identifiers
* Table identifiers
* Column identifiers (except the asterisks)

Comment:

Examples are `"my_schema"."my_table"."my_field"`, `"MY_TABLE"."MyField"` and `"MyTable".*`

Covers:

* `req~rendering.sql.confiugrable-identifier-quoting~1`

Needs: impl, utest

### Exasol Dialect Specific

#### Converting from 64 bit Integers to `INTERVAL DAY TO SECOND`
`dsn~exasol.converting-int-to-interval-day-to-second~2`

The data converter converts signed integers to `INTERVAL DAY TO SECOND`.

Covers:

* `req~integer-interval-conversion~1`

Needs: impl, utest

#### Converting from `INTERVAL DAY TO SECOND` to 64 bit Integers 
`dsn~exasol.converting-interval-day-to-second-to-int~1`

The data converter converts `INTERVAL DAY TO SECOND` to signed integers.

Covers:

* `req~integer-interval-conversion~1`

Needs: impl, utest

#### Parsing `INTERVAL DAY TO SECOND` From Strings
`dsn~exasol.parsing-interval-day-to-second-from-strings~2`

The data converter can parse `INTERVAL DAY TO SECOND` from strings in the following format:

    interval-d2s = [ "+" / "-" ] [ days SP ] time-interval
    
    time-interval = hours ":" minutes [ ":" seconds [ "." milliseconds ] ]
    
    hours = ( "2" "0" - "3" ) / ( [ "0" / "1" ] DIGIT )
    
    minutes = ( "5" DIGIT ) / ( [ "0" - "4" ] DIGIT )

    seconds = ( "5" DIGIT ) / ( [ "0" - "4" ] DIGIT )

    milliseconds = 1*3DIGIT

Examples are `12:30`, `12:30.081` or `100 12:30:00.081`.

Covers:

* `req~integer-interval-conversion~1`

Needs: impl, utest

#### Converting from 64 bit Integers to `INTERVAL YEAR TO MONTH`
`dsn~exasol.converting-int-to-interval-year-to-month~2`

The data converter converts signed integers to `INTERVAL YEAR TO MONTH`.

Covers:

* `req~integer-interval-conversion~1`

Needs: impl, utest

#### Converting from `INTERVAL YEAR TO MONTH` to 64 bit Integers
`dsn~exasol.converting-interval-year-to-month-to-int~1`

The data converter converts signed integers to `INTERVAL YEAR TO MONTH`.

Covers:

* `req~integer-interval-conversion~1`

Needs: impl, utest

#### Parsing `INTERVAL YEAR TO MONTH` From Strings
`dsn~exasol.parsing-interval-year-to-month-from-strings~2`

The data converter can parse `INTERVAL YEAR TO MONTH` from strings in the following format:

    interval-y2m = [ "+" / "-" ]  days "-" months
    
    days = 1*9DIGIT
    
    months = ( "1" "0" - "2" ) / DIGIT

Examples are `0-1` and `100-11`.

Covers:

* `req~integer-interval-conversion~1`

Needs: impl, utest