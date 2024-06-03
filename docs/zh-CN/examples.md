# 示例

示例项目演示如何使用这些项目。

这些项目可以作为您自己的项目的模板。 我们使用它们来验证这个库在不同的环境中运行，我们不会在不受注意的情况下改变它的行为。

> **注意:**
> 如果您对这些项目有疑问，或者想要其他的示例，随时可以提出一个 [issue](https://github.com/fangkehou-team/dapr-spring/issues)。

在Github仓库中，我们提供了一个简单的例子用来向你展示Dapr Spring Boot Starter的基本使用方式。在这个项目中，我们展示了使用@Value获取配置，在数据库连接阶段注入配置，使用openfeign配合invokeMethod请求其他微服务。

本例子使用需要通过Docker Compose启动，我们提供了两种构建方式：使用Dockerfile自动构建，或在本机进行构建而后转为Docker镜像。目前第一种方式由于并没有启动Dapr实例故暂时无效，我们正在寻找解决方案，建议您使用第二种方法。

如使用第一种方式启动，请使用`docker-compose.yml`和`docker-compose-override.yml` 启动 Docker Compose

如使用第二种方式启动，则在编译过程中需要启动一个Dapr实例，请使用example根目录下的`docker-compose-dapr-only.yml`启动一个Dapr实例后再进行构建。

当工件编译完成后，请使用`docker-compose-development.yml`和`docker-compose-override.yml` 启动 Docker Compose
