package com.example.springwebsocket;

import com.example.springwebsocket.DTO.ResponceMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Service
public class WSService {

    private final SimpMessagingTemplate messageginTemplate;
    private final NotificationService notificationService;

    @Autowired
    public WSService(SimpMessagingTemplate messageginTemplate, NotificationService notificationService) {
        this.messageginTemplate = messageginTemplate;
        this.notificationService = notificationService;
    }
    public void notifyFrontend(final String message){
        ResponceMessage responce = new ResponceMessage(message);
        notificationService.SendGlobalNotification();
        messageginTemplate.convertAndSend("/topic/messages",responce);
    }
    public void notifyUser(final String id,final String message){
        ResponceMessage responce = new ResponceMessage(message);
        notificationService.SendPrivateNotification(id);
        messageginTemplate.convertAndSendToUser(id,"/topic/private-messages",responce);
    }
}
