package com.b1gs.controllers.rabbitmq;

import com.b1gs.controllers.controller.dto.SensorDataDto;
import com.b1gs.controllers.entity.SensorDataEntity;
import com.b1gs.controllers.mappers.SensorDataMapper;
import com.b1gs.controllers.repository.SensorDataRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class SensorDataMessageListener implements MessageListener {

    private SensorDataMapper mapper = Mappers.getMapper(SensorDataMapper.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final SensorDataRepository repository;


    @Override
    public void onMessage(Message message) {
        try {
            List<SensorDataDto> sensorDataDtos = objectMapper.readValue(message.getBody(), new TypeReference<List<SensorDataDto>>() {});
            log.info("Saving sensor data for deviceId={}", sensorDataDtos.get(0).getDeviceId());

            List<SensorDataEntity> sensorDataToSave = sensorDataDtos.stream()
                    .filter((item) -> Float.valueOf(item.getTemperature()) > 0)
                    .map(mapper::toEntity)
                    .collect(Collectors.toList());

            if (sensorDataDtos.size() != sensorDataToSave.size()) {
                log.info("Some data was removed, sensor size {}, filtered sensor data {}", sensorDataDtos.size(), sensorDataToSave.size());
            }
            if (sensorDataToSave.size() > 0) {
                repository.saveAll(sensorDataToSave);
            } else {
                log.info("All sensor data was filtered out");
            }
        } catch (IOException e) {
            log.info("Failing to read an object", e);
        }

    }
}
