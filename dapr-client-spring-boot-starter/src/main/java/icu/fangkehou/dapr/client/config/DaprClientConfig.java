/*
 * Copyright (c) 2016-2024 Team Fangkehou
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package icu.fangkehou.dapr.client.config;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.DeprecatedConfigurationProperty;
import org.springframework.boot.convert.DurationUnit;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The properties for creating dapr client.
 */
@ToString
@EqualsAndHashCode
@ConfigurationProperties(DaprClientConfig.PROPERTY_PREFIX)
public class DaprClientConfig {

    public static final String PROPERTY_PREFIX = "dapr.client";

    /**
     * Dapr's default IP for HTTP and gRPC communication.
     */
    private static final String DEFAULT_SIDECAR_IP = "127.0.0.1";

    /**
     * Dapr's default HTTP port.
     */
    private static final Integer DEFAULT_HTTP_PORT = 3500;

    /**
     * Dapr's default gRPC port.
     */
    private static final Integer DEFAULT_GRPC_PORT = 50001;

    /**
     * Dapr's default max retries.
     */
    private static final Integer DEFAULT_API_MAX_RETRIES = 5;

    /**
     * Dapr's default timeout in seconds.
     */
    private static final Duration DEFAULT_API_TIMEOUT = Duration.ofMillis(2000L);

    /**
     * Dapr's default use of gRPC or HTTP.
     */
    private static final String DEFAULT_API_PROTOCOL = "grpc";

    /**
     * Dapr's default use of gRPC or HTTP for Dapr's method invocation APIs.
     */
    private static final String DEFAULT_API_METHOD_INVOCATION_PROTOCOL = "http";

    /**
     * Dapr's default String encoding: UTF-8.
     */
    private static final Charset DEFAULT_STRING_CHARSET = StandardCharsets.UTF_8;

    /**
     * Dapr's default timeout in seconds for HTTP client reads.
     */
    private static final Integer DEFAULT_HTTP_CLIENT_READ_TIMEOUT_SECONDS = 60;

    /**
     * Dapr's default maximum number of requests for HTTP client to execute concurrently.
     *
     * <p>
     * Above this requests queue in memory, waiting for the running calls to complete. Default is 64 in okhttp which is
     * OK for most case, but for some special case which is slow response and high concurrency, the value should set to
     * a little big.
     */
    private static final Integer DEFAULT_HTTP_CLIENT_MAX_REQUESTS = 1024;

    /**
     * Dapr's default maximum number of idle connections of HTTP connection pool.
     *
     * <p>
     * Attention! This is max IDLE connection, NOT max connection! It is also very important for high concurrency cases.
     */
    private static final Integer DEFAULT_HTTP_CLIENT_MAX_IDLE_CONNECTIONS = 128;

    /**
     * Dapr's default timeout in milliseconds for wait sidecar to be online.
     */
    private static final Integer DEFAULT_SIDECAR_CONNECT_WAIT_MILLIS = 2000;

    /**
     * Dapr's default value for if always wait dapr sidecar to be online.
     */
    private static final Boolean DEFAULT_SIDECAR_CONNECT_ALWAYS_RETRY = false;

    /**
     * IP for Dapr's sidecar.
     */
    String sidecarIp;

    /**
     * HTTP port for Dapr after checking system property and environment variable.
     */
    Integer httpPort;

    /**
     * GRPC port for Dapr after checking system property and environment variable.
     */
    Integer grpcPort;

    /**
     * GRPC endpoint for remote sidecar connectivity.
     */
    String grpcEndpoint;

    /**
     * HTTP endpoint for remote sidecar connectivity.
     */
    String httpEndpoint;

    /**
     * Maximum number of retries for retriable exceptions.
     */
    Integer maxRetries;

    /**
     * Timeout for API calls.
     */
    @DurationUnit(ChronoUnit.MILLIS)
    Duration timeout;

    /**
     * Determines if Dapr client will use gRPC or HTTP to talk to Dapr's side car.
     *
     * @deprecated This attribute will be deleted at SDK version 1.10.
     */
    @Deprecated
    String apiProtocol;

    /**
     * Determines if Dapr client should use gRPC or HTTP for Dapr's service method invocation APIs.
     *
     * @deprecated This attribute will be deleted at SDK version 1.10.
     */
    @Deprecated
    String apiMethodInvocationProtocol;

    /**
     * API token for authentication between App and Dapr's side car.
     */
    String apiToken;

