package icu.fangkehou.demo.dapr_client_demo.controller;

import icu.fangkehou.demo.dapr_client_demo.service.IDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
public class DemoController {

    IDemoService demoService;

    public DemoController(IDemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/getMessage")
    String getMessageFromServer(){
        return demoService.getDemoMessage();
    }

}
