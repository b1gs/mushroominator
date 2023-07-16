package com.b1gs.controllers.repository;

import com.b1gs.controllers.entity.SensorDataEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorDataRepository extends CrudRepository<SensorDataEntity, Long> {

    List<SensorDataEntity> getAllByDeviceIdOrderByCreationDateDesc(String deviceId);
}
