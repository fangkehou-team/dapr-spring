# Version

[<- Return to Index](index.md)

This page provides information about the versioning strategy and lifecycle.

## Current Version

> __Note: The current project is still in the Alpha stage. The API has not been completely finalized, and various issues may arise. Please upgrade to the latest version as much as possible when using it.__

The latest version hosted on JitPack and Maven Central is as follows:

[![](https://jitpack.io/v/icu.fangkehou/dapr-spring.svg)](https://jitpack.io/#icu.fangkehou/dapr-spring)
[![Maven Central with version prefix filter](https://img.shields.io/maven-central/v/icu.fangkehou/dapr-spring-boot-starter.svg)](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22icu.fangkehou%22%20dapr)

For the latest version hosted on Github Packages, please check [here](https://github.com/fangkehou-team/dapr-spring/packages/).

## Dependency Versions

This project is built based on Spring Boot 3.2.5 and Spring Cloud 2023.0.1.

Since the Spring Boot API used in this project is mostly stable or only maintained, in theory, the artifacts of this project are applicable to all versions of Spring Boot 3.x. Therefore, this project does not implement or depend on the API of components in Spring Boot or Spring Cloud. Dependencies are only introduced at the compile stage, so you may need to include the following dependencies:

- org.springframework.boot:spring-boot-starter
- org.springframework.cloud:spring-cloud-starter-openfeign
- org.springframework.cloud:spring-cloud-commons
- org.springframework.cloud:spring-cloud-context

Under normal circumstances, a standard Spring Boot and Spring Cloud project only needs to include `org.springframework.cloud:spring-cloud-starter-openfeign`. However, it is possible that other components may need to be included. Please be aware of this when creating new projects.

---

[<- Return to Index](index.md)
