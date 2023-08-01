package com.b1gs.controllers.telegram;

import com.b1gs.controllers.entity.TelegramChatEntity;
import com.b1gs.controllers.repository.DeviceRepository;
import com.b1gs.controllers.repository.TelegramChatRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.time.LocalDateTime;

@Slf4j
@Component
public class MushroominatorTelegramBot extends TelegramLongPollingBot {

    @Value("${telegram.bot.username}")
    private String username;

    @Value("${telegram.bot.token}")
    private String botToken;

    private final TelegramChatRepository chatRepository;
    private final DeviceRepository deviceRepository;

    public MushroominatorTelegramBot(TelegramChatRepository chatRepository, DeviceRepository deviceRepository) {
        super();
        this.chatRepository = chatRepository;
        this.deviceRepository = deviceRepository;
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        log.info("[TELEGRAM] Got message...");
        if (update.hasMessage()) {
            Message msg = update.getMessage();


            String messageText = determineMessageToSent(msg);

            sendNotification(String.valueOf(msg.getChatId()), messageText);
        } else {
            System.out.println("[TELEGRAM] no message!!!!!!!!!!!!!!!!!!");
        }
    }

    private String determineMessageToSent(Message msg) {

        Long chatId = msg.getChatId();

        User user = msg.getFrom();
        Long userId = user.getId();


        if (!chatRepository.existsByChatId(chatId)) {
            TelegramChatEntity chat = new TelegramChatEntity();
            chat.setChatId(chatId);
            chat.setUserId(userId);
            chat.setFirstName(user.getFirstName());
            chat.setLastName(user.getLastName());
            chat.setCreateDate(LocalDateTime.now());
            chat.setUpdateDate(LocalDateTime.now());

            chatRepository.save(chat);

            return "Please provide deviceId you want to get alerts for: ";
        } else {
            TelegramChatEntity chatEntity = chatRepository.findByChatId(chatId);
            if (StringUtils.hasText(chatEntity.getDeviceId())) {
                return "We are cool you registered..";
            } else {

                String possibleDeviceId = msg.getText();
                if (!(possibleDeviceId.length() == 36)) {
                    return "Please provide valid deviceId";
                }
                if (deviceRepository.existsByDeviceId(possibleDeviceId)) {
                    TelegramChatEntity telegramChatEntity = chatRepository.findByChatId(chatId);
                    telegramChatEntity.setDeviceId(possibleDeviceId);

                    chatRepository.save(telegramChatEntity);
                    return "Cool you have been registered";
                } else {
                    return "DeviceId invalid.";
                }
            }

        }
    }

    @PostConstruct
    public void startup() {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        log.info("[TELEGRAM] Bot started..");
    }

    public void sendNotification(String chatId, String msg) {
        var response = new SendMessage(chatId, msg);
        try {
            execute(response);
        } catch (TelegramApiException e) {
            log.info("[TELEGRAM] Message wasn't sent to {}, msg({}) ", chatId, msg, e);
        }
    }
}
