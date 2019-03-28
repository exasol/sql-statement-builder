# RENDERING SQL STATEMENT
  
  To render an SQL statement, you need to create an instance of the SQl 
  Statement Renderer (for example `CreateTableRenderer`) using the `create()` method of
  the Renderer class. It can be created either with default configuration or with custom
  configuration.

  Below is an example of creating a custom configuration: 

  ```java
  StringRendererConfig config = StringRendererConfig.builder().lowerCase(true).build();
  ```
  When you add a custom configuration, you need to pass an instance to the `create()` method. 
  Else, run the method without parameters:

  ```java
  CreateTableRenderer renderer = CreateTableRenderer.create();
  ```

  Next, call `accept()` method of the statement class (for example `CreateTable`) instance and pass the renderer as
  an argument:

  ```java
  createTable.accept(renderer);
  ```

  In the final step, render the SQl statement:

  ```java
  String renderedString = renderer.render();
  ```