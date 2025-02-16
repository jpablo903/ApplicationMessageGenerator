package com.message.component;

import com.message.entities.Message;
import com.message.repositories.MessageRepository;
import jakarta.annotation.PostConstruct;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class DiscordMessageScheduler {
    @Value("${discord.token}")
    private String botToken;

    @Value("${discord.channel.id}")
    private String channelId;

    private JDA jda;

    @PostConstruct
    public void init() {
        try {
            jda = JDABuilder.createDefault(botToken)
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                    .build();
            jda.awaitReady();
        } catch (Exception e) {
            System.err.println("Error al conectar el bot de Discord: " + e.getMessage());
        }
    }

    public void sendMessage(String message) {
        TextChannel channel = jda.getTextChannelById(channelId);
        if (channel != null) {
            channel.sendMessage(message).queue();
        } else {
            System.err.println("No se encontró el canal con ID: " + channelId);
        }
    }
}
