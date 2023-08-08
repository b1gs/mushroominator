package com.b1gs.controllers.service;

import com.b1gs.controllers.controller.dto.DeviceDto;
import com.b1gs.controllers.entity.DeviceEntity;
import com.b1gs.controllers.mappers.DeviceMapper;
import com.b1gs.controllers.rabbitmq.message.DeviceRestartMessage;
import com.b1gs.controllers.repository.DeviceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository repository;
    private final DeviceMapper mapper = Mappers.getMapper(DeviceMapper.class);
    private final RabbitmqService rabbitmqService;
    private final ObjectMapper objectMapper;

    public DeviceDto createDevice(DeviceDto deviceDto) {
        DeviceEntity deviceEntity = mapper.toEntity(deviceDto);

        deviceEntity = repository.save(deviceEntity);

        return mapper.toDto(deviceEntity);
    }

    @SneakyThrows
    public void restartDevice(String deviceId) {

        DeviceRestartMessage deviceRestartMessage = new DeviceRestartMessage();

        String jsonToSent = objectMapper.writeValueAsString(deviceRestartMessage);

        rabbitmqService.sendMessage(deviceId, jsonToSent);
    }

}
