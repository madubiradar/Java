package com.target.kafka.service;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.kafka.entity.Message;

import com.target.kafka.repository.MessageRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumerService {

    @Autowired
    MessageRepository messageRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "message", groupId = "group_id")
    public void receiveMessageFromDemoTopic(@Payload String message) throws JsonProcessingException {
        log.info("Message {} received from demo topic", message);

        //Message toMessage = objectMapper.readValue(message,Message.class);
        //messageRepository.save(toMessage);
        log.info("Message {} saved from demo topic", message);
    }

    @KafkaListener(topics = "user", groupId = "group_id")
    public void receiveMessageFromPaymentTopic(@Payload String message) throws JsonProcessingException {

        //Message toMessage = objectMapper.readValue(message,Message.class);
        log.info("Message {} received from Payment topic", message);
       // messageRepository.save(toMessage);
    }

}
