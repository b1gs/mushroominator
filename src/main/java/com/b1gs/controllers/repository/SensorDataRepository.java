package com.b1gs.controllers.repository;

import com.b1gs.controllers.entity.SensorDataEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorDataRepository extends CrudRepository<SensorDataEntity, Long>, JpaRepository<SensorDataEntity, Long> {

    Page<SensorDataEntity> getAllByDeviceId(String deviceId, Pageable pageable);

}
