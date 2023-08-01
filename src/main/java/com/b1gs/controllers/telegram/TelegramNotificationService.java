package com.b1gs.controllers.telegram;

import com.b1gs.controllers.entity.TelegramChatEntity;
import com.b1gs.controllers.repository.TelegramChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TelegramNotificationService {

    private final MushroominatorTelegramBot telegramBot;
    private final TelegramChatRepository telegramChatRepository;


    public void sendRestartNotification(String deviceId, String message){
        List<TelegramChatEntity> chatsToUpdate = telegramChatRepository.findByDeviceId(deviceId);

        chatsToUpdate.forEach(
                (chat) -> telegramBot.sendNotification(chat.getChatId().toString(), message)
        );
    }

}
