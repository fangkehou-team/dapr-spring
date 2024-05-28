package icu.fangkehou.test.daprtest.feign;

import icu.fangkehou.dapr.feign.annotation.UseDaprClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "dapr", url = "binding.myBinding/create")
@UseDaprClient
public interface DaprFeignTestClient {

    @GetMapping("/")
    void getQuery();

}
