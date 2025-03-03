# I started this repository back in June 2024, when Dapr's Java SDK had pretty basic support for Spring Boot. Recently, while checking out the Java SDK source code, I noticed it now covers most of the features in this repository. Looks like it's time to archive this repo! As for the Cloud Config part, I'll tidy it up and submit a pull request to the official java sdk soon.

README: [English](README.md) | [中文](README-zh_CN.md)

# dapr-spring-boot
Dapr SpringBoot Starter — Let the Dapr works just like what Spring Cloud does.

[![License](https://img.shields.io/github/license/fangkehou-team/dapr-spring.svg)](LICENSE)
[![](https://jitpack.io/v/icu.fangkehou/dapr-spring.svg)](https://jitpack.io/#icu.fangkehou/dapr-spring)
[![Maven Central with version prefix filter](https://img.shields.io/maven-central/v/icu.fangkehou/dapr-spring-boot-starter.svg)](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22icu.fangkehou%22%20dapr)

## Features

- Get DaprClient and DaprPreviewClient from Spring IOC
- Set DaprClient Properties in SpringBoot Properties
- An OpenFeign Client to make http request from Dapr Server
- A Config Backend Just Like Spring Cloud Vault (currently only using secret store as config store api is not stable)


## Usage

Please refer to the [documentation](https://dapr-spring.fangkehou.icu) to get the full usage.
