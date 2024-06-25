# Getting Started Guide

[<- Return to Index](../index.md)

This section describes the necessary steps to integrate your application with the dapr-openfeign-spring-boot-starter project.

## Adding Dependencies

This artifact is released daily builds, SNAPSHOT versions, and official versions on Github Packages, JitPack, and Maven Central. Please choose the appropriate version to use.

Refer to [How to Download Artifacts](../index.md#how-to-download-artifacts) to complete the Repository configuration, and add the following dependencies (if you have already added dapr-spring-boot-starter, you can ignore this):

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

> ___Please note: The above version number may be outdated, please ensure you are using the latest version. Components may require you to introduce additional dependencies.___

> ___For details, see [Version Overview](../versions.md)___

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

> ___Please note: The above version number may be outdated, please ensure you are using the latest SNAPSHOT version. Components may require you to introduce additional dependencies.___

> ___For details, see [Version Overview](../versions.md)___

## Basic Usage

After correctly introducing dependencies and enabling Spring Boot OpenFeign integration, you can specify OpenFeign to use DaprClient as the request sender by adding the `@UseDaprClient` annotation to the FeignClient interface.

For example:

```java
@FeignClient(name = "dapr", url = "method.myApp")
@UseDaprClient
public interface DaprFeignTestClient {

    @GetMapping("/create")
    String getQuery();

}
```

For the part about the url, please refer to the explanation about feign-dapr-client below.

## About feign-dapr-client

feign-dapr-client is an artifact independent of Spring Boot, without any dependencies on dapr-spring-boot-starter and spring-cloud-starter-openfeign, relying only on openfeign components. It mainly provides a Client for OpenFeign to receive OpenFeign requests and send them using Dapr.

The following content is from the Java Doc of DaprInvokeFeignClient.

Dapr currently supports two methods of invocation: invokeBinding (output binding) and invokeMethod. This client supports two modes: `http://binding.xxx` or `http://method.xxx`. The http scheme at the beginning is just to make Spring Boot Openfeign work properly.

For invokeMethod, the URL contains two types of information, similar to the format of an HTTP URL. The difference lies in the conversion of the host in the HTTP URL to appId, and the path (excluding "/") to methodName. For example, if you have a method with the appId "myApp" and the methodName "getAll", then the URL for this request would be `http://method.myApp/getAll`. You can also set HTTP headers if you wish, and the client will handle them. Currently, only HTTP calls are supported, but grpc calls may be supported in the future, with possible URLs like `http://method_grpc.myApp/getAll` or similar.

For invokeBinding, the URL also contains two types of information: the host is the bindingName, and the path is the operation. Note that different bindings support different operations, so you must consult the Dapr documentation. For example, if you have a binding with the bindingName "myBinding" and the supported operation is "create", then the URL for this request would be `http://binding.myBinding/create`. You can put some metadata in the headers of the Feign request, and the client will handle them.

As for the response, the result code is always 200 OK. If the client encounters any errors, it will throw an IOException.

Currently, we have no method to gain metadata from server as Dapr Client doesn't have methods to do that, so headers will be blank. If Accept header has set in request, a fake Content-Type header will be created in response, and it will be the first value of Accept header.

## Other

For more usage methods, please refer to other entries in the [homepage](../index.md), and consult the [configuration](configuration.md) entry to configure parameters.



----------

[<- Return to Index](../index.md)
