package com.b1gs.controllers.service;

import com.b1gs.controllers.controller.dto.DeviceConfigurationDto;
import com.b1gs.controllers.entity.DeviceConfigurationEntity;
import com.b1gs.controllers.mappers.DeviceConfigurationMapper;
import com.b1gs.controllers.repository.DeviceConfigurationRepository;
import com.b1gs.controllers.repository.DeviceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeviceConfigurationService {

    private final DeviceConfigurationRepository configurationRepository;
    private final DeviceRepository deviceRepository;
    private final ObjectMapper objectMapper;
    private final DeviceConfigurationMapper mapper = Mappers.getMapper(DeviceConfigurationMapper.class);

    private final RabbitmqService rabbitmqService;

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

    @SneakyThrows
    private void sentMessageToController(DeviceConfigurationDto configurationDto){
        String jsonToSent = objectMapper.writeValueAsString(mapper.toMessage(configurationDto));
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
