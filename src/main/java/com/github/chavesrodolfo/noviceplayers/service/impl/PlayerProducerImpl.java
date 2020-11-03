package com.github.chavesrodolfo.noviceplayers.service.impl;

import com.github.chavesrodolfo.noviceplayers.service.PlayerProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PlayerProducerImpl implements PlayerProducer {

    private static final String TOPIC = "novice-players";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendMessage(String message) {
        log.info("posting message {} in kafka topic", message);
        this.kafkaTemplate.send(TOPIC, message);
    }
    
}
