package icu.fangkehou.demo.dapr_client_demo.client;

import feign.Body;
import icu.fangkehou.dapr.feign.annotation.UseDaprClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@UseDaprClient
@FeignClient(name = "dapr-server", url = "http://method.dapr-demo-spring/demo")
public interface DaprServerClient {

    @GetMapping("/getMessage")
    String getMessageFromServer();

    @PostMapping("/setMessage")
    void sendMessageToServer(@RequestBody String newMessage);

}
