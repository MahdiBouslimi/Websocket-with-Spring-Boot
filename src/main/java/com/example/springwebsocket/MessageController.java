package com.example.springwebsocket;

import com.example.springwebsocket.DTO.Message;
import com.example.springwebsocket.DTO.ResponceMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

@Controller
public class MessageController {
    @Autowired
    private NotificationService notificationService;


    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public ResponceMessage getMessage(final Message message) throws InterruptedException {
        Thread.sleep(1000);
        notificationService.SendGlobalNotification();
        return new ResponceMessage(HtmlUtils.htmlEscape(message.getMessageContent()));
    }
    @MessageMapping("/private-message")
    @SendToUser("/topic/private-messages")
    public ResponceMessage getPrivateMessage(final Message message, final Principal principal) throws InterruptedException {
        Thread.sleep(1000);
        notificationService.SendPrivateNotification(principal.getName());
        return new ResponceMessage(HtmlUtils.htmlEscape("Sending private message to User " +principal.getName()+ ": " +message.getMessageContent()));
    }

}
