# 版本

[<- 返回索引](index.md)

本页面介绍版本策略和生命周期等信息。

## 当前版本

>__注：当前项目仍处于Alpha阶段，API并没有完全固定，同时有可能遇到各种奇怪的问题，请大家在使用时尽可能地升级到最新版本__

托管于JitPack和Maven Central的最新版本如下

[![](https://jitpack.io/v/icu.fangkehou/dapr-spring.svg)](https://jitpack.io/#icu.fangkehou/dapr-spring)
[![Maven Central with version prefix filter](https://img.shields.io/maven-central/v/icu.fangkehou/dapr-spring-boot-starter.svg)](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22icu.fangkehou%22%20dapr)

托管于Github Packages的最新版本请在[此处](https://github.com/fangkehou-team/dapr-spring/packages/)查看

## 依赖版本

本项目基于 Spring Boot 3.2.5 和 Spring Cloud 2023.0.1 构建。

由于本项目使用的Spring Boot API基本处于稳定或仅维护状态，理论上本项目的工件适用于 Spring Boot 3.x 的所有版本，故在本项目中并没有对 Spring Boot 或 Spring Cloud 中的组件做implements或api依赖，只在compile阶段引入依赖，故需要或可能需要引入如下依赖：

- org.springframework.boot:spring-boot-starter
- org.springframework.cloud:spring-cloud-starter-openfeign
- org.springframework.cloud:spring-cloud-commons
- org.springframework.cloud:spring-cloud-context

在通常情况下，一个正常的Spring Boot与Spring Cloud项目只需要引入 `org.springframework.cloud:spring-cloud-starter-openfeign` 即可，但不排除可能需要引入其他组件的可能，请在新建项目时提前注意。

---

[<- 返回索引](index.md)
