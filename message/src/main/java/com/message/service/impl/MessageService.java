package com.message.service.impl;

import com.message.component.DiscordMessageScheduler;
import com.message.entities.Message;
import com.message.entities.MessageDTO;
import com.message.repositories.MessageRepository;
import com.message.service.IMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageService implements IMessageService<MessageDTO, Long> {

    private final MessageRepository messageRepository;
    private final DiscordMessageScheduler discordMessageScheduler;

    @Override
    public List<MessageDTO> getMessages() {
        return messageRepository.findAll().stream()
                .map(message -> new MessageDTO(
                        message.getId(),
                        message.getMessage_content(),
                        message.getDate(),
                        message.isSent()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public MessageDTO saveMessage(MessageDTO messageDto) {
        Message message = Message.builder()
                .message_content(messageDto.message_content())
                .date(messageDto.date())
                .sent(false)
                .build();
        messageRepository.save(message);
        return new MessageDTO(message.getId(), message.getMessage_content(), message.getDate(), message.isSent());
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
