# Example

The example project demonstrates how to use these items.

These items can serve as templates for your own projects. We use them to verify that this library operates in different environments, and we do not change its behavior without notice.

> **Note:**
> If you have any questions about these projects, or would like other examples, feel free to raise an [issue](https://github.com/fangkehou-team/dapr-spring/issues).

In our Github repository, we provide a simple example to show you the basic usage of the Dapr Spring Boot Starter. In this project, we demonstrate how to retrieve configurations using @Value, inject configurations during the database connection phase, and use openfeign in conjunction with invokeMethod to request other microservices.

This example requires starting with Docker Compose, and we offer two ways to build: automatic building with Dockerfile, or local building followed by conversion to a Docker image. Currently, the first method is temporarily invalid because it does not start a Dapr instance; we are looking for a solution and suggest you use the second method.

If you choose to start with the first method, please use `docker-compose.yml` and `docker-compose-override.yml` to start Docker Compose.

If you opt for the second method, you need to start a Dapr instance during the compilation process. Please use `docker-compose-dapr-only.yml` in the root directory of the example to start a Dapr instance before building.

After the artifact is compiled, please use `docker-compose-development.yml` and `docker-compose-override.yml` to start Docker Compose.
