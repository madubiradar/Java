package com.target.kafka.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.kafka.dto.MessageDTO;
import com.target.kafka.entity.Message;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducerService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public Message publish(String topic, MessageDTO messageDTO) throws JsonProcessingException {

        Message toMessage =messageDTO.toMessage();
        log.info("Message {} is sent from producer service", toMessage);

        kafkaTemplate.send(topic, objectMapper.writeValueAsString(toMessage));

        log.info("objectMapper.writeValueAsString(toMessage) {} is sent as String", objectMapper.writeValueAsString(toMessage));

        return toMessage;

    }


}
