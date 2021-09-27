# CREATE SCHEMA

The `CreateSchema` class of the SQL Statement Builder provides an entry 
point to defining a [`CREATE SCHEMA`](https://docs.exasol.com/sql/create_schema.htm) SQL statement.

## Usage

1. Create an instance of the `CreateSchema` class through the `StatementFactory`:

  ```java
  CreateSchema createSchema = StatementFactory.getInstance().createSchema("schemaName");
  ```

2. Render the instance of `CreateSchema` class. Click [here](../rendering.md) for more information on Rendering SQL Statement.

- The complete example code

  ```java
  CreateSchema createSchema = StatementFactory.getInstance().createSchema("schemaName");

  // optional step: add configuration
  StringRendererConfig config = StringRendererConfig.builder().lowerCase(true).build();
  CreateSchemaRenderer renderer = CreateSchemaRenderer.create(config);
  createSchema.accept(renderer);

  String renderedString = renderer.render();
  ```