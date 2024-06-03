# Getting Started Guide

[<- Back to Index](../index.md)

This section outlines the necessary steps to integrate your application with the dapr-secretstore-spring-boot-starter project.

## Adding Dependencies

This artifact is released on GitHub Packages, JitPack, and Maven Central for daily builds, SNAPSHOT versions, and stable releases. Please select the appropriate version for use.

Refer to [How to Download Artifacts](../index.md#how-to-download-artifacts) to complete the Repository configuration and add the following dependencies (if dapr-spring-boot-starter has already been added, this can be ignored):

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

After correctly adding the dependencies, add the following fields to your application's configuration:

```yaml
spring:
  config:
    import: dapr:secret:mysecret/secret
```

or

```properties
spring.config.import = dapr:secret:mysecret/secret
```

Where `dapr:secret:` is the fixed field for the dapr secret store configuration, and the subsequent `mysecret/secret` is the user-configured secret location, formatted as `[Secret Store Name]/[Secret Name]`. You can also provide only the Secret Store Name, in which case the Dapr Client will retrieve all Secrets under that Secret Store, with the secret location formatted as `[Secret Store Name]`.

> __Please note: To avoid parsing failures, do not add any extra characters (such as "/", ":") at the beginning, end, or middle of the secret location.__

The document translates the Dapr Secret Store by converting the Secret Store configuration into a string in the Spring Boot Properties format for parsing. Therefore, when configuring the Secret Store, please store the secret key in a format similar to `spring.mysql.username`.

> __Please note: If the Secret is in a nested format, such as using json in localfile mode and nesting more than two layers, you need to set the `nestedSeparator` to `.`.__

It's important to note that since the configuration import process occurs during the Bootstrap phase, when the Spring IOC has not yet begun the batch Bean creation process, it is not possible to inject DaprClient by specifying `@Bean` in the Application Configuration.

## Additional Information

Please refer to other entries on the [Homepage](../index.md) for more ways to use it, and consult the [Configuration](configuration.md) entry to configure the various parameters of DaprClient.

----------

[<- Back to Index](../index.md)
