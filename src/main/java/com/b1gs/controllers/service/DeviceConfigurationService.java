package com.b1gs.controllers.service;

import com.b1gs.controllers.configuration.properties.RabbitProperties;
import com.b1gs.controllers.controller.dto.DeviceConfigurationDto;
import com.b1gs.controllers.entity.DeviceConfigurationEntity;
import com.b1gs.controllers.mappers.DeviceConfigurationMapper;
import com.b1gs.controllers.rabbitmq.message.DeviceConfigurationMessage;
import com.b1gs.controllers.repository.DeviceConfigurationRepository;
import com.b1gs.controllers.repository.DeviceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DeviceConfigurationService {


    private final String brokerHost;
    private final int mqttPort;

    private final DeviceConfigurationRepository configurationRepository;
    private final DeviceRepository deviceRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final DeviceConfigurationMapper mapper = Mappers.getMapper(DeviceConfigurationMapper.class);

    private final RabbitmqService rabbitmqService;

    public DeviceConfigurationService(DeviceConfigurationRepository configurationRepository,
                                      DeviceRepository deviceRepository,
                                      RabbitmqService rabbitmqService,
                                      RabbitProperties rabbitProperties) {
        this.configurationRepository = configurationRepository;
        this.deviceRepository = deviceRepository;
        this.rabbitmqService = rabbitmqService;
        this.brokerHost = rabbitProperties.getHost();
        this.mqttPort = rabbitProperties.getMqttport();
    }

    public DeviceConfigurationDto createDeviceConfiguration(DeviceConfigurationDto configurationDto) {
        String deviceId = configurationDto.getDeviceId();
        if (!deviceRepository.existsByDeviceId(deviceId)) {
            log.info("Device doesn't exists {}", deviceId);
            return configurationDto;
        } else {

            DeviceConfigurationEntity result = saveConfiguration(configurationDto);
            sentMessageToController(configurationDto);

            return mapper.toDto(result);
        }
    }

    public DeviceConfigurationDto getDeviceConfiguration(String deviceId){
        DeviceConfigurationEntity entity = configurationRepository.findByDeviceId(deviceId).orElseThrow(() -> new IllegalArgumentException("deviceId not found"));

        return mapper.toDto(entity);
    }

    @SneakyThrows
    private void sentMessageToController(DeviceConfigurationDto configurationDto){
        DeviceConfigurationMessage message = mapper.toMessage(configurationDto);
        message.setBrokerIp(brokerHost);
        message.setBrokerPort(mqttPort);

        String jsonToSent = objectMapper.writeValueAsString(message);
        rabbitmqService.sendMessage(configurationDto.getDeviceId(), jsonToSent);
    }

    private DeviceConfigurationEntity saveConfiguration(DeviceConfigurationDto configurationDto){
        final DeviceConfigurationEntity deviceEntity = mapper.toEntity(configurationDto);

        DeviceConfigurationEntity deviceConfigurationEntity = configurationRepository.findByDeviceId(configurationDto.getDeviceId())
                .map(item -> mapper.update(deviceEntity, item))
                .orElse(deviceEntity);

        return configurationRepository.save(deviceConfigurationEntity);
    }
}
