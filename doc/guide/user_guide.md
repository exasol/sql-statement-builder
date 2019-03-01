# Exasol SQL Statement Builder User Guide

You can follow this guide to learn more about how to use the SQL Statement
Builder. It provides explanations of currently supported features with various
code examples.

## Overview

The Exasol SQL Statement Builder abstracts programmatic creation of SQL
statements and is intended to replace ubiquitous string concatenation solutions
which make the code hard to read and are prone to errors and security risks.

SQL Statement Builder allows users to create an abstract representation of SQL statements using
[fluent programming][fluent]. Once you have such an abstract statement, 
you can for example use the SSB to render the statement into an SQL string."

**Currently supported SQL statements:**

- [CREATE TABLE](../guide/statements/create_table.md)
- SELECT
- INSERT INTO

[fluent]: https://en.wikipedia.org/wiki/Fluent_interface