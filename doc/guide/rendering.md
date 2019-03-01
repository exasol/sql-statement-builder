# RENDERING SQL STATEMENT
  
  Create an instance of the SQl Statement Renderer (for example `CreateTableRenderer`) using the `create()` method of
  the Renderer class. It can be created with the default configuration or with a custom
  configuration.

  An example of creating a custom configuration:

  ```java
  StringRendererConfig config = StringRendererConfig.builder().lowerCase(true).build();
  ```
  If you add a custom configuration, pass an instance to the `create()` method. If
  not, run the method without parameters:

  ```java
  CreateTableRenderer renderer = CreateTableRenderer.create();
  ```

  Then call accept method of the statement class (for example `CreateTable`) instance and pass the renderer as
  an argument:

  ```java
  createTable.accept(renderer);
  ```

  And finally render the SQl statement:

  ```java
  String renderedString = renderer.render();
  ```