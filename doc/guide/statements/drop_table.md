# DROP TABLE

The `DropTable` class of the SQL Statement Builder provides an entry 
point to defining a DROP TABLE SQL statement.

## Usage

1. Create an instance of the `DropTable` class through the `StatementFactory`

  ```java
  DropTable dropTable = StatementFactory.getInstance().dropTable("tableName");
  ```

2. Add available options if necessary. 
The next options are currently supported:
- `IF EXISTS`
- `CASCADE CONSTRAINTS`

Example:

  ```java
  dropTable.ifExists().cascadeConstraints();
  ```

3. Render the instance of `DropTable` class. Click [here](../rendering.md) for more information on Rendering SQL Statement.


- The complete example code

  ```java
  DropTable dropTable = StatementFactory.getInstance().dropTable("tableName");

  //optional step: add additional clauses
  dropTable.ifExists().cascadeConstraints();
   
  //Rendering
  //optional step: add config
  StringRendererConfig config = StringRendererConfig.builder().lowerCase(true).build();
  
  DropTableRenderer renderer = DropTableRenderer.create(config);
  dropTable.accept(renderer);
  String renderedString = renderer.render();
  ```