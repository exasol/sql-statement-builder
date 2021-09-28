# Rendering SQL Statements

## Creating the Renderer With Default Settings

To render an SQL statement, you need to create an instance of the SQL Statement Renderer (for example `CreateTableRenderer`) using the `create()` method of the Renderer class:

```java
CreateTableRenderer renderer = CreateTableRenderer.create();
```

## Customizing Rendering 

If you need more control over the rendering process, you can create you own configuration.

When you add a custom configuration, you need to pass an instance to the `create()` method. 

```java
StringRendererConfig config = StringRendererConfig.builder().lowerCase(true).build();
CreateTableRenderer renderer = CreateTableRenderer.create(config);
```

## Rendering Statements

Next, call `accept()` method of the statement class (for example `CreateTable`) instance and pass the renderer as an argument.

```java
createTable.accept(renderer);
```

In the final step, render the SQl statement:

```java
String renderedString = renderer.render();
```