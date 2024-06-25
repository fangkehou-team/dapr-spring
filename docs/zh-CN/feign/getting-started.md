# 入门指南

[<- 返回索引](../index.md)

本节描述了将您的应用程序接入 dapr-openfeign-spring-boot-starter 项目的必要步骤。

## 引入依赖

本工件分别在Github Packages, JitPack和Maven Central发布每日构建，SNAPSHOT版本和正式版本，请您选择合适的版本使用。

请参阅[如何下载工件](../index.md#如何下载工件)完成Repository配置，并添加如下依赖项（若已添加dapr-spring-boot-starter，则可以忽略）

__GitHub Packages, Maven Central__

```xml
<dependency>
    <groupId>icu.fangkehou</groupId>
    <artifactId>dapr-openfeign-spring-boot-starter</artifactId>
    <version>0.4.0-SNAPSHOT</version>
</dependency>
```

```groovy
dependencies {
    implementation 'icu.fangkehou:dapr-openfeign-spring-boot-starter:0.4.0-SNAPSHOT'
}
```

> ___请注意：以上版本号可能会过时，请确保您使用最新版本。组件可能会要求你引入其他依赖。___

> ___详情参阅[版本概述](../versions.md)___

__JitPack__

```xml
<dependency>
    <groupId>icu.fangkehou.dapr-spring</groupId>
    <artifactId>dapr-openfeign-spring-boot-starter</artifactId>
    <version>0.4.0-SNAPSHOT</version>
</dependency>
```

```groovy
dependencies {
    implementation 'icu.fangkehou.dapr-spring:dapr-openfeign-spring-boot-starter:0.4.0-SNAPSHOT'
}
```

> ___请注意：以上版本号可能会过时，请确保您使用最新SNAPSHOT版本。组件可能会要求你引入其他依赖。___

> ___详情参阅[版本概述](../versions.md)___

## 基础使用

在正确引入依赖并打开Spring Boot OpenFeign集成后，您可以通过在FeignClient接口添加`@UseDaprClient`注解来指定OpenFeign使用DaprClient作为请求发送者

例如：

```java
@FeignClient(name = "dapr", url = "method.myApp")
@UseDaprClient
public interface DaprFeignTestClient {

    @GetMapping("/create")
    String getQuery();

}
```

其中对于url的部分，请参阅下文中关于feign-dapr-client的说明

## 关于feign-dapr-client的相关说明

feign-dapr-client是独立于Spring Boot的工件，没有引入dapr-spring-boot-starter和spring-cloud-starter-openfeign相关的任何依赖，只依赖于openfeign相关组件，其主要为OpenFeign提供了一个Client用于接受OpenFeign请求并使用Dapr发送。

下面内容来自DaprInvokeFeignClient的Java Doc。

目前，Dapr支持两种调用操作的方式，即invokeBinding（输出绑定）和invokeMethod，因此这个客户端支持两种模式：http://binding.xxx 或 http://method.xxx 。您不必关心为什么起始处有一个http模式，这只是为了让Spring Boot Openfeign正常工作。

对于invokeMethod，URL中有两种类型的信息，格式HTTP URL非常相似，不同之处在于HTTP URL中的主机被转换为appId，路径（不包括“/”）被转换为methodName。例如，如果您有一个方法，其appid是"myApp"，methodName是"getAll"，那么这个请求的URL就是 "http://method.myApp/getAll" 。如果您愿意，也可以设置HTTP头部，客户端将处理它们。目前只支持HTTP调用，但将来可能会支持grpc调用，可能的URL将是"http://method_grpc.myApp/getAll"或类似的形式。

对于invokeBinding，URL中也有两种类型的信息，主机是bindingName，路径是操作。注意：不同的绑定支持不同的操作，所以您必须查阅Dapr的文档。例如，如果您有一个绑定，其bindingName是"myBinding"，支持的操作是"create"，那么这个请求的URL就是 "http://binding.myBinding/create" 。您可以在Feign请求的头部放入一些元数据，客户端将处理它们。

至于响应，结果代码始终是200 OK，如果客户端遇到任何错误，它将抛出一个IOException。

当前，我们无法从服务器获取元数据，因为Dapr客户端没有这样做的方法，所以HTTP响应头将为空。如果请求中设置了Accept请求头，那么在响应中将创建一个假的Content-Type响应头，并且它将是Accept请求头的第一个值。

## 其他

请参阅[主页](../index.md)中的其他条目来获取更多使用方式，请参阅[配置](configuration.md)条目对参数进行配置。



----------

[<- 返回索引](../index.md)
