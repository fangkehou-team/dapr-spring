# Configuration

[<- Back to Index](../index.md)

This section describes how you can configure your grpc-spring-boot-starter application.

This artifact depends on dapr-client-spring-boot-starter, please refer to [Dapr Client Spring Boot Starter Configuration](../client/configuration.md) for its configuration.

In addition to the above configurations, this artifact provides the following additional configurations:

| Parameter                             | Format  | Default Value | Description                       |
| ------------------------------------- | ------- | ------------- | --------------------------------- |
| dapr.secretstore.refresh-enabled*     | Boolean | true          | Whether to enable auto-refresh*   |
| dapr.secretstore.enabled*             | Boolean | true          | Whether to enable secret store*   |
| dapr.secretstore.timeout              | Integer | 2000          | Timeout for fetching secret store |

- Since the current version of dapr-client-spring-boot-starter does not support auto-refresh, the refresh-enabled configuration is currently ineffective.

- Since the current version of dapr-client-spring-boot-starter only supports Spring Boot Config, the configuration import is based on spring.config.import and does not implement support for System Property, so the enabled configuration is currently ineffective.

----------

[<- Back to Index](../index.md)