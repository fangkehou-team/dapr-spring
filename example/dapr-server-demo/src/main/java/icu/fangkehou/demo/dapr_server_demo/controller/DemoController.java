package icu.fangkehou.demo.dapr_server_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/getMessage")
    String getMessageFromServer(){
        return "hello";
    }

    @PostMapping("/setMessage")
    void sendMessageToServer(@RequestBody String newMessage){

    }

}
