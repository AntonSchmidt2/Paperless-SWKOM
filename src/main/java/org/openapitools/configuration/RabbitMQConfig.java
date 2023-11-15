package org.openapitools.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // get these from application.properties
    @Value("${spring.rabbitmq.host}")
    private String endpoint;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    // default exchange routing mechanism
    public static final String EXCHANGE = "";

    public static final String OCR_DOCUMENT_IN_QUEUE_NAME = "ORC_DOCUMENT_IN";
    public static final String OCR_DOCUMENT_OUT_QUEUE_NAME = "ORC_DOCUMENT_OUT";

    // this is the queue that the OCR service will listen to
    //durable false means that the queue will not survive a broker restart
    @Bean
    public Queue ocrDocumentInQueue() {
        return new Queue(OCR_DOCUMENT_IN_QUEUE_NAME, false);
    }

    @Bean
    public Queue ocrDocumentOutQueue() { return new Queue(OCR_DOCUMENT_OUT_QUEUE_NAME, false); }


    // Set up connection to RabbitMQ
    @Bean
    public ConnectionFactory rabbitMQConnectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(endpoint);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    // RabbitTemplate is a class that provides support for RabbitMQ sending and receiving.
    // We use this Template for communication with rabbit later on
    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(rabbitMQConnectionFactory());
        rabbitTemplate.setDefaultReceiveQueue(OCR_DOCUMENT_IN_QUEUE_NAME);
        return rabbitTemplate;
    }
}
