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

package icu.fangkehou.dapr.client.utils;

import icu.fangkehou.dapr.client.config.DaprClientConfig;

public class SetupDaprPropertyUtil {
    public static void setupDaprSystemProperty(DaprClientConfig daprClientConfig) {
        System.setProperty("dapr.sidecar.ip", daprClientConfig.getSidecarIp());
        System.setProperty("dapr.http.port", daprClientConfig.getHttpPort().toString());
        System.setProperty("dapr.grpc.port", daprClientConfig.getGrpcPort().toString());

        // grpc endpoint could be null
        if (daprClientConfig.getGrpcEndpoint() != null) {
            System.setProperty("dapr.grpc.endpoint", daprClientConfig.getGrpcEndpoint());
        }

        // http endpoint could be null
        if (daprClientConfig.getGrpcEndpoint() != null) {
            System.setProperty("dapr.http.endpoint", daprClientConfig.getGrpcEndpoint());
        }

        System.setProperty("dapr.api.max.retries", daprClientConfig.getMaxRetries().toString());
        System.setProperty("dapr.api.timeout.milliseconds", String.valueOf(daprClientConfig.getTimeout().toMillis()));

        // api token could be null
        if (daprClientConfig.getApiToken() != null) {
            System.setProperty("dapr.api.token", daprClientConfig.getApiToken());
        }

        System.setProperty("dapr.string.charset", daprClientConfig.getStringCharset().name());
        System.setProperty("dapr.http.client.read.timeout.seconds",
                daprClientConfig.getHttpClientReadTimeoutSeconds().toString());
        System.setProperty("dapr.http.client.max.requests", daprClientConfig.getHttpClientMaxRequests().toString());
        System.setProperty("dapr.http.client.max.idle.connections",
                daprClientConfig.getHttpClientMaxIdleConnections().toString());
    }
}
