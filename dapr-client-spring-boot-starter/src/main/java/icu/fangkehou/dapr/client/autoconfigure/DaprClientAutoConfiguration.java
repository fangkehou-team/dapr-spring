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

package icu.fangkehou.dapr.client.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import icu.fangkehou.dapr.client.config.DaprClientConfig;
import icu.fangkehou.dapr.client.utils.SetupDaprPropertyUtil;
import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import io.dapr.client.DaprPreviewClient;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;


/**
 * The autoconfiguration used by Spring-Boot that contains all beans to create and inject dapr clients into beans.
 * <p>
 * Dapr Java SDK currently has two types of Client: DaprClient and DaprPreviewClient, and both of them contains some
 * important methods, so we will create them all.
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties
@ConditionalOnClass(DaprClient.class)
public class DaprClientAutoConfiguration {

    @ConditionalOnMissingBean
    @Bean
    DaprClientConfig daprClientConfig() {
        return new DaprClientConfig();
    }

    @ConditionalOnMissingBean
    @Bean
    DaprClient daprClient(DaprClientConfig daprClientConfig) {
        SetupDaprPropertyUtil.setupDaprSystemProperty(daprClientConfig);

        DaprClient daprClient = new DaprClientBuilder().build();
        waitForDaprClient(daprClient, (int) daprClientConfig.getTimeout().toMillis(),
                daprClientConfig.getMaxRetries());

        return daprClient;
    }

    void waitForDaprClient(DaprClient daprClient, Integer waitTime, Integer maxRetry) {
        Mono<Void> result = daprClient.waitForSidecar(waitTime);

        int tempMaxRetry = maxRetry - 1;

        try {
            result.block();
        } catch (RuntimeException e) {
            if (tempMaxRetry == -1) {
                waitForDaprClient(daprClient, waitTime, maxRetry);
            } else if (tempMaxRetry != 0) {
                waitForDaprClient(daprClient, waitTime, tempMaxRetry);
            } else {
                throw e;
            }
        }
    }

    @ConditionalOnMissingBean
    @Bean
    DaprPreviewClient daprPreviewClient(DaprClientConfig daprClientConfig) {
        SetupDaprPropertyUtil.setupDaprSystemProperty(daprClientConfig);

        DaprPreviewClient daprPreviewClient = new DaprClientBuilder().buildPreviewClient();

        return daprPreviewClient;
    }
}
