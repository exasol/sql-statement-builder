# DROP SCHEMA

The `DropSchema` class of the SQL Statement Builder provides an entry 
point to defining a [`DROP SCHEMA`](https://docs.exasol.com/sql/drop_schema.htm) SQL statement.

## Usage

1. Create an instance of the `DropSchema` class through the `StatementFactory`

   ```java
   DropSchema dropSchema = StatementFactory.getInstance().dropSchema("schemaName");
   ```

2. Add available options using fluent programming if necessary.

    The following options are currently supported:
    
    - `IF EXISTS`
    - `CASCADE`
    - `RESTRICT`
    
    Example:

    ```java
    dropSchema.ifExists().cascade();
    ```
    Please do not use methods `cascade()` and `restrict()` on the same object.
    If both these options are used on the same object, `IllegalArgumentException` will be thrown.

3. Render the instance of `DropSchema` class. Click [here](../rendering.md) for more information on Rendering SQL Statement.

   - The complete example code

    ```java
     DropSchema dropSchema = StatementFactory.getInstance().dropSchema("schemaName");

     // optional step: add additional clauses
     dropSchema.ifExists().cascade();
     // or
     dropSchema.ifExists().restrict();
   
     // Rendering
     // optional step: add configuration
     StringRendererConfig config = StringRendererConfig.builder().lowerCase(true).build();
  
     DropSchemaRenderer renderer = DropSchemaRenderer.create(config);
     dropSchema.accept(renderer);
     String renderedString = renderer.render();
     ```