package com.b1gs.controllers.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "device_restart_history", schema = "mushroominator")
@Data
@ToString
public class DeviceRestartHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "creation_dt")
    private LocalDateTime creationDate;

}
