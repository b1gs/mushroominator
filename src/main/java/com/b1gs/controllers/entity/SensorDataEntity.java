package com.b1gs.controllers.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "sensor_data", schema = "mushroominator")
@Data
@ToString
public class SensorDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "temperature")
    private int temperature;

    @Column(name = "humidity")
    private int humidity;

}
