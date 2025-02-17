package com.message.service;

import java.util.List;

public interface IMessageService<T, ID> {
    List<T> getMessages();
    T saveMessage(T message);
    void deleteMessage(ID id);
}