    /**
     * Determines which string encoding is used in Dapr's Java SDK.
     */
    Charset stringCharset;

    /**
     * Dapr's timeout in seconds for HTTP client reads.
     */
    Integer httpClientReadTimeoutSeconds;

    /**
     * Dapr's maximum number of requests for HTTP client to execute concurrently.
     */
    Integer httpClientMaxRequests;

    /**
     * Dapr's maximum number of idle connections for HTTP connection pool.
     */
    Integer httpClientMaxIdleConnections;


    public String getSidecarIp() {
        return sidecarIp == null ? DEFAULT_SIDECAR_IP : sidecarIp;
    }

    public void setSidecarIp(String sidecarIp) {
        this.sidecarIp = sidecarIp;
    }

    public Integer getHttpPort() {
        return httpPort == null ? DEFAULT_HTTP_PORT : httpPort;
    }

    public void setHttpPort(Integer httpPort) {
        this.httpPort = httpPort;
    }

    public Integer getGrpcPort() {
        return grpcPort == null ? DEFAULT_GRPC_PORT : grpcPort;
    }

    public void setGrpcPort(Integer grpcPort) {
        this.grpcPort = grpcPort;
    }

    public String getGrpcEndpoint() {
        return grpcEndpoint;
    }

    public void setGrpcEndpoint(String grpcEndpoint) {
        this.grpcEndpoint = grpcEndpoint;
    }

    public String getHttpEndpoint() {
        return httpEndpoint;
    }

    public void setHttpEndpoint(String httpEndpoint) {
        this.httpEndpoint = httpEndpoint;
    }

    public Integer getMaxRetries() {
        return maxRetries == null ? DEFAULT_API_MAX_RETRIES : maxRetries;
    }

    public void setMaxRetries(Integer maxRetries) {
        this.maxRetries = maxRetries;
    }

    public Duration getTimeout() {
        return timeout == null ? DEFAULT_API_TIMEOUT : timeout;
    }

    public void setTimeout(Duration timeout) {
        this.timeout = timeout;
    }

    @Deprecated
    @DeprecatedConfigurationProperty
    public String getApiProtocol() {
        return apiProtocol == null ? DEFAULT_API_PROTOCOL : apiProtocol;
    }

    @Deprecated
    @DeprecatedConfigurationProperty
    public void setApiProtocol(String apiProtocol) {
        this.apiProtocol = apiProtocol;
    }

    @Deprecated
    @DeprecatedConfigurationProperty
    public String getApiMethodInvocationProtocol() {
        return apiMethodInvocationProtocol == null ? DEFAULT_API_METHOD_INVOCATION_PROTOCOL
                : apiMethodInvocationProtocol;
    }

    @Deprecated
    @DeprecatedConfigurationProperty
    public void setApiMethodInvocationProtocol(String apiMethodInvocationProtocol) {
        this.apiMethodInvocationProtocol = apiMethodInvocationProtocol;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public Charset getStringCharset() {
        return stringCharset == null ? DEFAULT_STRING_CHARSET : stringCharset;
    }

    public void setStringCharset(Charset stringCharset) {
        this.stringCharset = stringCharset;
    }

    public Integer getHttpClientReadTimeoutSeconds() {
        return httpClientReadTimeoutSeconds == null ? DEFAULT_HTTP_CLIENT_READ_TIMEOUT_SECONDS
                : httpClientReadTimeoutSeconds;
    }

    public void setHttpClientReadTimeoutSeconds(Integer httpClientReadTimeoutSeconds) {
        this.httpClientReadTimeoutSeconds = httpClientReadTimeoutSeconds;
    }

    public Integer getHttpClientMaxRequests() {
        return httpClientMaxRequests == null ? DEFAULT_HTTP_CLIENT_MAX_REQUESTS : httpClientMaxRequests;
    }

    public void setHttpClientMaxRequests(Integer httpClientMaxRequests) {
        this.httpClientMaxRequests = httpClientMaxRequests;
    }

    public Integer getHttpClientMaxIdleConnections() {
        return httpClientMaxIdleConnections == null ? DEFAULT_HTTP_CLIENT_MAX_IDLE_CONNECTIONS
                : httpClientMaxIdleConnections;
    }

    public void setHttpClientMaxIdleConnections(Integer httpClientMaxIdleConnections) {
        this.httpClientMaxIdleConnections = httpClientMaxIdleConnections;
    }
}
