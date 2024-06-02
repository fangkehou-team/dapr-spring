# Dapr-Spring-Boot-Starter 文档

Dapr-Spring-Boot-Starter 将 [Dapr](https://dapr.io) 与 [Spring Boot 进行整合](https://spring.io/projects/spring-boot)
该项目简化了 Dapr 客户端相关的设置，只需要为项目添加了一系列依赖项，并添加适当的注解。 本项目的目标是让Dapr for Spring Boot获得接近Spring Cloud的开发体验。



__请注意：__

本项目并没有重写Dapr Java SDK甚至官方Dapr Spring Boot工件的任何逻辑，仅为其增强了Spring Boot的使用体验，您可能需要参阅[Dapr Java SDK](https://docs.dapr.io/zh-hans/developing-applications/sdks/java/)来了解Dapr Client以及官方Dapr Spring Boot工件的使用方法（如Pubsub等）



## 目录

- dapr-client-spring-boot-starter
  - [入门指南](client/getting-started.md)
  - [配置](client/configuration.md)
- dapr-openfeign-spring-boot-starter，feign-dapr-client
  - [入门指南](feign/getting-started.md)
  - [配置](feign/configuration.md)
- dapr-secretstore-spring-boot-starter
  - [入门指南](secretstore/getting-started.md)
  - [配置](secretstore/configuration.md)
- [疑难解答](trouble-shooting.md)
- [示例项目](examples.md)
- [版本概述](versions.md)
- [参与贡献](contributions.md)

## 如何下载工件

本工件分别在Github Packages, JitPack和Maven Central发布每日构建，SNAPSHOT版本和正式版本，请您选择合适的版本使用。

- __使用Github Packages__  ___详情参阅[版本概述](versions.md)___

  若使用Github Packages托管的构建，您需要参考Github官方文档正确设置身份验证才可以下载工件。

  详情参阅[Gradle 说明](https://docs.github.com/zh/packages/working-with-a-github-packages-registry/working-with-the-gradle-registry#using-a-published-package) 以及 [Maven 说明](https://docs.github.com/zh/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry#installing-a-package)

  在配置好repository后，请将如下依赖加入到您的Maven或Gradle依赖项中：

  __Maven__

  ```xml
  <dependency>
      <groupId>icu.fangkehou</groupId>
      <artifactId>dapr-spring-boot-starter</artifactId>
      <version>0.3.0-SNAPSHOT</version>
  </dependency>
  ```

  __Gradle__

  ```groovy
  dependencies {
      implementation 'icu.fangkehou:dapr-spring-boot-starter:0.3.0-SNAPSHOT'
  }
  ```

  ___请注意：以上版本号可能会过时，请确保您使用最新SNAPSHOT版本。组件可能会要求你引入其他依赖。___

  ___详情参阅[版本概述](versions.md)___

- __使用JitPack__

  若使用Github Packages托管的构建，您需要参考JitPack官方文档正确设置repository才可以下载工件。

  详情参阅[JitPack 说明](https://docs.jitpack.io/intro/#building-with-jitpack)

  在配置好repository后，请将如下依赖加入到您的Maven或Gradle依赖项中：

  __Maven__

  ```xml
  <dependency>
      <groupId>icu.fangkehou.dapr-spring</groupId>
      <artifactId>dapr-spring-boot-starter</artifactId>
      <version>0.4.0-SNAPSHOT</version>
  </dependency>
  ```

  __Gradle__

  ```groovy
  dependencies {
      implementation 'icu.fangkehou.dapr-spring:dapr-spring-boot-starter:0.4.0-SNAPSHOT'
  }
  ```

  ___请注意：以上版本号可能会过时，请确保您使用最新SNAPSHOT版本。组件可能会要求你引入其他依赖。___

  ___详情参阅[版本概述](versions.md)___

- __使用Maven Central__

  在配置好repository后，请将如下依赖加入到您的Maven或Gradle依赖项中：

  __Maven__

  ```xml
  <dependency>
      <groupId>icu.fangkehou</groupId>
      <artifactId>dapr-spring-boot-starter</artifactId>
      <version>0.4.0</version>
  </dependency>
  ```

  __Gradle__

  ```groovy
  dependencies {
      implementation 'icu.fangkehou:dapr-spring-boot-starter:0.4.0'
  }
  ```

  ___请注意：以上版本号可能会过时，请确保您使用最新版本。组件可能会要求你引入其他依赖。___

  ___详情参阅[版本概述](versions.md)___
