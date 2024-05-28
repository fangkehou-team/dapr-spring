package icu.fangkehou.dapr.feign.autoconfigure;

import icu.fangkehou.dapr.feign.DaprInvokeFeignClient;
import io.dapr.client.DaprClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(FeignAutoConfiguration.class)
public class DaprFeignClientAutoConfiguration {

//    @Bean()
//    public DaprInvokeFeignClient daprInvokeFeignClient(DaprClient daprClient) {
//        return new DaprInvokeFeignClient(daprClient);
//    }
}
