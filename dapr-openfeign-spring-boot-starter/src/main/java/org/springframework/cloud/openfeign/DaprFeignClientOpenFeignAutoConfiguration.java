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

package org.springframework.cloud.openfeign;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.context.annotation.*;

import feign.Feign;
import feign.Target;
import icu.fangkehou.dapr.client.config.DaprClientConfig;
import icu.fangkehou.dapr.feign.DaprInvokeFeignClient;
import icu.fangkehou.dapr.feign.targeter.DaprClientTargeter;
import io.dapr.client.DaprClient;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration(proxyBeanMethods = false)
public class DaprFeignClientOpenFeignAutoConfiguration {
    //
    // @Bean
    // FeignClientsRegistrarProxyForDaprFeignClient feignClientsRegistrarProxyForDaprFeignClient() {
    // return new FeignClientsRegistrarProxyForDaprFeignClient();
    // }

    @Configuration(proxyBeanMethods = false)
    @Conditional(FeignCircuitBreakerDisabledConditions.class)
    protected static class DefaultFeignTargeterConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public Targeter feignTargeter(DaprClient daprClient, DaprClientConfig daprClientConfig) {
            return new DaprClientTargeter(new DaprInvokeFeignClient(daprClient,
                    (int) daprClientConfig.getTimeout().toMillis(), daprClientConfig.getMaxRetries()));
        }

    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(CircuitBreaker.class)
    @ConditionalOnProperty(value = "spring.cloud.openfeign.circuitbreaker.enabled", havingValue = "true")
    protected static class CircuitBreakerPresentFeignTargeterConfiguration {

        @Bean
        @ConditionalOnMissingBean(CircuitBreakerFactory.class)
        public Targeter defaultFeignTargeter(DaprClient daprClient, DaprClientConfig daprClientConfig) {
            return new DaprClientTargeter(new DaprInvokeFeignClient(daprClient,
                    (int) daprClientConfig.getTimeout().toMillis(), daprClientConfig.getMaxRetries()));
        }

        @Bean
        @ConditionalOnMissingBean(CircuitBreakerNameResolver.class)
        @ConditionalOnProperty(value = "spring.cloud.openfeign.circuitbreaker.alphanumeric-ids.enabled",
                havingValue = "false")
        public CircuitBreakerNameResolver circuitBreakerNameResolver() {
            return new DefaultCircuitBreakerNameResolver();
        }

        @Bean
        @ConditionalOnMissingBean(CircuitBreakerNameResolver.class)
        @ConditionalOnProperty(value = "spring.cloud.openfeign.circuitbreaker.alphanumeric-ids.enabled",
                havingValue = "true", matchIfMissing = true)
        public CircuitBreakerNameResolver alphanumericCircuitBreakerNameResolver() {
            return new AlphanumericCircuitBreakerNameResolver();
        }

        @SuppressWarnings("rawtypes")
        @Bean
        @ConditionalOnMissingBean
        @ConditionalOnBean(CircuitBreakerFactory.class)
        public Targeter circuitBreakerFeignTargeter(CircuitBreakerFactory circuitBreakerFactory,
                @Value("${spring.cloud.openfeign.circuitbreaker.group.enabled:false}") boolean circuitBreakerGroupEnabled,
                CircuitBreakerNameResolver circuitBreakerNameResolver) {
            return new FeignCircuitBreakerTargeter(circuitBreakerFactory, circuitBreakerGroupEnabled,
                    circuitBreakerNameResolver);
        }

        static class DefaultCircuitBreakerNameResolver implements CircuitBreakerNameResolver {

            @Override
            public String resolveCircuitBreakerName(String feignClientName, Target<?> target, Method method) {
                return Feign.configKey(target.type(), method);
            }

        }

        static class AlphanumericCircuitBreakerNameResolver extends DefaultCircuitBreakerNameResolver {

            @Override
            public String resolveCircuitBreakerName(String feignClientName, Target<?> target, Method method) {
                return super.resolveCircuitBreakerName(feignClientName, target, method).replaceAll("[^a-zA-Z0-9]", "");
            }

        }

    }
}
