package com.b1gs.controllers.repository;

import com.b1gs.controllers.entity.DeviceRestartHistoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DeviceRestartHistoryRepository extends CrudRepository<DeviceRestartHistoryEntity, Long> {

    List<DeviceRestartHistoryEntity> findAllByDeviceIdAndCreationDateBetween(String deviceId, LocalDateTime startDate, java.time.LocalDateTime endDate);
    List<DeviceRestartHistoryEntity> findAllByDeviceId(String deviceId);
}
