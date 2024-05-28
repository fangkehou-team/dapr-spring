package org.springframework.cloud.openfeign;

import feign.Feign;
import feign.Target;
import icu.fangkehou.dapr.feign.DaprInvokeFeignClient;
import icu.fangkehou.dapr.feign.targeter.DaprClientTargeter;
import io.dapr.client.DaprClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.context.annotation.*;

import java.lang.reflect.Method;

@Slf4j
@Configuration(proxyBeanMethods = false)
public class DaprFeignClientOpenFeignAutoConfiguration {
//
//    @Bean
//    FeignClientsRegistrarProxyForDaprFeignClient feignClientsRegistrarProxyForDaprFeignClient() {
//        return new FeignClientsRegistrarProxyForDaprFeignClient();
//    }
    
    @Configuration(proxyBeanMethods = false)
    @Conditional(FeignCircuitBreakerDisabledConditions.class)
    protected static class DefaultFeignTargeterConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public Targeter feignTargeter(DaprClient daprClient) {
            return new DaprClientTargeter(new DaprInvokeFeignClient(daprClient));
        }

    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(CircuitBreaker.class)
    @ConditionalOnProperty(value = "spring.cloud.openfeign.circuitbreaker.enabled", havingValue = "true")
    protected static class CircuitBreakerPresentFeignTargeterConfiguration {

        @Bean
        @ConditionalOnMissingBean(CircuitBreakerFactory.class)
        public Targeter defaultFeignTargeter(DaprClient daprClient) {
            return new DaprClientTargeter(new DaprInvokeFeignClient(daprClient));
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
