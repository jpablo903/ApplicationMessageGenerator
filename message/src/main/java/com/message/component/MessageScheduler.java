package com.message.component;

import com.message.entities.Message;
import com.message.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@EnableScheduling
@Component
public class MessageScheduler {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageScheduler(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Scheduled(fixedRate = 10000)
    public void schedule() {
        LocalDateTime now = LocalDateTime.now();
        List<Message> messages = messageRepository.findBySentFalseAndDateBefore(now);
        for (Message message : messages) {
            message.setSent(true);
            messageRepository.save(message);
        }
    }
}
