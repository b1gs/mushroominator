package com.b1gs.controllers.rabbitmq.message;

import lombok.Data;

@Data
public class DeviceRestartMessage {

    private EventType eventType = EventType.RESTART;

}
