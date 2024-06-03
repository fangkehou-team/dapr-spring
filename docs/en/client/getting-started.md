# Getting Started Guide

[<- Back to Index](../index.md)

This section outlines the necessary steps to integrate your application with the dapr-client-spring-boot-starter project.

## Adding Dependencies

This artifact is released daily builds, SNAPSHOT versions, and official versions on GitHub Packages, JitPack, and Maven Central. Please choose the appropriate version to use.

Refer to [How to Download Artifacts](../index.md#how-to-download-artifacts) to complete the Repository configuration and add the following dependencies (if you have already added dapr-spring-boot-starter, you can ignore this):

__GitHub Packages, Maven Central__

```xml
<dependency>
    <groupId>icu.fangkehou</groupId>
    <artifactId>dapr-client-spring-boot-starter</artifactId>
    <version>0.4.0-SNAPSHOT</version>
</dependency>
```

```groovy
dependencies {
    implementation 'icu.fangkehou:dapr-client-spring-boot-starter:0.4.0-SNAPSHOT'
}
```

> ___Please note: The above version number may be outdated, ensure you are using the latest version. Components may require you to introduce additional dependencies.___

> ___For details, see [Version Overview](../versions.md)___

__JitPack__

```xml
<dependency>
    <groupId>icu.fangkehou.dapr-spring</groupId>
    <artifactId>dapr-client-spring-boot-starter</artifactId>
    <version>0.4.0-SNAPSHOT</version>
</dependency>
```

```groovy
dependencies {
    implementation 'icu.fangkehou.dapr-spring:dapr-client-spring-boot-starter:0.4.0-SNAPSHOT'
}
```

> ___Please note: The above version number may be outdated, ensure you are using the latest SNAPSHOT version. Components may require you to introduce additional dependencies.___

> ___For details, see [Version Overview](../versions.md)___

## Basic Usage

After correctly introducing the dependencies, when starting Spring Boot, by default, `dapr-client-spring-boot-starter` will use the local IP address as the sidecar IP and test the Dapr connection using the default port number. If a connection is not obtained within 2 seconds, the Spring Boot application will stop with an error.

Once the application obtains a normal Dapr connection, the Spring Boot application will automatically create `DaprClient` and `DaprPreviewClient` beans. These beans can be injected using the @Autowired annotation or as parameters in constructors, request handling, and other methods. Please refer to the [Dapr Official Documentation](https://docs.dapr.io/developing-applications/sdks/java/) and the corresponding Github repository to learn how to use the Dapr Java SDK.

## Others

Please refer to other entries in the [Homepage](../index.md) for more usage methods, and refer to the [Configuration](configuration.md) entry to configure the parameters of DaprClient.



----------

[<- Back to Index](../index.md)
