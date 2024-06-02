# Configuration

[<- Back to Index](../index.md)

This section describes how you can configure your grpc-spring-boot-starter client.

Following the parameters listed by the Dapr Java SDK, as well as those related to this artifact,

The configurable parameters for this artifact are as follows:

| Parameter | Format | Default Value | Description |
| --- | --- | --- | --- |
| dapr.client.sidecar-ip | String | 127.0.0.1 | Sidecar IP |
| dapr.client.http-port* | Integer | 3500 | HTTP sidecar port number* |
| dapr.client.grpc-port | Integer | 50001 | GRPC sidecar port number |
| dapr.client.grpc-endpoint | String | Empty | GRPC remote sidecar connection address |
| dapr.client.http-endpoint | String | Empty | HTTP remote sidecar connection address |
| dapr.client.max-retries | Integer | 0 | Client connection retry count |
| dapr.client.timeout | Duration | 2000 | Client connection timeout duration (milliseconds) |
| dapr.client.api-token | String | Empty | Client authentication Token |
| dapr.client.string-charset | Charset | UTF-8 | Encoding |
| dapr.client.http-client-read-timeout-seconds* | Integer | 60 | HTTPClient timeout duration* |
| dapr.client.http-client-max-requests* | Integer | 1024 | HTTPClient maximum request count* |
| dapr.client.http-client-max-idle-connections* | Integer | 128 | HTTPClient maximum idle connections* |

- The HTTP connection method has been defined as deprecated by the Java SDK, and this artifact does not use HTTP to connect to the sidecar, hence parameters marked with `*` are invalid. However, since these parameters are not marked as deprecated in the DaprClient, they have been included in the Spring Boot configuration for better compatibility with the DaprClient implementation.

---

[<- Back to Index](../index.md)