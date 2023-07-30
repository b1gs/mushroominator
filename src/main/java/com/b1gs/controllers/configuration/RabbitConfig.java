package com.b1gs.controllers.configuration;

import com.b1gs.controllers.rabbitmq.DeviceRestartMessageListener;
import com.b1gs.controllers.rabbitmq.SensorDataMessageListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    private static final String DEVICE_RESTART_QUEUE_NAME = "device-restart";
    private static final String SENSOR_DATA_QUEUE_NAME = "sensor-data";

    @Value("${spring.rabbitmq.host}")
    private String rabbitHost;

    @Value("${spring.rabbitmq.port}")
    private int rabbitPort;

    @Value("${spring.rabbitmq.username}")
    private String rabbitUser;

    @Value("${rabbit.password}")
    private String rabbitPassword;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(rabbitHost);
        connectionFactory.setPort(rabbitPort);
        connectionFactory.setUsername(rabbitUser);
        connectionFactory.setPassword(rabbitPassword);
        return connectionFactory;
    }

    @Bean
    public MessageListenerAdapter deviceRestartListenerAdapter(DeviceRestartMessageListener receiver) {
        return new MessageListenerAdapter(receiver);
    }

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter deviceRestartListenerAdapter) {
        return getSimpleMessageListenerContainer(connectionFactory, deviceRestartListenerAdapter, DEVICE_RESTART_QUEUE_NAME);
    }

    @Bean
    public MessageListenerAdapter sensorDataListenerAdapter(SensorDataMessageListener receiver) {
        return new MessageListenerAdapter(receiver);
    }

    @Bean
    public SimpleMessageListenerContainer sensorDataContainer(ConnectionFactory connectionFactory, MessageListenerAdapter sensorDataListenerAdapter) {
        return getSimpleMessageListenerContainer(connectionFactory, sensorDataListenerAdapter, SENSOR_DATA_QUEUE_NAME);
    }

    private SimpleMessageListenerContainer getSimpleMessageListenerContainer(ConnectionFactory connectionFactory, MessageListenerAdapter sensorDataListenerAdapter, String sensorDataQueueName) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setQueueNames(sensorDataQueueName);
        container.setConnectionFactory(connectionFactory);
        container.setMessageListener(sensorDataListenerAdapter);
        return container;
    }
}
