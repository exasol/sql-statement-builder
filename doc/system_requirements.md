# System Requirement Specification -- Exasol SQL Statement Builder

## Introduction

The Exasol SQL Statement Builder (ESB) is a [Java](https://java.com) library that allows you to define SQL statements in a [internal Domain-specific language (DSL)](https://en.wikipedia.org/wiki/Domain-specific_language#Usage_patterns). This means it uses standard Java language features to create a DSL.

The project uses agile software development process, so this document contains only the portion of requirement necessary for the current iteration.

### Goals

The goals of the ESB are:

* Abstraction from the SQL text representation
* Compile-time error detection (where possible)
* Extensibility (for different SQL dialects, validators and renderers)
* User friendly API

### Terms and Abbreviations

* ESB: Exasol SQL Statement Builder
* Renderer: extension that turns the abstract representation into a different one (most notably an SQL string)
* Validator: extension that validates a statements structure and content

### Notation

#### Augmented Backus-Naur Form (ABNF)

This document uses Augmented Backus–Naur Form (ABNF) for syntax definitions.

#### ABNF Terminals

This subsection list the ABNF terminals used in this document. Terminals are ABNF rules that cannot be split further down, like string literals for example.

##### General Terminals

    COMMA = ","
    
    L-BRACKET = "("
    
    R-BRACKET = ")"

##### Operator Terminals

    EQUAL-OPERATOR = "="
    
    NOT-EQUAL-OPERATOR = "<>"
    
    LESS-THAN-OPERATOR = "<"
    
    LESS-THAN-OR-EQUAL-OPERATOR = "<="
    
    GREATER-THAN-OPERATOR = ">"
    
    GREATER-THAN-OR-EQUAL-OPERATOR = ">="

## Features

### Statement Definition
`feat~statement-definition~1`

The ESB allows users to define SQL statements in abstract form.

Needs: req

### SQL String Rendering
`feat~sql-string-rendering~1`

The ESB renders abstract SQL statements into strings.

Rationale:

The SQL strings are necessary input for executing queries (e.g. with JDBC).

Needs: req

### Compile-time Error Checking
`feat~compile-time-error-checking~1`

ESB reports detectable errors at compile-time.

Rationale:

Making sure at compile time that illegal constructs do not compile make the resulting code safer and reduces debugging efforts.

Needs: req

### Data Conversion
`feat~data-conversion~1`

ESB converts between values of compatible data types.

Rationale:

Different databases and related tools use different ways to store and process similar data types. A collection of well-tested converters saves the API users time and trouble.

Needs: req

## Functional Requirements

### Statement Structure

#### Building the Statement Structure Step-wise
`req~statement-structure.step-wise~1`

ESB lets users build the statement structure step-by-step.

Rationale:

This is necessary since complex statements are usually build as a result of multi-layered decision trees and parts of the statements are constructed in different places.

Covers:

* [feat~statement-definition~1](#statement-definition)

Needs: dsn

#### Statement Structure Limited at Compile-time
`req~statement-structure-limited-at-compile-time~1`

ESB lets users create only valid statement structures at compile-time.

Rationale:

If users can't get illegal structures to compile, they don't need to spend time debugging them later.

Covers:

* [feat~compile-time-error-checking~1](#compile-time-error-checking)

Needs: dsn

### General SQL Construction

#### Comparison Operations
`req~comparison-operations~1`

ESB supports the following comparison operations:

    operation = left-operand operator right-operand
    
    left-operand = field-reference / literal
    
    operator = equal-operator / not-equal-operator / greater-operator / less-than-operator /
        greater-or-equal-operator / less-than-or-equal-operator
    
    right-operand = field-reference / literal

Covers:

* [feat~statement-definition~1](#statement-definition)

Needs: dsn

#### Boolean Operators
`req~boolean-operators~1`

ESB supports the following boolean operators: `AND`, `OR` and `NOT`

Covers:

* [feat~statement-definition~1](#statement-definition)

Needs: dsn

#### Boolean Literals
`req~boolean-literals~1`

ESB can convert the following string literals into boolean values, independently of the case they are written in:

* true: `true`, `t`, `yes`, `y`, `on`, `enabled`, `1`
* false: `false`, `f`, `no`, `n`, `off`, `disabled`, `0`

Covers:

* [feat~statement-definition~1](#statement-definition)

Needs: dsn

### Data Query Language (DQL)

#### Value Table
`req~value-table~1`

ESB supports the following way to construct tables from a value table:

    value-table = VALUES value-row *( COMMA value-row )
    
    value-row = L-BRACKET expression *( COMMA expression ) R-BRACKET

Covers:

* [feat~statement-definition~1](#statement-definition)

Needs: dsn

### Data Definition Language (DDL)

#### `CREATE` Statements
`req~create-statements~1`

ESB supports the following `CREATE` statements.

Create schema:
    
    create-statement = "CREATE SCHEMA" schema-reference 
    
    schema-reference = schema

Create table: 

    create-statement = "CREATE TABLE" table-reference table-element-list
    
    table-reference = table
    
    table-element-list = L-BRACKET table-element [*( COMMA table-element )] R-BRACKET

Covers:

* [feat~statement-definition~1](#statement-definition)

Needs: dsn

#### `DROP` Statements
`req~drop-statements~1`

ESB supports the following `DROP` statement.

Drop schema:

    drop-statement = "DROP SCHEMA" [IF EXISTS] schema-reference [CASCADE / RESTRICT]
    
    schema-reference = schema

Drop table:

    drop-statement = "DROP TABLE" [IF EXISTS] table-reference [CASCADE CONSTRAINTS]
    
    table-reference = table
    
Covers:

* [feat~statement-definition~1](#statement-definition)

Needs: dsn

### Data Manipulation Language (DML)

#### `INSERT` Statements
`req~insert-statements~1`

ESB supports the following `INSERT` statement:

    insert-statement = "INSERT INTO" table-reference [insert-columns]
        insert-source
    
    table-reference = table [AS table-alias]
    
    insert-columns = L-BRACKET column *( COMMA column ) R-BRACKET

Covers:

* [feat~statement-definition~1](#statement-definition)

Needs: dsn

#### Values as `INSERT` Source
`req~values-as-insert-source~1`

ESB supports a list of explicit values as `INSERT` source:

    insert-source =/ "VALUES" L-BRACKET ( value-expression /
        "DEFAULT" ) R-BRACKET

Covers:

* [feat~statement-definition~1](#statement-definition)

Needs: dsn

#### `MERGE` Statements
`req~merge-statements~1`

ESB supports the following `MERGE` statement:

    merge-statement = "MERGE INTO" table-reference
        "USING" table-reference
        "ON" condition
        [ "WHEN MATCHED THEN" ( merge-update-clause / merge-delete-clause ) ]
        [ "WHEN NOT MATCHED THEN" merge-insert-clause ]
    
    table-reference = table [AS table-alias]
    
    merge-update-clause = "UPDATE SET" column "="
        ( expression / "DEFAULT" ) * ( COMMA ( expression / "DEFAULT" ) ) [ where-clause ]
        [ merge-delete-clause ]
        
    merge-delete-clause = "DELETE" where-clause
    
    merge-insert-clause = "INSERT" [ L-BRACKET column *( COMMA column ) R-BRACKET ]
        merge-values

    merge-values = "VALUES" L-BRACKET ( expression / "DEFAULT" )
        *( COMMA ( expression / "DEFAULT" ) )  R_BRACKET
        [ where-clause]

Covers:

* [feat~statement-definition~1](#statement-definition)

Needs: dsn

### SQL String Rendering

#### Configurable Case Rendering
`req~rendering.sql.configurable-case~1`

Users can choose whether the keywords in the SQL string should be rendered in upper case and lower case.

Rationale:

While keyword case is mostly an esthetic point, different users still have different preferences.

Covers:

* [feat~sql-string-rendering~1](#sql-string-rendering)

Needs: dsn

#### Configurable Identifier Quoting
`req~rendering.sql.confiugrable-identifier-quoting~1`

ESB allows users to choose whether the following identifiers should be quoted in the rendered query:

* Schema identifiers
* Table identifiers
* Column identifiers

Rationale:

The Exasol database for example requires identifiers to be enclosed in double quotes in order to enable case sensitivity.

Covers:

* [feat~sql-string-rendering~1](#sql-string-rendering)

Needs: dsn

#### `SELECT` Statement Rendering
`req~rendering.sql.select~1`

ESB renders abstract `SELECT` statements into SQL query strings.

Covers:

* [feat~sql-string-rendering~1](#sql-string-rendering)

Needs: dsn

#### `CREATE` Statement Rendering
`req~rendering.sql.create~1`

ESB renders abstract `CREATE` statements into SQL data definition language strings.

Covers:

* [feat~sql-string-rendering~1](#sql-string-rendering)

Needs: dsn

#### `DROP` Statement Rendering
`req~rendering.sql.drop~1`

ESB renders abstract `DROP` statements into SQL data definition language strings.

Covers:

* [feat~sql-string-rendering~1](#sql-string-rendering)

Needs: dsn

#### `INSERT` Statement Rendering
`req~rendering.sql.insert~1`

ESB renders abstract `INSERT` statements into SQL data manipulation language strings.

Covers:

* [feat~sql-string-rendering~1](#sql-string-rendering)

Needs: dsn

### Exasol Dialect Specific Requirements

#### Integer - Interval Conversion
`req~integer-interval-conversion~1`

ESB converts values of type `INTERVAL` to integer and vice-versa.

Rationale:

Neighboring systems of an Exasol database often do not have equivalent data types, so conversion to a primitive data type is required.

Covers:

* [feat~data-conversion~1](#data-conversion)

Needs: dsn