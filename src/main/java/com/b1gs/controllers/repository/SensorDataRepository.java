package com.b1gs.controllers.repository;

import com.b1gs.controllers.entity.SensorDataEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorDataRepository extends CrudRepository<SensorDataEntity, Long>, JpaRepository<SensorDataEntity, Long> {

    Page<SensorDataEntity> getAllByDeviceIdOrderByCreationDateDesc(String deviceId, Pageable pageable);

}
