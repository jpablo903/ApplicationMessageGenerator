package com.message.service.impl;

import com.message.component.DiscordMessageScheduler;
import com.message.entities.Message;
import com.message.repositories.MessageRepository;
import com.message.service.IMessageService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService implements IMessageService {

    private final MessageRepository messageRepository;
    private final DiscordMessageScheduler discordMessageScheduler;

    public MessageService(MessageRepository messageRepository, DiscordMessageScheduler discordMessageScheduler) {
        this.messageRepository = messageRepository;
        this.discordMessageScheduler = discordMessageScheduler;
    }

    @Override
    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Message saveMessage(Message message) {
        message.setSent(false);
        return messageRepository.save(message);
    }

    @Override
    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }

    public void sendScheduledMessages() {
        List<Message> unsentMessages = messageRepository.findBySentFalseAndDateBefore(LocalDateTime.now());
        for (Message message : unsentMessages) {
            discordMessageScheduler.sendMessage(message.getMessage_content());
            message.setSent(true);
            messageRepository.save(message);
        }
    }
}
