package com.message.controller;

import com.message.entities.Message;
import com.message.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

//@RestController
@Controller
@RequestMapping("/messages")
public class MessageController {

    private final IMessageService messageService;

    @Autowired
    public MessageController(IMessageService messageService) {
        this.messageService = messageService;
    }
    //Metodos para API
    /*
    @GetMapping("/messages")
    public List<Message> getMessages() {
        return messageService.getMessages();
    }

    @PostMapping("/message")
    public Message saveMessage(@RequestBody Message message) {
        return messageService.saveMessage(message);
    }

    @DeleteMapping("/message/:{id}")
    public void deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
    }
    */

    @GetMapping
    public String showMessagePage(Model model) {
        List<Message> messages = messageService.getMessages();
        model.addAttribute("messages", messages);
        model.addAttribute("message", new Message());
        return "message";
    }

    @PostMapping
    public String scheduleMessage(@RequestParam String messageContent,
                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        Message message = new Message();
        message.setMessage_content(messageContent);
        message.setDate(date);
        message.setSent(false);
        messageService.saveMessage(message);
        return "redirect:/messages";
    }

    @DeleteMapping("/{id}")
    public String deleteMessage(@PathVariable("id") Long id) {
        messageService.deleteMessage(id);
        return "redirect:/messages";
    }
    
}
