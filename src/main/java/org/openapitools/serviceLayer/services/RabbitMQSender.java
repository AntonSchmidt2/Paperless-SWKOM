package org.openapitools.serviceLayer.services;

import org.openapitools.configuration.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Spring Service class to send messages to the RabbitMQ queue
@Service
public class RabbitMQSender {

    private final RabbitTemplate rabbitTemplate;

    // Autowired constructor
    @Autowired
    public RabbitMQSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    // Method to send a message to the OCR_DOCUMENT_IN_QUEUE_NAME queue
    public void sendToOcrDocumentInQueue(String message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.OCR_DOCUMENT_IN_QUEUE_NAME, message);
    }
}
