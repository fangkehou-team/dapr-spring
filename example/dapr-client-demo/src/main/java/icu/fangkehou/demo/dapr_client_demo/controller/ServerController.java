package icu.fangkehou.demo.dapr_client_demo.controller;

import icu.fangkehou.demo.dapr_client_demo.service.IServerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/server")
public class ServerController {

    IServerService serverService;

    public ServerController(IServerService serverService) {
        this.serverService = serverService;
    }

    @GetMapping("/getMessage")
    String getMessageFromServer(){
        return serverService.getRemoteMessage();
    }

    @PostMapping("/setMessage")
    void sendMessageToServer(){

    }

}
