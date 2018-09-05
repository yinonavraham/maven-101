# Multi Module Example

This is an example of a multi-module project.

## Project Structure

| Module Artifact ID    | Description               | Dependencies |
| --------------------- | ------------------------- | ------------ |
| `multi-module-parent` | The parent pom            |  |
| `multi-module-api`    | API of the app's business |  |
| `multi-module-core`   | Implementation of the API | `multi-module-api` (compile) |
| `multi-module-app`    | The application's shell   | `multi-module-api` (compile) <br> `multi-module-core` (runtime) |

## Main Points

1. This project uses both module inheritence and module aggregation patterns. This allows reuse and single point of project management.
1. The parent pom defines the `dependencyManagement` so that child modules do not need to redeclair everything.
1. The scope of the dependency on the `multi-module-core` module is `runtime`. The goal here is for the application to depend only on the API, not its implementation. This way it is simpler to keep the separation and the abstraction.
