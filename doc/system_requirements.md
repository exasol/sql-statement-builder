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

#### Augmented Backus–Naur Form (ABNF)

This document uses Augmented Backus–Naur Form (ABNF) for syntax definitions.

#### ABNF Terminals

This subsection list the ABNF terminals used in this document. Terminals are ABNF rules that cannot be split further down, like string literals for example.

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

## Functional Requirements

### Statement Structure

##### Building the Statement Structure Step-wise
`req~statement-structure.step-wise~1`

ESB lets users build the statement structure step-by-step.

Rationale:

This is necessary since complex statements are usually build as a result of multi-layered decision trees and parts of the statements are constructed in different places.

Covers:

* [feat~statment-definition~1](#statement-definition)

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

* Upper case / lower case
* One line / pretty

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


### TODO

---

SELECT
* Fields
* Asterisk ("*")

FROM

( INNER / ( LEFT / RIGHT / FULL ) OUTER ) JOIN
* ON

LIMIT
* offset
* count
