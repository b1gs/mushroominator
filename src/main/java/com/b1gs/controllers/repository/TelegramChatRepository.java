package com.b1gs.controllers.repository;

import com.b1gs.controllers.entity.TelegramChatEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelegramChatRepository extends CrudRepository<TelegramChatEntity, Long> {

    boolean existsByChatId(Long chatId);

    TelegramChatEntity findByChatId(Long chatId);

    List<TelegramChatEntity> findByDeviceId(String deviceId);

}
