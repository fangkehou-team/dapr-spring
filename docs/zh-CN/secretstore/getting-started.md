# 入门指南

[<- 返回索引](../index.md)

本节描述了将您的应用程序接入 dapr-secretstore-spring-boot-starter 项目的必要步骤。

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

在正确引入依赖后，在应用的配置中添加如下字段：

```yaml
spring:
  config:
    import: dapr:secret:mysecret/secret
```

或

```properties
spring.config.import = dapr:secret:mysecret/secret
```

其中dapr:secret:为dapr secret store配置的固定字段，后方的mysecret/secret为用户配置的secret位置，格式为`[Secret Store Name]/[Secret Name]`，也可以只提供Secret Store Name，此时Dapr Client将获取该Secret Store下的所有Secret，此时secret位置格式为`[Secret Store Name]`

__请注意：为了避免解析失败，请不要在secret位置的首尾及中间位置额外添加任何字符（如"/"，":"）__

值得注意的是，由于配置引入的过程是在Bootstrap阶段进行的，此时Spring IOC并没有开始进行批量的Bean创建过程，所以无法通过在Application Configuration中指定`@Bean`来实现对DaprClient的注入。

## 其他

请参阅[主页](../index.md)中的其他条目来获取更多使用方式，请参阅[配置](configuration.md)条目对DaprClient的各项参数进行配置。

----------

[<- 返回索引](../index.md)
