package com.b1gs.controllers.rabbitmq;

import com.b1gs.controllers.entity.DeviceConfigurationEntity;
import com.b1gs.controllers.entity.DeviceRestartHistoryEntity;
import com.b1gs.controllers.mappers.DeviceConfigurationMapper;
import com.b1gs.controllers.rabbitmq.message.DeviceConfigurationMessage;
import com.b1gs.controllers.repository.DeviceConfigurationRepository;
import com.b1gs.controllers.repository.DeviceRepository;
import com.b1gs.controllers.repository.DeviceRestartHistoryRepository;
import com.b1gs.controllers.telegram.TelegramNotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeviceRestartMessageListener implements MessageListener {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final DeviceRestartHistoryRepository deviceRestartHistoryRepository;
    private final DeviceConfigurationRepository deviceConfigurationRepository;
    private final DeviceRepository deviceRepository;
    private final TelegramNotificationService telegramNotificationService;
    private final DeviceConfigurationMapper deviceConfigurationMapper = Mappers.getMapper(DeviceConfigurationMapper.class);

    @Override
    @SneakyThrows
    public void onMessage(Message message) {
        DeviceConfigurationMessage deviceConfigurationMessage = objectMapper.readValue(message.getBody(), DeviceConfigurationMessage.class);

        String deviceId = deviceConfigurationMessage.getDeviceId();
        log.info("Registering device restart {}", deviceId);
        log.info("Config: {}", deviceConfigurationMessage);


        if (deviceRepository.existsByDeviceId(deviceId)) {
            DeviceRestartHistoryEntity restartHistoryEntity = new DeviceRestartHistoryEntity();
            restartHistoryEntity.setDeviceId(deviceId);
            restartHistoryEntity.setCreationDate(LocalDateTime.now());

            if (!deviceRepository.existsByDeviceId(deviceId)) {
                log.info("Message is not consumed:  {}", deviceId);
            }
            deviceRestartHistoryRepository.save(restartHistoryEntity);

            telegramNotificationService.sendRestartNotification(deviceId, makeRestartString(deviceConfigurationMessage));
        }else {
            log.error("Device is not registered: {}", deviceId);
        }
    }

    private String makeRestartString(DeviceConfigurationMessage deviceConfigurationMessage){
        StringBuilder sb = new StringBuilder("Device restart detected..");
        sb.append("\n");
        if (isConfigChanged(deviceConfigurationMessage)) {
            sb.append("Config changed need to fix");
        } else {
            sb.append("Config is up-to-date");
        }
        return sb.toString();
    }

    private boolean isConfigChanged(DeviceConfigurationMessage deviceConfigurationMessage) {

        DeviceConfigurationEntity deviceConfigurationEntity = deviceConfigurationRepository.findByDeviceId(deviceConfigurationMessage.getDeviceId())
                .orElseThrow(() -> new IllegalArgumentException(String.format("DeviceId doesn't exists %s", deviceConfigurationMessage.getDeviceId())));

        DeviceConfigurationMessage databaseConfigAsMessage = deviceConfigurationMapper.toMessage(deviceConfigurationEntity);

        return !databaseConfigAsMessage.equals(deviceConfigurationMessage);
    }
}
