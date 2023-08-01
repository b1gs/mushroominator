package com.b1gs.controllers.mappers;

import com.b1gs.controllers.controller.dto.DeviceConfigurationDto;
import com.b1gs.controllers.entity.DeviceConfigurationEntity;
import com.b1gs.controllers.rabbitmq.message.DeviceConfigurationMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface DeviceConfigurationMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "deviceId", target = "deviceId")
    @Mapping(source = "sensorDataInterval", target = "sensorDataInterval")
    @Mapping(source = "sensorReadInterval", target = "sensorReadInterval")
    @Mapping(source = "fanWorkInterval", target = "fanWorkInterval")
    @Mapping(source = "fanWorkPeriodInterval", target = "fanWorkPeriodInterval")
    @Mapping(source = "pumpWorkInterval", target = "pumpWorkInterval")
    @Mapping(source = "humidityThreshold", target = "humidityThreshold")
    @Mapping(source = "fanRelayPin", target = "fanRelayPin")
    @Mapping(source = "pumpRelayPin", target = "pumpRelayPin")
    @Mapping(source = "dhtSensor1", target = "dhtSensor1")
    @Mapping(source = "dhtSensor2", target = "dhtSensor2")
    @Mapping(source = "createDate", target = "createDate")
    @Mapping(source = "updateDate", target = "updateDate")
    DeviceConfigurationDto toDto(DeviceConfigurationEntity entity);

    @Mapping(source = "deviceId", target = "deviceId")
    @Mapping(source = "sensorDataInterval", target = "sensorDataInterval")
    @Mapping(source = "sensorReadInterval", target = "sensorReadInterval")
    @Mapping(source = "fanWorkInterval", target = "fanWorkInterval")
    @Mapping(source = "fanWorkPeriodInterval", target = "fanWorkPeriodInterval")
    @Mapping(source = "pumpWorkInterval", target = "pumpWorkInterval")
    @Mapping(source = "humidityThreshold", target = "humidityThreshold")
    @Mapping(source = "fanRelayPin", target = "fanRelayPin")
    @Mapping(source = "pumpRelayPin", target = "pumpRelayPin")
    @Mapping(source = "dhtSensor1", target = "dhtSensor1")
    @Mapping(source = "dhtSensor2", target = "dhtSensor2")
    @Mapping(source = "createDate", target = "createDate")
    @Mapping(source = "updateDate", target = "updateDate")
    DeviceConfigurationEntity toEntity(DeviceConfigurationDto dto);

    @Mapping(source = "deviceId", target = "deviceId")
    @Mapping(source = "sensorDataInterval", target = "sensorDataInterval")
    @Mapping(source = "sensorReadInterval", target = "sensorReadInterval")
    @Mapping(source = "fanWorkInterval", target = "fanWorkInterval")
    @Mapping(source = "fanWorkPeriodInterval", target = "fanWorkPeriodInterval")
    @Mapping(source = "pumpWorkInterval", target = "pumpWorkInterval")
    @Mapping(source = "humidityThreshold", target = "humidityThreshold")
    @Mapping(source = "fanRelayPin", target = "fanRelayPin")
    @Mapping(source = "pumpRelayPin", target = "pumpRelayPin")
    @Mapping(source = "dhtSensor1", target = "dhtSensor1")
    @Mapping(source = "dhtSensor2", target = "dhtSensor2")
    DeviceConfigurationMessage toMessage(DeviceConfigurationDto dto);

    @Mapping(source = "deviceId", target = "deviceId")
    @Mapping(source = "sensorDataInterval", target = "sensorDataInterval")
    @Mapping(source = "sensorReadInterval", target = "sensorReadInterval")
    @Mapping(source = "fanWorkInterval", target = "fanWorkInterval")
    @Mapping(source = "fanWorkPeriodInterval", target = "fanWorkPeriodInterval")
    @Mapping(source = "pumpWorkInterval", target = "pumpWorkInterval")
    @Mapping(source = "humidityThreshold", target = "humidityThreshold")
    @Mapping(source = "fanRelayPin", target = "fanRelayPin")
    @Mapping(source = "pumpRelayPin", target = "pumpRelayPin")
    @Mapping(source = "dhtSensor1", target = "dhtSensor1")
    @Mapping(source = "dhtSensor2", target = "dhtSensor2")
    DeviceConfigurationMessage toMessage(DeviceConfigurationEntity entity);

    @Mapping(ignore = true, target = "id")
    @Mapping(source = "deviceId", target = "deviceId")
    @Mapping(source = "sensorDataInterval", target = "sensorDataInterval")
    @Mapping(source = "sensorReadInterval", target = "sensorReadInterval")
    @Mapping(source = "fanWorkInterval", target = "fanWorkInterval")
    @Mapping(source = "fanWorkPeriodInterval", target = "fanWorkPeriodInterval")
    @Mapping(source = "pumpWorkInterval", target = "pumpWorkInterval")
    @Mapping(source = "humidityThreshold", target = "humidityThreshold")
    @Mapping(source = "fanRelayPin", target = "fanRelayPin")
    @Mapping(source = "pumpRelayPin", target = "pumpRelayPin")
    @Mapping(source = "dhtSensor1", target = "dhtSensor1")
    @Mapping(source = "dhtSensor2", target = "dhtSensor2")
    @Mapping(expression = "java(java.time.LocalDateTime.now())", target = "updateDate")
    DeviceConfigurationEntity update(DeviceConfigurationEntity source, @MappingTarget DeviceConfigurationEntity target);

}
