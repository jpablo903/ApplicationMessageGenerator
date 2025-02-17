package com.message.controller;

import com.message.entities.MessageDTO;
import com.message.service.IMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final IMessageService<MessageDTO, Long> messageService;

    @GetMapping
    public String showMessagePage(Model model) {
        List<MessageDTO> messages = messageService.getMessages();
        model.addAttribute("messages", messages);
        model.addAttribute("message", new MessageDTO(null,"", LocalDateTime.now(), false));
        return "message";
    }

    @PostMapping
    public String scheduleMessage(@RequestParam String messageContent,
                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        MessageDTO messageDto = new MessageDTO(null, messageContent, date, true);
        messageService.saveMessage(messageDto);
        return "redirect:/messages";
    }

    @PostMapping("/{id}")
    public String deleteMessage(@PathVariable("id") Long id) {
        messageService.deleteMessage(id);
        return "redirect:/messages";
    }
}
