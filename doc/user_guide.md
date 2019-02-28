# Exasol Sql Statement Builder User Guide

This guide is targeted at users wanting to learn how to use the Sql Statement Builder. 
It contains code examples and explanation of currently supported behavior.

### About Sql Statement Builder

The Exasol SQL Statement Builder abstracts programmatic creation of SQL statements and is intended to replace ubiquitous string concatenation solutions which make the code hard to read and are prone to error and security risks.
It allows users to create an SQL statement using fluent programming. 
Then it renders the statement and returns string.

**List of currently supported SQL statements:**
1. [CREATE TABLE](#create-table)
2. SELECT
3. INSERT INTO

## CREATE TABLE

The `CreateTable` class of the Sql Statement Builder implements SQL CREATE TABLE functionality.


**How to use it:**

1. Create an instance of the `CreateTable` class through the `StatementFactory`. 
```java
CreateTable createTable = StatementFactory.getInstance().createTable("table name");
```

2. Create columns with desired data types using fluent programming.
The columns' order in a rendered string will be exactly the same as a sequence of the called methods. 

An example of creating a table with three columns (decimal, char and boolean):

```java
createTable.decimalColumn("column name 1", 9, 0)
           .charColumn("column name 2", 10)
           .booleanColumn("column name 3");
```
Each time you create a new column with any data type the name of the column is required. 
Some of the data types also require additional parameters.  

**Currently the next data types are supporting:**

- BOOLEAN 
```java
createTable.booleanColumn("column name");
```
- CHAR*
```java
createTable.charColumn("column name", 10);
```
- DATE 
```java
createTable.dateColumn("column name");
```
- DECIMAL*
```java
createTable.decimalColumn("column name", 18, 0);
```
- DOUBLE PRECISION
```java
createTable.doublePrecisionColumn("column name");
```
- INTERVAL DAY TO SECOND*
```java
createTable.intervalDayToSecondColumn("column name", 2, 3);
```
- INTERVAL YEAR TO MONTH*
```java
createTable.intervalYearToMonthColumn("column name", 2);
```
- TIMESTAMP
```java
createTable.timestampColumn("column name");
```
- TIMESTAMP WITH LOCAL TIME ZONE
```java
createTable.timestampWithLocalTimeZoneColumn("column name");
```
- VARCHAR* 
```java
createTable.varcharColumn("column name", 100);
```

Data types marked with * require additional parameters while creating a column.
Read about these parameters in the Javadoc files. 
 
 3. Render the instance of `CreateTable` class. 
 
Create an instance of the `CreateTableRenderer` using the `create()` method of the class.
It can be created with the default configuration or with a custom configuration.
An example of creating a custom configuration:
 ```java
 StringRendererConfig config = StringRendererConfig.builder().lowerCase(true).build();
```
If you add a custom configuration, pass an instance to the `create()` method. If not, run the method without parameters:
```java
CreateTableRenderer renderer = CreateTableRenderer.create();
```

Then call accept method of the `CreateTable` instance and pass the renderer as an argument.
```java
createTable.accept(renderer);
```
Render the SQl statement.
```java
String renderedString = renderer.render();
```

**The whole example:**
```java
CreateTable createTable = StatementFactory.getInstance().createTable("name for your table here);
createTable.decimalColumn("column name 1", 9, 0)
           .charColumn("column name 2", 10)
           .booleanColumn("column name 3");

//optional step: add config
StringRendererConfig config = StringRendererConfig.builder().lowerCase(true).build();
CreateTableRenderer renderer = CreateTableRenderer.create(config);
createTable.accept(renderer);
String renderedString = renderer.render();
```