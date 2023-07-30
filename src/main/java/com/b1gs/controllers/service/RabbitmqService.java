package com.b1gs.controllers.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitmqService {

    private static Logger logger = LoggerFactory.getLogger(RabbitmqService.class);
    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(String deviceId, String message) {
        logger.info("sending message {}" , message);
        rabbitTemplate.convertAndSend("amq.topic", deviceId, message);
    }

}
