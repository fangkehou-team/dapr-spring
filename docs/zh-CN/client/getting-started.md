# 入门指南

[<- 返回索引](../index.md)

本节描述了将您的应用程序接入 dapr-client-spring-boot-starter 项目的必要步骤。

## 引入依赖

    本工件分别在Github Packages, JitPack和Maven Central发布每日构建，SNAPSHOT版本和正式版本，请您选择合适的版本使用。

    请参阅[如何下载工件](../index.md#如何下载工件)完成Repository配置，并添加如下依赖项（若已添加dapr-spring-boot-starter，则可以忽略）

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

___请注意：以上版本号可能会过时，请确保您使用最新版本。组件可能会要求你引入其他依赖。___

___详情参阅[版本概述](../versions.md)___

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

___请注意：以上版本号可能会过时，请确保您使用最新SNAPSHOT版本。组件可能会要求你引入其他依赖。___

___详情参阅[版本概述](../versions.md)___

## 基础使用

    在正确引入依赖后，启动Spring Boot时，默认情况下，`dapr-client-spring-boot-starter` 将使用本地IP地址作为边车IP，并使用默认端口号测试Dapr连接，在等待2秒未获得连接时，Spring Boot应用将会报错停止。

    当应用正常获得Dapr连接后，Spring Boot应用将自动创建`DaprClient`以及`DaprPreviewClient`两个Bean，这两个Bean既可以使用@Autowired注解注入，也可以在构造函数，请求处理等方法中作为参数注入，请参阅[Dapr 官方文档](https://docs.dapr.io/zh-hans/developing-applications/sdks/java/)以及相应的Github仓库了解Dapr Java SDK的使用方法。

## 其他

    请参阅[主页](../index.md)中的其他条目来获取更多使用方式，请参阅[配置](configuration.md)条目对DaprClient的各项参数进行配置。



----------

[<- 返回索引](../index.md)
