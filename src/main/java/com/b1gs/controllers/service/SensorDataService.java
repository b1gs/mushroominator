package com.b1gs.controllers.service;

import com.b1gs.controllers.entity.SensorDataEntity;
import com.b1gs.controllers.repository.SensorDataRepository;
import org.springframework.stereotype.Component;

@Component
public class SensorDataService {

    private final SensorDataRepository repository;

    public SensorDataService(SensorDataRepository repository) {
        this.repository = repository;
    }

    public void createSensorData(SensorDataEntity sensorDataEntity) {
        repository.save(sensorDataEntity);
    }
}
