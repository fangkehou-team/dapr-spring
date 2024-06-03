# 添加 Java 8 镜像来源
FROM eclipse-temurin:17-jre-alpine AS base
WORKDIR /app
EXPOSE 8080

FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /src
COPY ["gradlew", "gradlew"]
COPY ["gradle", "gradle"]
RUN ./gradlew
COPY ["settings.gradle", "settings.gradle"]
COPY ["build.gradle", "build.gradle"]
RUN ./gradlew build || return 0
COPY . .
RUN ./gradlew bootJar


FROM base AS final
WORKDIR /app
COPY --from=build /src/build/libs/ .
# 执行启动命令
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/DaprServerApplication.jar"]