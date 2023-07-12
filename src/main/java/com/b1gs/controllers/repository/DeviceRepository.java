package com.b1gs.controllers.repository;

import com.b1gs.controllers.entity.DeviceEntity;
import com.b1gs.controllers.entity.SensorDataEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends CrudRepository<DeviceEntity, Long> {


}
