# Exasol SQL Statement Builder User Guide

You can follow this guide to learn more about how to use the SQL Statement Builder. It provides explanations of currently supported features with various code examples.

## Overview

The Exasol SQL Statement Builder abstracts programmatic creation of SQL statements and is intended to replace ubiquitous string concatenation solutions which make the code hard to read and are prone to errors and security risks.

SQL Statement Builder allows users to create an abstract representation of SQL statements using [fluent programming][fluent]. Once you have such an abstract statement, you can, for example, use the various renderers provided by this project to render the statement into an SQL string.

## Currently Supported SQL Statements

Data Definition Language (DDL)

- [CREATE SCHEMA](statements/create_schema.md)
- [DROP SCHEMA](statements/drop_schema.md)
- [CREATE TABLE](statements/create_table.md)
- [DROP TABLE](statements/drop_table.md)

Data Query Language (DQL)

- [SELECT](statements/select.md)

Data Modification Language (DML)

- [INSERT](statements/insert.md)
- [MERGE](statements/merge.md)

[fluent]: https://en.wikipedia.org/wiki/Fluent_interface