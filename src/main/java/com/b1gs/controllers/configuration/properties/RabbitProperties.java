package com.b1gs.controllers.configuration.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitProperties {

   private String host;
   private int port;
   private int mqttport;
   private String username;
   private String password;

}
