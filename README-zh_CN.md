# 这个repository是我在2024年6月开始编写的，那个时候Dapr的java-sdk中对于SpringBoot的支持还比较简陋，近期在查阅java-sdk源码时突然发现java-sdk里已经实现了大部分这个repository里的功能，看来是时候让这个repository被archive了！关于Cloud Config的部分，我会在近期整理后提交相应的pull request到官方的java-sdk中。

README: [English](README.md) | [中文](README-zh_CN.md)

# dapr-spring-boot
Dapr SpringBoot Starter - 让Dapr Java开发像Spring Cloud一样优雅.

[![License](https://img.shields.io/github/license/fangkehou-team/dapr-spring.svg)](LICENSE)
[![Maven Central with version prefix filter](https://img.shields.io/maven-central/v/icu.fangkehou/dapr-spring-boot-starter.svg)](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22icu.fangkehou%22%20dapr)

## 功能

- 自动注入 DaprClient、 DaprPreviewClient
- 在 Spring Boot 中设置 DaprClient 参数
- 与OpenFeign集成
- 远程配置引入（介于当前 `Dapr 应用配置功能` 只在 `DaprPreviewClient` 中提供，目前只支持引入 `Dapr 密钥存储`）


## 使用

请参考 [项目文档](https://dapr-spring.fangkehou.icu) 获得相关信息
