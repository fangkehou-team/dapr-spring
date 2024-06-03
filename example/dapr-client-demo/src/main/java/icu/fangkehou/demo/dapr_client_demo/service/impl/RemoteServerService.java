package icu.fangkehou.demo.dapr_client_demo.service.impl;

import icu.fangkehou.demo.dapr_client_demo.client.DaprServerClient;
import icu.fangkehou.demo.dapr_client_demo.service.IServerService;
import org.springframework.stereotype.Service;

@Service
public class RemoteServerService implements IServerService {

    DaprServerClient daprServerClient;

    public RemoteServerService(DaprServerClient daprServerClient) {
        this.daprServerClient = daprServerClient;
    }

    @Override
    public String getRemoteMessage() {
        return daprServerClient.getMessageFromServer();
    }

    @Override
    public void setRemoteMessage(String newMessage) {
        daprServerClient.sendMessageToServer(newMessage);
    }
}
