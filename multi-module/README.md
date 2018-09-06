# Multi Module Example

This is an example of a multi-module project. It demonstrates the following:

* **Module Inheritance**
* **Module Aggregation**
* **Distribution Management**
* **Dependency Management** (including importing dependency management from other modules)
* **Dependency Scopes**

## Project Structure

| Module Artifact ID    | Description               | Dependencies                 |
| --------------------- | ------------------------- | ---------------------------- |
| `multi-module-parent` | The parent pom            |                              |
| `multi-module-api`    | API of the app's business |                              |
| `multi-module-core`   | Implementation of the API | `multi-module-api` (compile) |
| `multi-module-app`    | The application           | `multi-module-api` (compile) <br> `multi-module-core` (runtime) |

### API

Contains a single `HelloService` interface

### Core

Contains a single `HelloServiceImpl` class which implements `HelloService` 
and is annotated with the spring framework's `@Service` stereotype.

### App

Contains a single `Main` class which creates the Spring application context and scans the application's package.
It is then uses the `HelloService` to generate a "Hello <name>" message and prints it to `stdout`.  
This module has no access in compile time to the `HelloServiceImpl` class 
due to the `runtime` scope used for the dependency on the core module.

## Running the Example

*Note-* All commands below shall be executed in the command line (bash) from the root of the project.

### Build the Project

```
mvn clean install
```

This will build all 4 modules.

### Run the Application

```
$ ./sayHello.sh "my name is Inigo Montoya"
Hello my name is Inigo Montoya
```

### Deploy the Artifacts

```
mvn deploy
```

## Main Points

1. This project uses both module inheritence and module aggregation patterns. This allows reuse and single point of project management.
1. The parent pom defines the `dependencyManagement` so that child modules do not need to redeclair everything.
1. The dependency defined on the `multi-module-core` artifact is scoped as `runtime`. 
   The goal here is for the application to depend in compile time only on the API, not its implementation 
   (i.e. the classes in the `multi-module-core` artifact are not available in compile time). 
   This way it is simpler to keep the separation and the abstraction.
1. The parent pom defines the `distributionManagement` for both releases and snapshots repositories. 
   In the example, the target repositories are actually in the user's home directory in the local file system.
1. Importing the dependency management from a different pom module (in this example, spring framework) 
   ensures using the right versions for relevant dependencies.
1. *Bonus-* Running the application from the command line using the `exec` plugin - see the [`sayHello.sh`](sayHello.sh) bash script.
