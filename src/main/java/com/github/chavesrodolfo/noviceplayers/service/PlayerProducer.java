package com.github.chavesrodolfo.noviceplayers.service;

public interface PlayerProducer {

    /**
     * Send a message to Kafka topic
     * @param message
     */
    public void sendMessage(String message);
}
