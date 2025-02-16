package com.message.component;

import com.message.service.impl.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@EnableScheduling
@Component
public class MessageScheduler {
    private final MessageService messageService;

    @Autowired
    public MessageScheduler(MessageService messageService) {
        this.messageService = messageService;
    }

    @Scheduled(fixedRate = 5000)
    public void checkAndSendMessages() {
        messageService.sendScheduledMessages();
    }
}
