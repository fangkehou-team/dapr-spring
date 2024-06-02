# 配置

[<- 返回索引](../index.md)

本节描述您如何配置您的 grpc-spring-boot-starter 客户端。

依照Dapr Java SDK所列举的参数，以及本工件关于

本工件可配置的参数如下：

| 参数  | 格式  | 默认值 | 说明  |
| --- | --- | --- | --- |
| dapr.client.sidecar-ip | String | 127.0.0.1 | 边车IP |
| dapr.client.http-port* | Integer | 3500 | HTTP边车端口号* |
| dapr.client.grpc-port | Integer | 50001 | GRPC边车端口号 |
| dapr.client.grpc-endpoint | String | 空   | GRPC远程边车连接地址 |
| dapr.client.http-endpoint | String | 空   | HTTP远程边车连接地址 |
| dapr.client.max-retries | Integer | 0   | Client连接重试次数 |
| apr.client.timeout | Duration | 2000 | Client连接超时时间（毫秒） |
| dapr.client.api-token | String | 空   | Client鉴权Token |
| dapr.client.string-charset | Charset | UTF-8 | 编码  |
| dapr.client.http-client-read-timeout-seconds* | Integer | 60  | HTTPClient超时时间* |
| dapr.client.http-client-max-requests* | Integer | 1024 | HTTPClient最大请求数* |
| dapr.client.http-client-max-idle-connections* | Integer | 128 | HTTPClient最大连接数* |

- HTTP连接方式已经被Java SDK定义为过时方法，本工件中亦没有使用HTTP连接边车，故带有`*`标志的参数为无效参数，但由于DaprClient中并没有标记这些参数为过时参数，故为了对DaprClient实现更好的兼容性将这些参数加入了Spring Boot配置中。



---

[<- 返回索引](../index.md)
