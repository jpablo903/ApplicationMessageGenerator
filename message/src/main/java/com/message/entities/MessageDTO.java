package com.message.entities;

import java.time.LocalDateTime;

public record MessageDTO(Long id, String message_content, LocalDateTime date, boolean sent) {
}
