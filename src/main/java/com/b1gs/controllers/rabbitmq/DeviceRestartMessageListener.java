package com.b1gs.controllers.rabbitmq;

import com.b1gs.controllers.entity.DeviceRestartHistoryEntity;
import com.b1gs.controllers.rabbitmq.message.DeviceConfigurationMessage;
import com.b1gs.controllers.repository.DeviceRepository;
import com.b1gs.controllers.repository.DeviceRestartHistoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
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
    private final DeviceRepository deviceRepository;

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
        }else {
            log.error("Device is not registered: {}", deviceId);
        }
    }
}
