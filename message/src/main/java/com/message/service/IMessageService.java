package com.message.service;

import com.message.entities.Message;

import java.util.List;

public interface IMessageService {
    List<Message> getMessages();
    Message saveMessage(Message message);
    void deleteMessage(Long id);
}
