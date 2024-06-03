package icu.fangkehou.demo.dapr_client_demo.service.impl;

import icu.fangkehou.demo.dapr_client_demo.config.DemoApplicationConfig;
import icu.fangkehou.demo.dapr_client_demo.service.IDemoService;
import org.springframework.stereotype.Service;

@Service
public class ConfigDemoService implements IDemoService {

    private final DemoApplicationConfig config;

    public ConfigDemoService(DemoApplicationConfig config) {
        this.config = config;
    }


    @Override
    public String getDemoMessage() {
        return config.getHelloMessage();
    }
}
