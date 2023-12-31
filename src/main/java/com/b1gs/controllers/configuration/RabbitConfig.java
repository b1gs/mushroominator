package com.b1gs.controllers.configuration;

import com.b1gs.controllers.configuration.properties.RabbitProperties;
import com.b1gs.controllers.rabbitmq.DeviceRestartMessageListener;
import com.b1gs.controllers.rabbitmq.SensorDataMessageListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    private static final String DEVICE_RESTART_QUEUE_NAME = "device-restart";
    private static final String SENSOR_DATA_QUEUE_NAME = "sensor-data";

    @Bean
    public ConnectionFactory connectionFactory(RabbitProperties rabbitProperties) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(rabbitProperties.getHost());
        connectionFactory.setPort(rabbitProperties.getPort());
        connectionFactory.setUsername(rabbitProperties.getUsername());
        connectionFactory.setPassword(rabbitProperties.getPassword());
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

        container.setDefaultRequeueRejected(false);
        return container;
    }
}
