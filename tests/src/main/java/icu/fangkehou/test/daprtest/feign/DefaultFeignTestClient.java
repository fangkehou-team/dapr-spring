package icu.fangkehou.test.daprtest.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "default", url = "https://www.baidu.com")
public interface DefaultFeignTestClient {

    @GetMapping("/")
    void getQuery();
}
