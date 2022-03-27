package com.example.springwebsocket;

import com.example.springwebsocket.DTO.ResponceMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final SimpMessagingTemplate messageginTemplate;

    @Autowired
    public NotificationService(SimpMessagingTemplate messageginTemplate) {
        this.messageginTemplate = messageginTemplate;
    }

    public  void SendGlobalNotification(){
        ResponceMessage global_notification = new ResponceMessage("Global Notification");
        messageginTemplate.convertAndSend("/topic/global-notifications",global_notification);
    }
    public  void SendPrivateNotification(final String id){
        ResponceMessage global_notification = new ResponceMessage("Private Notification");
        messageginTemplate.convertAndSendToUser(id,"/topic/private-notifications",global_notification);
    }
}
