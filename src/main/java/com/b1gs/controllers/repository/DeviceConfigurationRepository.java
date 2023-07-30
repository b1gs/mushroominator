package com.b1gs.controllers.repository;

import com.b1gs.controllers.entity.DeviceConfigurationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceConfigurationRepository extends CrudRepository<DeviceConfigurationEntity, Long> {

    Optional<DeviceConfigurationEntity> findByDeviceId(String deviceId);
}
