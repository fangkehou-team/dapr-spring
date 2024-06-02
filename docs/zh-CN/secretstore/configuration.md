# 配置

[<- 返回索引](../index.md)

本节描述您如何配置您的 grpc-spring-boot-starter 应用程序。

本工件依赖于dapr-client-spring-boot-starter，请参考[Dapr Client Spring Boot Starter配置](../client/configuration.md)对其进行配置

除了上述配置外，本工件额外提供了如下的配置：

| 参数                                | 格式      | 默认值  | 说明                 |
| --------------------------------- | ------- | ---- | ------------------ |
| dapr.secretstore.refresh-enabled* | Boolean | true | 是否启用自动刷新*          |
| dapr.secretstore.enabled*         | Boolean | true | 是否启用secret store*  |
| dapr.secretstore.timeout          | Integer | 2000 | 获取secret store超时时间 |

- 由于目前版本的dapr-client-spring-boot-starter并没有对自动刷新提供支持，所以refresh-enabled配置目前无效。

- 由于当前版本dapr-client-spring-boot-starter只针对Spring Boot Config 提供支持，故当前版本的配置导入是基于spring.config.import实现的，并没有实现对System Property的支持，所以enabled配置目前无效。

----------

[<- 返回索引](../index.md)
