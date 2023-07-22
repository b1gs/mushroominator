package com.b1gs.controllers.controller.dto;


import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SensorDataList {

    private List<SensorDataDto> list;

    // Custom method with @JsonValue annotation
    @JsonValue
    public List<SensorDataDto> getList() {
        return list;
    }
}
