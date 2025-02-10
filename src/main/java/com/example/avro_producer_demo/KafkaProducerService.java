package com.example.avro_producer_demo;

import com.example.avro_producer_demo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, User> kafkaTemplate;
    private final KafkaTemplate<String, String> kafkaJsonTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, User> kafkaTemplate, KafkaTemplate<String, String> kafkaJsonTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaJsonTemplate = kafkaJsonTemplate;
    }

    public void sendMessage(String topic, String messageKey, User user) {
        kafkaTemplate.send(topic, messageKey, user);
    }

    public void sendJsonMessage(String topic, String messageKey, String message) {
        kafkaJsonTemplate.send(topic, messageKey, message);
    }
}
