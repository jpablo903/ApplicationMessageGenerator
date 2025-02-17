package com.message.controller;

import com.message.entities.MessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.message.service.IMessageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class MessageRestController {

    private final IMessageService<MessageDTO, Long> messageService;

    //Metodos para API

    @GetMapping("/messages")
    public ResponseEntity<List<MessageDTO>> getMessages() {
        return ResponseEntity.ok(messageService.getMessages());
    }

    @PostMapping("/message")
    public ResponseEntity<MessageDTO> saveMessage(@RequestBody MessageDTO messageDto) {
        MessageDTO savedMessage = messageService.saveMessage(messageDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMessage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }

}
